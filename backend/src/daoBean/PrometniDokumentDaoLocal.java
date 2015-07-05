package daoBean;

import model.PrometniDokument;
import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDao;

public interface PrometniDokumentDaoLocal extends GenericDao<PrometniDokument, Integer>{

	PrometniDokument persistSaKreiranjemStavki(PrometniDokument entity) throws NoSuchFieldException;

	PrometniDokument proknjiziDokument(PrometniDokument entity) throws Exception;

	PrometniDokument persistSaKreiranjemStavkiDodavanjeBroja(
			PrometniDokument entity) throws NoSuchFieldException;
	
	boolean proveriPrometniDokument(PrometniDokument entity);
	
	public int findMaxRB();
}
