package rs.ac.uns.ftn.xws.services.payments;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.MagacinskaKartica;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.xws.util.Authenticate;
import daoBean.MagacinskaKarticaDaoLocal;

@Path("/magacinska-kartica")
public class MagacinskaKarticaService {
	private static Logger log = Logger.getLogger(MagacinskaKarticaService.class);
	
	@EJB
	private MagacinskaKarticaDaoLocal mkDao;
	
	@GET 
	@Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
    public MagacinskaKartica findByMagCardId(@PathParam("id") String id) {
		MagacinskaKartica retVal = new MagacinskaKartica();
		try {
			retVal = mkDao.findByMagCardId(Integer.parseInt(id));
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
    	log.info("PUT Mag Kartica");
    	MagacinskaKartica retVal = null;
        try {
        	retVal = mkDao.merge(entity);
        } catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
     }
}
