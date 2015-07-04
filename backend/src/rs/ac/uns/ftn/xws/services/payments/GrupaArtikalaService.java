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
import daoBean.GrupaArtikalaDaoLocal;

@Path("/grupa-artikala")
public class GrupaArtikalaService {
	private static Logger log = Logger.getLogger(GrupaArtikalaService.class);
	@EJB
	private  GrupaArtikalaDaoLocal grupaArtikalaDao;
	 
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
			retVal = grupaArtikalaDao.findById(Integer.parseInt(id));
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
	@Authenticate
    public void delete(@PathParam("id") Integer id) {
    	log.info("DELETE");
    	GrupaArtikala p = grupaArtikalaDao.findById(id);
        try {
        	grupaArtikalaDao.remove(p);
        } catch (Exception e) {
			log.error(e.getMessage(), e);
		}
    }
}
