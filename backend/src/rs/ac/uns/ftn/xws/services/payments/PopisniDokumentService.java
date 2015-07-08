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

import model.Artikal;
import model.PopisniDokument;
import model.PopisniDokument.statusPredaje;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.xws.util.Authenticate;
import daoBean.PopisniDokumentDaoLocal;

@Path("/popisni-dokumenti")
public class PopisniDokumentService {
	
	private static Logger log = Logger.getLogger(PopisniDokumentService.class);
	
	@EJB
	private PopisniDokumentDaoLocal popDocDao;
	
	@GET 
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
    public List<PopisniDokument> findAll() {
		List<PopisniDokument> retVal = null;
		try {
			retVal = popDocDao.findAll();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }
	
	@GET 
	@Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
    public PopisniDokument findById(@PathParam("id") String id) {
		PopisniDokument retVal = new PopisniDokument();
		try {
			retVal = popDocDao.findById(Integer.parseInt(id));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Authenticate
	public PopisniDokument create(PopisniDokument entity) throws Exception {
		log.error("Entered create popisni document. ID: "+entity.getIdPopisniDokument());
		entity.setStatusPredaje(statusPredaje.neproknjizen);
		PopisniDokument retVal = null;
		try{
			retVal = popDocDao.persistSaStavkama(entity);
		}catch(Exception e){
			e.printStackTrace();
		}
		return retVal;
	}
	
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
    public PopisniDokument update(PopisniDokument entity) {
    	log.info("PUT");
    	PopisniDokument retVal = null;
        try {
        	retVal = popDocDao.merge(entity);
        } catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }
}
