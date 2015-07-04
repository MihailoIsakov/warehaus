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

import model.JedinicaMere;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.xws.util.Authenticate;
import daoBean.JedinicaMereDaoLocal;

@Path("/jedinica-mere")
public class JedinicaMereService {
	private static Logger log = Logger.getLogger(JedinicaMereService.class);
	@EJB
	private JedinicaMereDaoLocal jedinicaDao;
	 
	@GET 
    @Produces(MediaType.APPLICATION_JSON)
//	@Authenticate
    public List<JedinicaMere> findAll() {
		List<JedinicaMere> retVal = null;
		try {
			retVal = jedinicaDao.findAll();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }
	
	@GET 
	@Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
    public JedinicaMere findById(@PathParam("id") String id) {
		JedinicaMere retVal = null;
		try {
			retVal = jedinicaDao.findById(Integer.parseInt(id));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
    public JedinicaMere create(JedinicaMere entity) {
		log.info("POST");
		JedinicaMere retVal = null;
		try {
			System.out.println("entity: "+entity);
			retVal = jedinicaDao.persist(entity);
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
    public JedinicaMere update(JedinicaMere entity) {
    	log.info("PUT");
    	JedinicaMere retVal = null;
        try {
        	retVal = jedinicaDao.merge(entity);
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
    	JedinicaMere p = jedinicaDao.findById(id);
        try {
        	jedinicaDao.remove(p);
        } catch (Exception e) {
			log.error(e.getMessage(), e);
		}
    }
}
