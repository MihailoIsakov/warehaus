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

import model.Drzava;
import model.Preduzece;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.xws.util.Authenticate;
import daoBean.PreduzeceDaoLocal;

@Path("/preduzeca")
public class PreduzecaService {
	private static Logger log = Logger.getLogger(PreduzecaService.class);
	
	@EJB
	private PreduzeceDaoLocal preduzecaDao;
	 
	@GET 
    @Produces(MediaType.APPLICATION_JSON)
//	@Authenticate
    public List<Preduzece> findAll() {
		List<Preduzece> retVal = null;
		try {
			retVal = preduzecaDao.findAll();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }
	
	@GET 
	@Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
    public Preduzece findById(@PathParam("id") String id) {
		Preduzece retVal = null;
		try {
			retVal = preduzecaDao.findById(Integer.parseInt(id));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
    public Preduzece create(Preduzece entity) {
		
		Preduzece retVal = null;
		try {
			
			retVal = preduzecaDao.persist(entity);
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
    public Preduzece update(Preduzece entity) {
    	log.info("PUT");
    	Preduzece retVal = null;
        try {
        	retVal = preduzecaDao.merge(entity);
        } catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }
    
    @DELETE 
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
	public void removeItem(@PathParam("id") Integer id) {
    	try {
    		preduzecaDao.remove(id);
        } catch (Exception e) {
        	log.error(e.getMessage(), e);
        }
    	
    }
}
