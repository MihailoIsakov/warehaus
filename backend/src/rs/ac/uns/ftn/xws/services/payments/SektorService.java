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

import model.Sektor;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.xws.util.Authenticate;
import daoBean.MagacinskaKarticaDaoLocal;
import daoBean.PrometniDokumentDaoLocal;
import daoBean.SektorDaoLocal;

@Path("/sektor")
public class SektorService {
	private static Logger log = Logger.getLogger(SektorService.class);
	
	@EJB
	private SektorDaoLocal sektorDao;
	@EJB
	private PrometniDokumentDaoLocal promDocDao;
	@EJB
	private MagacinskaKarticaDaoLocal magCardDao;
	 
	@GET 
    @Produces(MediaType.APPLICATION_JSON)
//	@Authenticate
    public List<Sektor> findAll() {
		List<Sektor> retVal = null;
		try {
			retVal = sektorDao.findAll();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }
	
	@GET 
	@Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
    public Sektor findById(@PathParam("id") String id) {
		Sektor retVal = null;
		try {
			retVal = sektorDao.findById(Integer.parseInt(id));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
    public Sektor create(Sektor entity) {
		
		Sektor retVal = null;
		try {
			
			retVal = sektorDao.persist(entity);
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
    public Sektor update(Sektor entity) {
    	log.info("PUT");
    	
    	Sektor retVal = null;
        try {
        	retVal = sektorDao.merge(entity);
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
    	Sektor p = sektorDao.findById(id);
    	try {
    		sektorDao.remove(p);
        } catch (Exception e) {
        	log.error(e.getMessage(), e);
        }
    	
    }
}
