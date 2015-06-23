package daoBean;

import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import model.AnalitikaMagacinskeKartice;
import model.AnalitikaMagacinskeKartice.smer;
import model.Magacin;
import model.MagacinskaKartica;
import model.PrometniDokument;
import model.StavkaPrometnogDokumenta;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.xws.entities.payments.Invoice;
import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDaoBean;

@Stateless
@Local(MagacinskaKarticaDaoLocal.class)
public class MagacinskaKarticaDao extends
		GenericDaoBean<MagacinskaKartica, Long> implements
		MagacinskaKarticaDaoLocal {
	private static Logger log = Logger.getLogger(Invoice.class);


	@EJB
	AnalitikaMagacinskeKarticeDaoLocal amkDao;

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findByMagacin(Integer id) {

		Query q = em.createNamedQuery("MagacinskaKartica.findByMagacin");

		q.setParameter("id", id);
		return q.getResultList();
	}
	
	@Override
	public MagacinskaKartica findByMagCardId(Integer id){
		Query q = em.createNamedQuery("MagacinskaKartica.findByMagCardId");

		q.setParameter("id", id);
		return (MagacinskaKartica) q.getSingleResult();
	}

	@Override
	public MagacinskaKartica findByMagaciniArtikaliPG(int idMagacin,
			int idArtikal, int idPG) throws Exception {
		try{
		Query q = em
				.createNamedQuery("MagacinskaKartica.findByMagaciniArtikaliPG");

		
		q.setParameter("idMagacin", idMagacin);
		q.setParameter("idArtikal", idArtikal);
		q.setParameter("idPG", idPG);
		List<MagacinskaKartica> lista = q.getResultList();
		if(!lista.isEmpty())
			return lista.get(0);
		else
			throw new Exception();
		}catch(Exception e){
			throw e;
		}
	}

	@Override
	public void update(PrometniDokument entity, Magacin magacin, int brMagacina) throws Exception {
		try {
			Iterator<StavkaPrometnogDokumenta> stavke = entity.getStavke()
					.iterator();

			while (stavke.hasNext()) {
				StavkaPrometnogDokumenta stavkaPrometnogDokumenta = (StavkaPrometnogDokumenta) stavke
						.next();

				smer smer;
				
				if (brMagacina == 1) {
					if (entity.getVrstaDokumenta().getNazivVrste()
							.equals("primka"))
						smer = model.AnalitikaMagacinskeKartice.smer.U;
					else
						smer = model.AnalitikaMagacinskeKartice.smer.I;

				} else {
					if (entity.getVrstaDokumenta().getNazivVrste()
							.equals("primka"))
						smer = model.AnalitikaMagacinskeKartice.smer.I;
					else
						smer = model.AnalitikaMagacinskeKartice.smer.U;
				}
				
			
				MagacinskaKartica mk = findByMagaciniArtikaliPG(
						magacin.getIdMagacin(), stavkaPrometnogDokumenta
								.getArtikal().getIdArtikal(), entity
								.getPoslovnaGodina().getIdPoslovnaGodina());
				if (mk != null) {
					mk.update(stavkaPrometnogDokumenta, smer);
					merge(mk);
				} else {
					mk = new MagacinskaKartica(stavkaPrometnogDokumenta, smer);
					mk.setMagacin(magacin);
					mk.setPoslovnaGodina(entity.getPoslovnaGodina());
					persist(mk);
				}
				AnalitikaMagacinskeKartice amk = new AnalitikaMagacinskeKartice(
						stavkaPrometnogDokumenta);

				amk.setMagacinskaKartica(mk);
				amk.setSmer(smer);
				amkDao.persist(amk);

			}
		} catch (Exception e) {
			throw e;
		}
	}
}
