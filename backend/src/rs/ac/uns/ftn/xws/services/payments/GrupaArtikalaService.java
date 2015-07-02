package rs.ac.uns.ftn.xws.services.payments;

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

import model.GrupaArtikala;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.xws.util.Authenticate;
import daoBean.GrupaArtikalaDao;

@Path("/grupa-artikala")
public class GrupaArtikalaService {
	private static Logger log = Logger.getLogger(GrupaArtikalaService.class);
	@EJB
	private  GrupaArtikalaDao grupaArtikalaDao;
	 
	@GET 
    @Produces(MediaType.APPLICATION_JSON)
//	@Authenticate
    public List<GrupaArtikala> findAll() {
		List<GrupaArtikala> retVal = null;
		try {
			retVal = grupaArtikalaDao.findAll();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }
	
	@GET 
	@Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
    public GrupaArtikala findById(@PathParam("id") String id) {
		GrupaArtikala retVal = null;
		try {
			retVal = grupaArtikalaDao.findById(Long.getLong(id));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
    public GrupaArtikala create(GrupaArtikala entity) {
		log.info("POST");
		GrupaArtikala retVal = null;
		try {
			System.out.println("entity: "+entity);
			retVal = grupaArtikalaDao.persist(entity);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }
    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
    public GrupaArtikala update(GrupaArtikala entity) {
    	log.info("PUT");
    	GrupaArtikala retVal = null;
        try {
        	retVal = grupaArtikalaDao.merge(entity);
        } catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }
    
    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
    public void delete(GrupaArtikala entity) {
    	log.info("DELETE");
        try {
        	grupaArtikalaDao.remove(entity);
        } catch (Exception e) {
			log.error(e.getMessage(), e);
		}
    }
}
