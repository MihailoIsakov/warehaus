package rs.ac.uns.ftn.xws.services.payments;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.MagacinskaKartica;
import model.PoslovnaGodina;
import model.PrometniDokument;
import model.PrometniDokument.statusDokumenta;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.xws.util.Authenticate;
import daoBean.MagacinskaKarticaDaoLocal;
import daoBean.PoslovnaGodinaDaoLocal;
import daoBean.PrometniDokumentDaoLocal;

@Path("/poslovna-godina-doc")
public class PoslGodinaDocService {
	private static Logger log = Logger.getLogger(PoslGodinaDocService.class);

	@EJB
	private PoslovnaGodinaDaoLocal poslGodDao;
	@EJB
	private PrometniDokumentDaoLocal promDocDao;
	@EJB
	private MagacinskaKarticaDaoLocal magCardDao;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	// @Authenticate
	public List<PoslovnaGodina> findAll() {
		List<PoslovnaGodina> retVal = null;
		try {
			retVal = poslGodDao.findAll();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
	}

	

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Authenticate
	public PoslovnaGodina create(PoslovnaGodina entity) {

		PoslovnaGodina retVal = null;
		try {

			retVal = poslGodDao.persist(entity);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
	}

	@GET
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Authenticate
	public ArrayList<PrometniDokument> zakljucivanje(@PathParam("id") Integer id, String da) {
		log.info("PUT");
		PoslovnaGodina entity = poslGodDao.findById(id);
		ArrayList<PrometniDokument> retVal = new ArrayList<PrometniDokument>();
		if (!entity.getZakljucenaGodina()) {
			Iterator<PrometniDokument> promDocIt = entity.getPromDoc().iterator();
			while (promDocIt.hasNext()) {
				PrometniDokument prometniDokument = (PrometniDokument) promDocIt
						.next();
				if(prometniDokument.getStatusDokumenta().equals(statusDokumenta.u_fazi_formiranje)){
					retVal.add(prometniDokument);
				}
				
			}
			if(retVal.size()==0){
				entity.setZakljucenaGodina(true);
				if (!zakljuciGodinu(entity)) {
					return null;
				}
			}
			
			
			
			try {
				poslGodDao.merge(entity);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		}
		return retVal;
	}

	private boolean zakljuciGodinu(PoslovnaGodina entity) {
		List<PrometniDokument> list = promDocDao.findAll();
		for (PrometniDokument p : list) {
			if (p.getPoslovnaGodina().getIdPoslovnaGodina() == entity
					.getIdPoslovnaGodina() && p.getDatumKnjizenja() == null) {
				p.setDatumKnjizenja(new Date());
				p.setStatusDokumenta(statusDokumenta.proknjizen);
				try {
					promDocDao.merge(p);
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		List<MagacinskaKartica> mag = magCardDao.findAll();
		for (MagacinskaKartica m : mag) {
			// Pocetno stanje + ulaz - izlaz
			m.setPocetnoStanjeKol(m.getPocetnoStanjeKol().add(m.getKolUlaza())
					.subtract(m.getKolIzlaza()));
			m.setPoslovnaGodina(entity);
			try {
				magCardDao.merge(m);
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Authenticate
	public void removeItem(@PathParam("id") Integer id) {
		PoslovnaGodina p = poslGodDao.findById(id);
		try {
			poslGodDao.remove(p);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

	}
}
