package daoBean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import model.StavkaPrometnogDokumenta;
import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDaoBean;

@Stateless
@Local(StavkaPrometnogDokumentaDaoLocal.class)
public class StavkaPrometnogDokumentaDao extends GenericDaoBean<StavkaPrometnogDokumenta, Integer> implements StavkaPrometnogDokumentaDaoLocal{
	public List<StavkaPrometnogDokumenta> getStavkeByIdDokumenta(Integer dokumentId) {
			List<StavkaPrometnogDokumenta> ret = new ArrayList<StavkaPrometnogDokumenta>();
			for (StavkaPrometnogDokumenta stavka : this.findAll()) {
				if (stavka.getPrometniDokument().getIdPrometniDokument() == dokumentId) {
					ret.add(stavka);
				}
			}
			return ret;
	}
}
