package rs.ac.uns.ftn.xws.services.payments;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.MagacinskaKartica;
import model.PoslovnaGodina;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.xws.util.Authenticate;
import daoBean.MagacinskaKarticaDaoLocal;
import daoBean.PoslovnaGodinaDaoLocal;

@Path("/poslovna-godina-open")
public class PoslovnaGodinaOService {
	private static Logger log = Logger.getLogger(PoslovnaGodinaOService.class);

	@EJB
	private PoslovnaGodinaDaoLocal poslGodDao;
	
	@EJB
	private MagacinskaKarticaDaoLocal magCardDao;
	
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Authenticate
	public PoslovnaGodina update(@PathParam("id") String id) {
		log.error("IN");
		PoslovnaGodina retVal = null;
		List<MagacinskaKartica> spisak = null;
		List<MagacinskaKartica> spisakNovi = null;
		try {
			spisak = magCardDao.findByPG(Integer.parseInt(id));
			spisakNovi = magCardDao.findByPG(Integer.parseInt(id) + 1);
			int rb = 0;
			if(spisakNovi.size() == 0){
				for(MagacinskaKartica m: spisak){
					BigDecimal ukupnaKol = m.getPocetnoStanjeKol().add(m.getKolUlaza()).subtract(m.getKolIzlaza());
					BigDecimal ukupnaVr = m.getPocetnoStanjeVr().add(m.getVrUlaza()).subtract(m.getVrIzlaza());
					if(ukupnaKol.compareTo(BigDecimal.valueOf(0.0)) == 1){
						MagacinskaKartica nova = new MagacinskaKartica();
						nova.setArtikal(m.getArtikal());
						nova.setKolIzlaza(BigDecimal.valueOf(0.0));
						nova.setKolUlaza(BigDecimal.valueOf(0.0));
						nova.setMagacin(m.getMagacin());
						nova.setPocetnoStanjeKol(ukupnaKol);
						nova.setPocetnoStanjeVr(ukupnaVr);
						retVal = poslGodDao.findById(Integer.parseInt(id) + 1);
						nova.setPoslovnaGodina(retVal);
						nova.setProsecnaCena(m.getProsecnaCena());
						nova.setRedniBrojKartice(rb);
						nova.setVrIzlaza(BigDecimal.valueOf(0.0));
						nova.setVrUlaza(BigDecimal.valueOf(0.0));
						magCardDao.persist(nova);
						rb++;
					}
				}
			}else {
				for(MagacinskaKartica m: spisak){
					BigDecimal ukupnaKol = m.getPocetnoStanjeKol().add(m.getKolUlaza()).subtract(m.getKolIzlaza());
					BigDecimal ukupnaVr = m.getPocetnoStanjeVr().add(m.getVrUlaza()).subtract(m.getVrIzlaza());
					for(MagacinskaKartica k: spisakNovi){
						if(m.getMagacin().getIdMagacin() == k.getMagacin().getIdMagacin() && 
								m.getArtikal().getIdArtikal() == k.getArtikal().getIdArtikal()){
							k.setPocetnoStanjeKol(ukupnaKol);
							k.setPocetnoStanjeVr(ukupnaVr);
						}
					}
				}
			}
		} catch (Exception e) {
			if(spisakNovi == null){
				int rb = 0;
				for(MagacinskaKartica m: spisak){
					BigDecimal ukupnaKol = m.getPocetnoStanjeKol().add(m.getKolUlaza()).subtract(m.getKolIzlaza());
					BigDecimal ukupnaVr = m.getPocetnoStanjeVr().add(m.getVrUlaza()).subtract(m.getVrIzlaza());
					if(ukupnaKol.compareTo(BigDecimal.valueOf(0.0)) == 1){
						MagacinskaKartica nova = new MagacinskaKartica();
						nova.setArtikal(m.getArtikal());
						nova.setKolIzlaza(BigDecimal.valueOf(0.0));
						nova.setKolUlaza(BigDecimal.valueOf(0.0));
						nova.setMagacin(m.getMagacin());
						nova.setPocetnoStanjeKol(ukupnaKol);
						nova.setPocetnoStanjeVr(ukupnaVr);
						retVal = poslGodDao.findById(Integer.parseInt(id) + 1);
						nova.setPoslovnaGodina(retVal);
						nova.setProsecnaCena(m.getProsecnaCena());
						nova.setRedniBrojKartice(rb);
						nova.setVrIzlaza(BigDecimal.valueOf(0.0));
						nova.setVrUlaza(BigDecimal.valueOf(0.0));
						try {
							magCardDao.persist(nova);
						} catch (NoSuchFieldException e1) {
							e1.printStackTrace();
						}
						rb++;
					}
				}
			}
			log.error(e.getMessage(), e);
		}
		return retVal;
	}
}
