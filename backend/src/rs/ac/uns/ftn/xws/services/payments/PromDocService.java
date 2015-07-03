package rs.ac.uns.ftn.xws.services.payments;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
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

import model.MagacinskaKartica;
import model.PrometniDokument;
import model.PrometniDokument.statusDokumenta;
import model.StavkaPrometnogDokumenta;
import model.VrstaDokumenta;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.xws.entities.payments.Invoice;
import rs.ac.uns.ftn.xws.util.Authenticate;
import daoBean.MagacinskaKarticaDaoLocal;
import daoBean.PrometniDokumentDaoLocal;
import daoBean.StavkaPrometnogDokumentaDaoLocal;
import daoBean.VrstaDokumentaDaoLocal;

@Path("/prometni-dokumenti")
public class PromDocService {

	private static Logger log = Logger.getLogger(Invoice.class);

	@EJB
	private PrometniDokumentDaoLocal promDocDao;

	@EJB
	private StavkaPrometnogDokumentaDaoLocal stavkaDao;
	@EJB
	private MagacinskaKarticaDaoLocal magCardDao;
	@EJB
	private VrstaDokumentaDaoLocal vrstaDao;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Authenticate
	public List<PrometniDokument> findByAll() {
		List<PrometniDokument> retVal = new ArrayList<PrometniDokument>();

		try {
			log.error("usao");

			retVal = promDocDao.findAll();
			// Gson gson = new Gson();
			// / String rez = gson.toJson(retVal);
			// log.error(rez);
			// retVal = gson.fromJson(rez,retVal.getClass());
			// log.error("size1"+retVal.size());
			// log.error("id"+retVal.get(0).getIdMagacin());
			// log.error("size2"+retVal.get(0).getMagacinskaKarticas().size());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Authenticate
	public PrometniDokument findById(@PathParam("id") String id) {
		PrometniDokument retVal = null;
		try {

			retVal = promDocDao.findById(Integer.parseInt(id));
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
		return null;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Authenticate
	public PrometniDokument createNew(PrometniDokument entity) throws Exception {
		if (entity.getStatusDokumenta().equals(statusDokumenta.proknjizen)) {
			Iterator<StavkaPrometnogDokumenta> stavke = entity.getStavke()
					.iterator();
			while (stavke.hasNext()) {
				StavkaPrometnogDokumenta stavkaPrometnogDokumenta = (StavkaPrometnogDokumenta) stavke
						.next();
				stavkaDao.persist(stavkaPrometnogDokumenta);
				
			}
			PrometniDokument retVal = null;
			try {
				retVal = promDocDao.persistSaKreiranjemStavki(entity);
				promDocDao.proknjiziDokument(retVal);
			} catch (Exception e) {
				throw e;

			}

			return retVal;
		}
		return null;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Authenticate
	public PrometniDokument update(PrometniDokument entity) throws Exception {
		entity = promDocDao.findById(entity.getIdPrometniDokument());
		if (entity.getStatusDokumenta().equals(statusDokumenta.proknjizen)&& !entity.getPoslovnaGodina().getZakljucenaGodina()) {
			entity.setIdPrometniDokument(0);
			Iterator<StavkaPrometnogDokumenta> stavke = entity.getStavke()
					.iterator();
			while (stavke.hasNext()) {
				StavkaPrometnogDokumenta stavkaPrometnogDokumenta = (StavkaPrometnogDokumenta) stavke
						.next();
				stavkaPrometnogDokumenta.setKolicinaPrDokumenta(stavkaPrometnogDokumenta.getKolicinaPrDokumenta().negate());
			}
			PrometniDokument retVal = null;
			try {
				retVal = promDocDao.persistSaKreiranjemStavki(entity);
				promDocDao.proknjiziDokument(retVal);
			} catch (Exception e) {
				throw e;

			}

			return retVal;
		}
		return null;
	}
	
	public boolean updateStanjaMagacina(PrometniDokument p){
		VrstaDokumenta primka = vrstaDao.findByName("primka");
		if(p.getVrstaDokumenta().getIdVrstaDokumenta() == primka.getIdVrstaDokumenta()){
			for(StavkaPrometnogDokumenta sp: p.getStavke()){
				MagacinskaKartica m = magCardDao.findByArtikalId(sp.getArtikal().getIdArtikal());
				if(m == null)
					return false;
				BigDecimal ukupnaVr = m.getPocetnoStanjeVr().add(m.getVrUlaza()).subtract(m.getVrIzlaza());
				BigDecimal ukupnaKol = m.getPocetnoStanjeKol().add(m.getKolUlaza()).subtract(m.getKolIzlaza());
				BigDecimal cena = ukupnaVr.add(sp.getCenaStavke().multiply(sp.getKolicinaPrDokumenta()));
				cena = cena.divide(sp.getKolicinaPrDokumenta().add(ukupnaKol));
				m.setProsecnaCena(cena);
				m.setKolUlaza(m.getKolUlaza().add(sp.getKolicinaPrDokumenta()));
				m.setVrUlaza(m.getVrUlaza().add(sp.getVrednostStavke()));
				try {
					magCardDao.merge(m);
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
					return false;
				}
			}
			return true;
		}
		VrstaDokumenta ot = vrstaDao.findByName("otpremnica");
		if(p.getVrstaDokumenta().getIdVrstaDokumenta() == ot.getIdVrstaDokumenta()){
			for(StavkaPrometnogDokumenta sp: p.getStavke()){
				MagacinskaKartica m = magCardDao.findByArtikalId(sp.getArtikal().getIdArtikal());
				if(m == null)
					return false;
				BigDecimal ukupnaVr = m.getPocetnoStanjeVr().add(m.getVrUlaza()).subtract(m.getVrIzlaza());
				BigDecimal ukupnaKol = m.getPocetnoStanjeKol().add(m.getKolUlaza()).subtract(m.getKolIzlaza());
				BigDecimal cena = ukupnaVr.add(sp.getCenaStavke().multiply(sp.getKolicinaPrDokumenta()));
				cena = cena.divide(sp.getKolicinaPrDokumenta().add(ukupnaKol));
				m.setProsecnaCena(cena);
				m.setKolIzlaza(m.getKolIzlaza().add(sp.getKolicinaPrDokumenta()));
				m.setVrIzlaza(m.getVrIzlaza().add(sp.getVrednostStavke()));
				try {
					magCardDao.merge(m);
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
					return false;
				}
			}
			return true;
		}
		return false;
	}

}
