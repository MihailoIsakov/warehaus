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

import model.Magacin;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.xws.util.Authenticate;
import daoBean.MagacinDao;
import daoBean.MagacinDaoLocal;

@Path("/magacin")
public class MagacinService {
private static Logger log = Logger.getLogger(MagacinService.class);
	
	@EJB
	private MagacinDaoLocal magacinDao;
	 
	@GET 
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
    public List<Magacin> findAll() {
		List<Magacin> retVal = null;
		try {
			retVal = magacinDao.findAll();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }
	
	@GET 
	@Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
    public Magacin findById(@PathParam("id") String id) {
		Magacin retVal = null;
		try {
			retVal = magacinDao.findById(Long.getLong(id));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
    public Magacin create(Magacin entity) {
		log.info("POST");
		Magacin retVal = null;
		try {
			System.out.println("entity: "+entity);
			retVal = magacinDao.persist(entity);
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
    public Magacin update(Magacin entity) {
    	log.info("PUT");
    	Magacin retVal = null;
        try {
        	retVal = magacinDao.merge(entity);
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
    public void delete(Magacin entity) {
    	log.info("DELETE");
        try {
        	magacinDao.remove(entity);
        } catch (Exception e) {
			log.error(e.getMessage(), e);
		}
    }
}
