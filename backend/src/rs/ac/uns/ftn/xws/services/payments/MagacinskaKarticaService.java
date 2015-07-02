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

import model.MagacinskaKartica;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.xws.util.Authenticate;
import daoBean.MagacinskaKarticaDao;

@Path("/magacinska-kartica")
public class MagacinskaKarticaService {
	private static Logger log = Logger.getLogger(MagacinskaKarticaService.class);
	
	@EJB
	private MagacinskaKarticaDao magacinskaKarticaDao;
	
	@GET 
    @Produces(MediaType.APPLICATION_JSON)
//	@Authenticate
    public List<MagacinskaKartica> findAll() {
		List<MagacinskaKartica> retVal = null;
		try {
			retVal = magacinskaKarticaDao.findAll();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }
	
	@GET 
	@Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
    public MagacinskaKartica findById(@PathParam("id") String id) {
		MagacinskaKartica retVal = null;
		try {
			retVal = magacinskaKarticaDao.findById(Long.getLong(id));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
    public MagacinskaKartica create(MagacinskaKartica entity) {
		log.info("POST");
		MagacinskaKartica retVal = null;
		try {
			System.out.println("entity: "+entity);
			retVal = magacinskaKarticaDao.persist(entity);
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
    public MagacinskaKartica update(MagacinskaKartica entity) {
    	log.info("PUT");
    	MagacinskaKartica retVal = null;
        try {
        	retVal = magacinskaKarticaDao.merge(entity);
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
    public void delete(MagacinskaKartica entity) {
    	log.info("DELETE");
        try {
        	magacinskaKarticaDao.remove(entity);
        } catch (Exception e) {
			log.error(e.getMessage(), e);
		}
    }
}
