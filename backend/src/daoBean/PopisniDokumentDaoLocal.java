package daoBean;

import model.PopisniDokument;
import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDao;

public interface PopisniDokumentDaoLocal extends GenericDao<PopisniDokument, Integer>{
	public PopisniDokument persistSaStavkama(PopisniDokument p);
}
