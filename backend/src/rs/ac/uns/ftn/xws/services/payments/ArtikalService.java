package rs.ac.uns.ftn.xws.services.payments;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Artikal;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.xws.util.Authenticate;
import daoBean.ArtikalDaoLocal;

@Path("/artikal")
public class ArtikalService {
	
	private static Logger log = Logger.getLogger(ArtikalService.class);
	
	@EJB
	private ArtikalDaoLocal artikalDao;
	
	@GET 
	@Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
    public Artikal findById(@PathParam("id") String id) {
		Artikal retVal = new Artikal();
		try {
			
			retVal = artikalDao.findById(Long.parseLong(id));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }
}
