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
			retVal = artikalDao.findById(Integer.parseInt(id));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }
	
	@GET 
    @Produces(MediaType.APPLICATION_JSON)
//	@Authenticate
    public List<Artikal> findAll() {
		List<Artikal> retVal = null;
		try {
			retVal = artikalDao.findAll();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
    public Artikal create(Artikal entity) {
		log.info("POST");
		Artikal retVal = null;
		try {
			System.out.println("entity: "+entity);
			retVal = artikalDao.persist(entity);
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
    public Artikal update(Artikal entity) {
    	log.info("PUT");
    	Artikal retVal = null;
        try {
        	retVal = artikalDao.merge(entity);
        } catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }
    
    @DELETE 
    @Path("{id}")
	@Authenticate
    public void delete(@PathParam("id") Integer id) {
    	Artikal p = artikalDao.findById(id);
    	log.info("DELETE");
        try {
        	artikalDao.remove(p);
        } catch (Exception e) {
			log.error(e.getMessage(), e);
		}
    }
}
