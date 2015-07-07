package daoBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import model.AnalitikaMagacinskeKartice;
import model.MagacinskaKartica;
import model.PopisniDokument;
import model.StavkaPopisa;
import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDaoBean;

@Stateless
@Local(PopisniDokumentDaoLocal.class)
public class PopisniDokumentDao extends GenericDaoBean<PopisniDokument, Integer> implements PopisniDokumentDaoLocal {
	
	@EJB
	private StavkaPopisaDaoLocal stavkaDao;
	
	@EJB
	private MagacinskaKarticaDaoLocal magCardDao;
	
	@EJB
	private AnalitikaMagacinskeKarticeDaoLocal anDao;
	
	@SuppressWarnings("static-access")
	@Override
	public PopisniDokument persistSaStavkama(PopisniDokument p) {
		PopisniDokument retVal = null;
		try{
			retVal = persist(p);
			
			List<StavkaPopisa> empty = new ArrayList<StavkaPopisa>();
			int redniBroj = p.getStavke().size();
			//dodati prazne stavke
			List<Object> spisak = magCardDao.findByMagacin(p.getMagacin().getIdMagacin());
			for(Object o: spisak){
				if(((MagacinskaKartica) o).getPoslovnaGodina().getIdPoslovnaGodina() == p.getPoslovnaGodina().getIdPoslovnaGodina()){
					if(!p.getStavke().contains((MagacinskaKartica) o)){
						StavkaPopisa ss = new StavkaPopisa();
						ss.setArtikal(((MagacinskaKartica) o).getArtikal());
						ss.setCenaAktuelnaPriPopisu(((MagacinskaKartica) o).getProsecnaCena());
						ss.setKolicinaPoPopisu(BigDecimal.valueOf(0.0));
						ss.setKolicinaUKartici(((MagacinskaKartica) o).getPocetnoStanjeKol().add(((MagacinskaKartica) o).getKolUlaza()).subtract(((MagacinskaKartica) o).getKolIzlaza()));
						ss.setPopisniDokument(p);
						ss.setRedniBrojStavke(redniBroj);
						redniBroj++;
						ss.setVrednostPoPopisu(BigDecimal.valueOf(0.0));
						ss.setVrednostUKartici(((MagacinskaKartica) o).getPocetnoStanjeVr().add(((MagacinskaKartica) o).getVrUlaza()).subtract(((MagacinskaKartica) o).getVrIzlaza()));
						empty.add(ss);
					}
				}
			}
			
			//sacuvati sve
			Iterator<StavkaPopisa> stavke = p.getStavke()
					.iterator();
			while (stavke.hasNext()) {
				StavkaPopisa stavkaPopisa = (StavkaPopisa) stavke
						.next();
				stavkaPopisa.setPopisniDokument(p);
				stavkaDao.persist(stavkaPopisa);
				//ako se ne slazu stanja, izvrsiti korekciju
				if(stavkaPopisa.getKolicinaPoPopisu().compareTo(stavkaPopisa.getKolicinaUKartici()) != 0){
					//izmena kartice
					MagacinskaKartica entity = magCardDao.findByMagaciniArtikaliPG(p.getMagacin().getIdMagacin(), stavkaPopisa.getArtikal().getIdArtikal(), p.getPoslovnaGodina().getIdPoslovnaGodina());
					//kreiranje analitike
					BigDecimal diff = stavkaPopisa.getKolicinaUKartici().subtract(stavkaPopisa.getKolicinaPoPopisu()).abs();
					AnalitikaMagacinskeKartice a = new AnalitikaMagacinskeKartice();
		        	a.setCena(entity.getProsecnaCena());
		        	a.setDatumPromene(new Date());
		        	a.setKolicina(diff);
		        	a.setMagacinskaKartica(entity);
		        	//a.setRedniBroj(redniBroj);
		        	a.setSifraDokumenta("KOR");
		        	//VREDNOST korekcije
		        	BigDecimal value = diff.multiply(entity.getProsecnaCena());
		        	a.setVrednost(value);
		        	if(stavkaPopisa.getKolicinaPoPopisu().compareTo(stavkaPopisa.getKolicinaUKartici()) == -1){
		        		//Nova je veca, povecava se kolUlaz
		            	a.setSmer(a.getSmer().U);
		            	entity.setKolUlaza(entity.getKolUlaza().add(diff));
		            	entity.setVrUlaza(entity.getVrUlaza().add(value));
		        	}else {
		        		//Stara je veca, povecava se kolIzlaz
		            	a.setSmer(a.getSmer().I);
		            	entity.setKolIzlaza(entity.getKolIzlaza().add(diff));
		            	entity.setVrIzlaza(entity.getVrIzlaza().add(value));
		        	}
		        	a.setStavkaPrometnogDokumenta(null);
		        	anDao.persist(a);
		        	magCardDao.persist(entity);
				}
			}
			for(StavkaPopisa sp: empty){
				stavkaDao.persist(sp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return retVal;
	}

}
