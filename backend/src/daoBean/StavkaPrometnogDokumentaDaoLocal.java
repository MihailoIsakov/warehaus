package daoBean;

import java.util.List;

import model.StavkaPrometnogDokumenta;
import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDao;

public interface StavkaPrometnogDokumentaDaoLocal extends GenericDao<StavkaPrometnogDokumenta, Integer>{

	public List<StavkaPrometnogDokumenta> getStavkeByIdDokumenta(Integer dokumentId);
}
