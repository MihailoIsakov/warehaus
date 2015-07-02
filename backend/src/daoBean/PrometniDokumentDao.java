package daoBean;

import java.util.Iterator;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import model.AnalitikaMagacinskeKartice;
import model.AnalitikaMagacinskeKartice.smer;
import model.MagacinskaKartica;
import model.PrometniDokument;
import model.StavkaPrometnogDokumenta;
import rs.ac.uns.ftn.xws.entities.payments.Invoice;
import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDaoBean;

@Stateless
@Local(PrometniDokumentDaoLocal.class)
public class PrometniDokumentDao extends
		GenericDaoBean<PrometniDokument, Integer> implements
		PrometniDokumentDaoLocal {
	private static Logger log = Logger.getLogger(Invoice.class);


	@EJB
	StavkaPrometnogDokumentaDaoLocal spdDao;

	@EJB
	MagacinskaKarticaDaoLocal mkDao;


	@Override
	public PrometniDokument persistSaKreiranjemStavki(PrometniDokument entity) throws NoSuchFieldException {
		PrometniDokument retVal = null;
		try {
			retVal = persist(entity);

			Iterator<StavkaPrometnogDokumenta> stavke = entity.getStavke()
					.iterator();
			while (stavke.hasNext()) {
				StavkaPrometnogDokumenta stavkaPrometnogDokumenta = (StavkaPrometnogDokumenta) stavke
						.next();
				stavkaPrometnogDokumenta.setPrometniDokument(retVal);
				stavkaPrometnogDokumenta.setIdStavkaPrometnogDokumenta(0);
				spdDao.persist(stavkaPrometnogDokumenta);
			}
		} catch (NoSuchFieldException e) {
			throw e;
		}
		return retVal;
	}
	@Override
	public PrometniDokument proknjiziDokument(PrometniDokument entity) throws Exception {
		// reci da je proknjizen
		// izmeni magacinsku karticu za oba magacina dodaj karticu ako ne
		// postoji kartica za taj artikal u tom magacinu
		// dodaj novu analitiku magacinske kartice za oba magacina sa promenom
		// koja se desila nad magacinskim karticama oba magacina
			try {
			if (entity.getMagacin1() != null) {
			
		
				mkDao.update(entity, entity.getMagacin1(),1);
			
		}
			if(entity.getMagacin2() != null){
			mkDao.update(entity, entity.getMagacin2(),2);
		}
		} catch (Exception e) {
			throw e;
		}
		return null;
	}

}
