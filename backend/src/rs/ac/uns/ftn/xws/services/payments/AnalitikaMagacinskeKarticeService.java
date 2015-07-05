package rs.ac.uns.ftn.xws.services.payments;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.AnalitikaMagacinskeKartice;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.xws.util.Authenticate;
import daoBean.AnalitikaMagacinskeKarticeDaoLocal;
import daoBean.MagacinskaKarticaDaoLocal;

@Path("/analitika")
public class AnalitikaMagacinskeKarticeService {
	private static Logger log = Logger.getLogger(AnalitikaMagacinskeKarticeService.class);
	
	@EJB
	private AnalitikaMagacinskeKarticeDaoLocal anDao;
	
	@EJB
	private MagacinskaKarticaDaoLocal magCardDao;
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Authenticate
	public List<AnalitikaMagacinskeKartice> findByMagCardId(@PathParam("id") String id) {
		log.error("ANALITIKA IN");
		List<AnalitikaMagacinskeKartice> retVal = null;
		try {

			retVal = anDao.findByMagCardId(Integer.parseInt(id));
			log.error("ANALITIKA SIZE: "+retVal.size());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
	}
}
