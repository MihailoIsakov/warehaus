package rs.ac.uns.ftn.xws.services.payments;

import java.math.BigDecimal;
import java.util.Date;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.AnalitikaMagacinskeKartice;
import model.MagacinskaKartica;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.xws.util.Authenticate;
import daoBean.AnalitikaMagacinskeKarticeDaoLocal;
import daoBean.MagacinskaKarticaDaoLocal;

@Path("/magacinska-kartica")
public class MagacinskaKarticaService {
	private static Logger log = Logger.getLogger(MagacinskaKarticaService.class);
	
	@EJB
	private MagacinskaKarticaDaoLocal mkDao;
	
	@EJB
	private AnalitikaMagacinskeKarticeDaoLocal anDao;
	
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
	
	 @SuppressWarnings("static-access")
	@PUT
     @Path("{id}")
     @Consumes(MediaType.APPLICATION_JSON)
     @Produces(MediaType.APPLICATION_JSON)
	 @Authenticate
     public MagacinskaKartica update(MagacinskaKartica entity) {
    	log.info("PUT Mag Kartica");
    	MagacinskaKartica retVal = null;
    	MagacinskaKartica before = mkDao.findByMagCardId(entity.getIdMagacinskaKartica());
    	//STARA KOLICINA UKUPNO articled.pocetnoStanjeKol+articled.kolUlaza-articled.kolIzlaza
    	BigDecimal quantity = before.getPocetnoStanjeKol().add(before.getKolUlaza());
    	quantity = quantity.subtract(before.getKolIzlaza());
    	//NOVA KOLICINA UKUPNO
    	BigDecimal newQuantity = entity.getPocetnoStanjeKol().add(entity.getKolUlaza());
    	newQuantity = newQuantity.subtract(entity.getKolIzlaza());
    	//KOLICINA NA ANALITICI
    	BigDecimal diff = quantity.subtract(newQuantity).abs();
        try {
        	retVal = mkDao.merge(entity);
        	AnalitikaMagacinskeKartice a = new AnalitikaMagacinskeKartice();
        	a.setCena(entity.getProsecnaCena());
        	a.setDatumPromene(new Date());
        	a.setKolicina(diff);
        	a.setMagacinskaKartica(entity);
        	//a.setRedniBroj(redniBroj);
        	//a.setSifraDokumenta(sifraDokumenta);
        	if(quantity.compareTo(newQuantity) == -1){
        		//Nova je veca, povecava se kolUlaz
            	a.setSmer(a.getSmer().U);
        	}else {
        		//Stara je veca, povecava se kolIzlaz
            	a.setSmer(a.getSmer().I);
        	}
        	a.setStavkaPrometnogDokumenta(null);
        	//VREDNOST articled.pocetnoStanjeVr+articled.vrUlaza-articled.vrIzlaza
        	BigDecimal value = entity.getPocetnoStanjeVr().add(entity.getVrUlaza());
        	value = value.subtract(entity.getVrIzlaza());
        	a.setVrednost(value);
        	anDao.persist(a);
        } catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
     }
}
