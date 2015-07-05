package daoBean;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import model.AnalitikaMagacinskeKartice;
import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDaoBean;

@Stateless
@Local(AnalitikaMagacinskeKarticeDaoLocal.class)
public class AnalitikaMagacinskeKarticeDao extends GenericDaoBean<AnalitikaMagacinskeKartice, Integer> implements AnalitikaMagacinskeKarticeDaoLocal{

	@SuppressWarnings("unchecked")
	@Override
	public List<AnalitikaMagacinskeKartice> findByMagCardId(int parseInt) {
		Query q = em.createNamedQuery("AnalitikaMagacinskeKartice.findByMagCardId");

		q.setParameter("id", parseInt);
		return q.getResultList();
	}

}
