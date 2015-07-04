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

import model.StavkaPrometnogDokumenta;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.xws.util.Authenticate;
import daoBean.StavkaPrometnogDokumentaDaoLocal;

@Path("/stavkaPD")
public class StavkaPDService {

	private static Logger log = Logger.getLogger(DrzaveService.class);
	
	@EJB
	private StavkaPrometnogDokumentaDaoLocal stavkaDao;
	 
	@GET 
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
    public List<StavkaPrometnogDokumenta> findAll() {
		List<StavkaPrometnogDokumenta> retVal = null;
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
    public StavkaPrometnogDokumenta findById(@PathParam("id") String id) {
		StavkaPrometnogDokumenta retVal = null;
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
    public StavkaPrometnogDokumenta create(StavkaPrometnogDokumenta entity) {
		log.info("POST");
		StavkaPrometnogDokumenta retVal = null;
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
    public StavkaPrometnogDokumenta update(StavkaPrometnogDokumenta entity) {
    	log.info("PUT");
    	StavkaPrometnogDokumenta retVal = null;
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
    	StavkaPrometnogDokumenta p = stavkaDao.findById(id);
    	try {
    		stavkaDao.remove(p);
        } catch (Exception e) {
        	log.error(e.getMessage(), e);
        }
    	
    }
}
