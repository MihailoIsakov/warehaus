package rs.ac.uns.ftn.xws.services.payments;

import java.util.ArrayList;
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

import model.Artikal;
import model.PrometniDokument;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.xws.util.Authenticate;
import daoBean.MagacinDaoLocal;
import daoBean.PrometniDokumentDao;

@Path("/primka")
public class PrimkaService {
	private static Logger log = Logger.getLogger(PrimkaService.class);
	
	@EJB
	private MagacinDaoLocal magacinDao;
	
	@EJB
	private PrometniDokumentDao dokumentDao;
	 
	@GET 
	@Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
    public PrometniDokument findById(@PathParam("id") String id) {
		PrometniDokument retVal = null;
		try {
			retVal = dokumentDao.findById(Integer.parseInt(id));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
    public PrometniDokument create(PrometniDokument entity) {
		log.info("POST");
		PrometniDokument retVal = null;
		try {
			System.out.println("entity: "+entity);
			retVal = dokumentDao.persist(entity);
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
    public PrometniDokument update(PrometniDokument entity) {
    	log.info("PUT");
    	PrometniDokument retVal = null;
        try {
        	retVal = dokumentDao.merge(entity);
        } catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }
}
