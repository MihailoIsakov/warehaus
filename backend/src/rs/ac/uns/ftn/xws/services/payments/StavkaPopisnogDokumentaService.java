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

import model.StavkaPopisa;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.xws.util.Authenticate;
import daoBean.StavkaPopisaDaoLocal;

@Path("/stavkaPopisnogDokumenta")
public class StavkaPopisnogDokumentaService {

	private static Logger log = Logger.getLogger(DrzaveService.class);
	
	@EJB
	private StavkaPopisaDaoLocal stavkaDao;
	 
	@GET 
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
    public List<StavkaPopisa> findAll() {
		List<StavkaPopisa> retVal = null;
		try {
			retVal = stavkaDao.findAll();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }
	
	@GET 
	@Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
    public StavkaPopisa findById(@PathParam("id") String id) {
		StavkaPopisa retVal = null;
		try {
			retVal = stavkaDao.findById(Integer.parseInt(id));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
    public StavkaPopisa create(StavkaPopisa entity) {
		log.info("POST");
		StavkaPopisa retVal = null;
		try {
			
			retVal = stavkaDao.persist(entity);
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
    public StavkaPopisa update(StavkaPopisa entity) {
    	log.info("PUT");
    	StavkaPopisa retVal = null;
        try {
        	retVal = stavkaDao.merge(entity);
        } catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }
    
    @DELETE 
    @Path("{id}")
	@Authenticate
	public void remove(@PathParam("id") Integer id) {
    	StavkaPopisa p = stavkaDao.findById(id);
    	try {
    		stavkaDao.remove(p);
        } catch (Exception e) {
        	log.error(e.getMessage(), e);
        }
    	
    }
}
