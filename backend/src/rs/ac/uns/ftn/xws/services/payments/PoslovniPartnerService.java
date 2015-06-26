package rs.ac.uns.ftn.xws.services.payments;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.PoslovniPartner;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.xws.util.Authenticate;
import daoBean.PoslovniPartnerDao;

@Path("/poslovni-partner")
public class PoslovniPartnerService {
	private static Logger log = Logger.getLogger(PoslovniPartnerService.class);
	
	@EJB
	private PoslovniPartnerDao partnerDao;
	 
	@GET 
    @Produces(MediaType.APPLICATION_JSON)
//	@Authenticate
    public List<PoslovniPartner> findAll() {
		List<PoslovniPartner> retVal = null;
		try {
			retVal = partnerDao.findAll();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }
	
	@GET 
	@Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
    public PoslovniPartner findById(@PathParam("id") String id) {
		PoslovniPartner retVal = null;
		try {
			retVal = partnerDao.findById(Long.getLong(id));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
    public PoslovniPartner create(PoslovniPartner entity) {
		log.info("POST");
		PoslovniPartner retVal = null;
		try {
			System.out.println("entity: "+entity);
			retVal = partnerDao.persist(entity);
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
    public PoslovniPartner update(PoslovniPartner entity) {
    	log.info("PUT");
    	PoslovniPartner retVal = null;
        try {
        	retVal = partnerDao.merge(entity);
        } catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }
}
