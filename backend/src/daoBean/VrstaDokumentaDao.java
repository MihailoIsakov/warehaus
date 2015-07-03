package daoBean;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import model.VrstaDokumenta;
import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDaoBean;

@Stateless
@Local(VrstaDokumentaDaoLocal.class)
public class VrstaDokumentaDao extends GenericDaoBean<VrstaDokumenta, Long> implements VrstaDokumentaDaoLocal{

	@Override
	public VrstaDokumenta findByName(String name) {
		Query q = em.createNamedQuery("VrstaDokumenta.findByName");

		q.setParameter("name", name);
		return (VrstaDokumenta) q.getResultList();
	}

}
