package daoBean;

import java.util.List;

import model.MagacinskaKartica;
import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDao;

public interface MagacinskaKarticaDaoLocal extends GenericDao<MagacinskaKartica, Long> {


	public List<Object> findByMagacin(Integer id);
	

}
