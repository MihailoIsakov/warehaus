package rs.ac.uns.ftn.xws.services.payments;

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
import javax.ws.rs.core.Response;

import model.PrometniDokument;
import model.StavkaPrometnogDokumenta;
import model.PrometniDokument.statusDokumenta;
import model.VrstaDokumenta;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.xws.entities.payments.Invoice;
import rs.ac.uns.ftn.xws.util.Authenticate;

import com.sun.xml.internal.stream.Entity;

import daoBean.PrometniDokumentDaoLocal;

@Path("/prometni-dokumenti")
public class PromDocService {

	private static Logger log = Logger.getLogger(Invoice.class);

	@EJB
	private PrometniDokumentDaoLocal promDocDao;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Authenticate
	public List<PrometniDokument> findByAll() {
		List<PrometniDokument> retVal = new ArrayList<PrometniDokument>();
		// Magacin a = new Magacin();

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
	public PrometniDokument create(PrometniDokument entity) throws Exception {
		entity = promDocDao.findById(entity.getIdPrometniDokument());
		if (entity.getStatusDokumenta().equals(statusDokumenta.proknjizen)) {
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

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Authenticate
	public PrometniDokument update(PrometniDokument entity) {
		log.info("PUT");

		PrometniDokument retVal = null;
		try {
			retVal = promDocDao.merge(entity);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
	}

}
