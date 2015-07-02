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
import model.Mesto;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.xws.util.Authenticate;
import daoBean.MestoDaoLocal;

@Path("/mesta")
public class MestaService {
	private static Logger log = Logger.getLogger(MestaService.class);
	
	@EJB
	private MestoDaoLocal mestaDao;
	 
	@GET 
    @Produces(MediaType.APPLICATION_JSON)
//	@Authenticate
    public List<Mesto> findAll() {
		List<Mesto> retVal = null;
		try {
			retVal = mestaDao.findAll();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }
	
	@GET 
	@Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
    public Mesto findById(@PathParam("id") String id) {
		Mesto retVal = null;
		try {
			retVal = mestaDao.findById(Integer.parseInt(id));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
    public Mesto create(Mesto entity) {
		
		Mesto retVal = null;
		try {
			
			retVal = mestaDao.persist(entity);
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
    public Mesto update(Mesto entity) {
    	log.info("PUT");
    	Mesto retVal = null;
        try {
        	retVal = mestaDao.merge(entity);
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
    		mestaDao.remove(id);
        } catch (Exception e) {
        	log.error(e.getMessage(), e);
        }
    	
    }
}
