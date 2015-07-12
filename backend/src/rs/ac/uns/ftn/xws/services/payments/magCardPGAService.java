package rs.ac.uns.ftn.xws.services.payments;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import model.MagacinskaKartica;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.xws.util.Authenticate;
import daoBean.MagacinskaKarticaDaoLocal;

@Path("/magacinska-karticaPGA")
public class magCardPGAService {
	
	private static Logger log = Logger.getLogger(magCardPGAService.class);
	
	@EJB
	private MagacinskaKarticaDaoLocal mkDao;
	
	@GET 
	@Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
    public MagacinskaKartica findGG(@PathParam("id") String id, @QueryParam("idMagacin") String idMagacin, @QueryParam("idPG") String idPG) {
		MagacinskaKartica retVal = new MagacinskaKartica();
		log.error(id+"  "+idMagacin+"  "+idPG+"  ");
		try {
			retVal = mkDao.findByMagaciniArtikaliPG((Integer.parseInt(idMagacin)), (Integer.parseInt(id)), (Integer.parseInt(idPG)));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }
	
}
