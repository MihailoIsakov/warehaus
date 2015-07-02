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

import model.Zaposleni;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.xws.util.Authenticate;
import daoBean.ZaposleniDao;

@Path("/zaposleni")
public class ZaposleniService {
	private static Logger log = Logger.getLogger(ZaposleniService.class);
	@EJB
	private ZaposleniDao zaposleniDao;
	 
	@GET 
    @Produces(MediaType.APPLICATION_JSON)
//	@Authenticate
    public List<Zaposleni> findAll() {
		List<Zaposleni> retVal = null;
		try {
			retVal = zaposleniDao.findAll();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }
	
	@GET 
	@Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
    public Zaposleni findById(@PathParam("id") String id) {
		Zaposleni retVal = null;
		try {
			retVal = zaposleniDao.findById(Long.getLong(id));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
    public Zaposleni create(Zaposleni entity) {
		log.info("POST");
		Zaposleni retVal = null;
		try {
			System.out.println("entity: "+entity);
			retVal = zaposleniDao.persist(entity);
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
    public Zaposleni update(Zaposleni entity) {
    	log.info("PUT");
    	Zaposleni retVal = null;
        try {
        	retVal = zaposleniDao.merge(entity);
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
    public void delete(Zaposleni entity) {
    	log.info("DELETE");
        try {
        	zaposleniDao.remove(entity);
        } catch (Exception e) {
			log.error(e.getMessage(), e);
		}
    }

}
