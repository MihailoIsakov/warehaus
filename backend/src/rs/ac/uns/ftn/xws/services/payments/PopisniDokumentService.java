package rs.ac.uns.ftn.xws.services.payments;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.PopisniDokument;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.xws.util.Authenticate;
import daoBean.PopisniDokumentDaoLocal;

@Path("/popisni-dokumenti")
public class PopisniDokumentService {
	
	private static Logger log = Logger.getLogger(PopisniDokumentService.class);
	
	@EJB
	private PopisniDokumentDaoLocal popDocDao;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Authenticate
	public PopisniDokument create(PopisniDokument entity) throws Exception {
		log.error("Entered create popisni document. ID: "+entity.getIdPopisniDokument());
		PopisniDokument retVal = null;
		try{
			retVal = popDocDao.persistSaStavkama(entity);
		}catch(Exception e){
			e.printStackTrace();
		}
		return retVal;
	}
}
