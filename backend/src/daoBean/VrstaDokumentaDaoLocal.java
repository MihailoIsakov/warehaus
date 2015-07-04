package daoBean;

import model.VrstaDokumenta;
import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDao;

public interface VrstaDokumentaDaoLocal extends GenericDao<VrstaDokumenta, Integer>{
	public VrstaDokumenta findByName(String name);
}
