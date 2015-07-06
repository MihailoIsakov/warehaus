package rs.ac.uns.ftn.xws.services.payments;



import java.math.BigDecimal;
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
import model.Magacin;
import model.MagacinskaKartica;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.xws.entities.payments.Invoice;
import rs.ac.uns.ftn.xws.util.Authenticate;
import daoBean.ArtikalDaoLocal;
import daoBean.MagacinDaoLocal;
import daoBean.MagacinskaKarticaDaoLocal;

@Path("/lager-list")
public class LagerListService {

	private static Logger log = Logger.getLogger(Invoice.class);

	@EJB
	private ArtikalDaoLocal artikalDao;
	@EJB
	private MagacinDaoLocal magacinDao;
	@EJB
	private MagacinskaKarticaDaoLocal mkDao;

	@GET 
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
	public List<Magacin> findByAll() {
		List<Magacin> retVal = new ArrayList<Magacin>();
		Magacin a = new Magacin();
		
		
		try {
			log.error("usao");
		
		retVal =  magacinDao.findAll();
	//	Gson gson = new Gson();
	///	String rez = gson.toJson(retVal);
	//	log.error(rez);
	//	retVal = gson.fromJson(rez,retVal.getClass());
		log.error("size1"+retVal.size());
		log.error("id"+retVal.get(0).getIdMagacin());
		//log.error("size2"+retVal.get(0).getMagacinskaKarticas().size());
			} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }

	@GET 
	@Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
    public List<Object> findById(@PathParam("id") String id) {
		List<Object> retVal = new ArrayList<Object>();
		try {
			
			retVal = mkDao.findByMagacin(Integer.parseInt(id));
			log.error(retVal.size());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }

	@POST
	@Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Authenticate
    public void nivelisi(@PathParam("id") String id) {
		log.info("POST");
		try {
			System.out.println("IDDDD: "+id);
			List<Object> ret = mkDao.findByMagacin(Integer.parseInt(id));
			for(Object o: ret){
				BigDecimal kol = ((MagacinskaKartica) o).getPocetnoStanjeKol().add(((MagacinskaKartica) o).getKolUlaza()).
						subtract(((MagacinskaKartica) o).getKolIzlaza());
				BigDecimal beforeVal = kol.multiply(((MagacinskaKartica) o).getProsecnaCena());
				BigDecimal val = ((MagacinskaKartica) o).getPocetnoStanjeVr().add(((MagacinskaKartica) o).getVrUlaza()).
						subtract(((MagacinskaKartica) o).getVrIzlaza());
				if(!beforeVal.equals(val)){
					BigDecimal niv = beforeVal.subtract(val);
					if(niv.compareTo(BigDecimal.valueOf(0.0)) == 1) {
						//povecavamo izlaz
						((MagacinskaKartica) o).setVrIzlaza(((MagacinskaKartica) o).getVrIzlaza().subtract(niv));
					}
					else {
						//povecavamo ulaz
						((MagacinskaKartica) o).setVrUlaza(((MagacinskaKartica) o).getVrUlaza().add(niv));
					}
					mkDao.merge(((MagacinskaKartica) o));
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
    }
    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
    public Artikal update(Artikal entity) {
    	log.info("PUT");
    	Artikal a = new Artikal();
    	Artikal retVal = null;
        try {
        	retVal = artikalDao.merge(entity);
        } catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }

    
}
