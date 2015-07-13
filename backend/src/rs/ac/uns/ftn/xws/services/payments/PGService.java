package rs.ac.uns.ftn.xws.services.payments;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.PoslovnaGodina;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.xws.util.Authenticate;
import daoBean.MagacinskaKarticaDaoLocal;
import daoBean.PoslovnaGodinaDaoLocal;
import daoBean.PrometniDokumentDaoLocal;

@Path("/poslovna-godina-new")
public class PGService {
	private static Logger log = Logger.getLogger(PGService.class);
	
	@EJB
	private PoslovnaGodinaDaoLocal poslGodDao;
	@EJB
	private PrometniDokumentDaoLocal promDocDao;
	@EJB
	private MagacinskaKarticaDaoLocal magCardDao;
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Authenticate
	public PoslovnaGodina zakljucivanje(@PathParam("id") Integer id) throws Exception {
		log.error("U getu2!!!");
		PoslovnaGodina retVal = null;
		try {
			retVal = poslGodDao.findById(id);
			if(retVal == null)
				throw new Exception();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new Exception();
		}
		log.error(retVal);
		return retVal;
	}
}
