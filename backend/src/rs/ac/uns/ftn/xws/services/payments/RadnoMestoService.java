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

import model.RadnoMesto;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.xws.util.Authenticate;
import daoBean.RadnoMestoDao;

@Path("/radno-mesto")
public class RadnoMestoService {
	private static Logger log = Logger.getLogger(RadnoMestoService.class);
	@EJB
	private  RadnoMestoDao radnoMestoDao;
	 
	@GET 
    @Produces(MediaType.APPLICATION_JSON)
//	@Authenticate
    public List<RadnoMesto> findAll() {
		List<RadnoMesto> retVal = null;
		try {
			retVal = radnoMestoDao.findAll();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }
	
	@GET 
	@Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
    public RadnoMesto findById(@PathParam("id") String id) {
		RadnoMesto retVal = null;
		try {
			retVal = radnoMestoDao.findById(Long.getLong(id));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
    public RadnoMesto create(RadnoMesto entity) {
		log.info("POST");
		RadnoMesto retVal = null;
		try {
			System.out.println("entity: "+entity);
			retVal = radnoMestoDao.persist(entity);
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
    public RadnoMesto update(RadnoMesto entity) {
    	log.info("PUT");
    	RadnoMesto retVal = null;
        try {
        	retVal = radnoMestoDao.merge(entity);
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
    public void delete(RadnoMesto entity) {
    	log.info("DELETE");
        try {
        	radnoMestoDao.remove(entity);
        } catch (Exception e) {
			log.error(e.getMessage(), e);
		}
    }

}
