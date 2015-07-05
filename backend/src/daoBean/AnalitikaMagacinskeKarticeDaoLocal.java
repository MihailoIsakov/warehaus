package daoBean;

import java.util.List;

import model.AnalitikaMagacinskeKartice;
import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDao;

public interface AnalitikaMagacinskeKarticeDaoLocal extends GenericDao<AnalitikaMagacinskeKartice, Integer>{

	public List<AnalitikaMagacinskeKartice> findByMagCardId(int parseInt);

}
