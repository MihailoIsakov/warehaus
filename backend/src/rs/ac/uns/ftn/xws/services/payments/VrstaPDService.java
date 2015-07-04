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

import model.VrstaDokumenta;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.xws.util.Authenticate;
import daoBean.VrstaDokumentaDaoLocal;

@Path("/vrstaPD")
public class VrstaPDService {

	private static Logger log = Logger.getLogger(DrzaveService.class);
	
	@EJB
	private VrstaDokumentaDaoLocal vrstaDao;
	 
	@GET 
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
    public List<VrstaDokumenta> findAll() {
		List<VrstaDokumenta> retVal = null;
		try {
			retVal = vrstaDao.findAll();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }
	
	@GET 
	@Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
    public VrstaDokumenta findById(@PathParam("id") String id) {
		VrstaDokumenta retVal = null;
		try {
			retVal = vrstaDao.findById(Integer.parseInt(id));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
    public VrstaDokumenta create(VrstaDokumenta entity) {
		log.info("POST");
		VrstaDokumenta retVal = null;
		try {
			
			retVal = vrstaDao.persist(entity);
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
    public VrstaDokumenta update(VrstaDokumenta entity) {
    	log.info("PUT");
    	VrstaDokumenta retVal = null;
        try {
        	retVal = vrstaDao.merge(entity);
        } catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }
    
    @DELETE 
    @Path("{id}")
	@Authenticate
	public void remove(@PathParam("id") Integer id) {
    	VrstaDokumenta p = vrstaDao.findById(id);
    	try {
    		vrstaDao.remove(p);
        } catch (Exception e) {
        	log.error(e.getMessage(), e);
        }
    	
    }
}
