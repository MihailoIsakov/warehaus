package daoBean;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import model.MagacinskaKartica;
import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDaoBean;

@Stateless
@Local(MagacinskaKarticaDaoLocal.class)
public class MagacinskaKarticaDao extends GenericDaoBean<MagacinskaKartica, Long> implements MagacinskaKarticaDaoLocal{

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findByMagacin(Integer id) {
		
		Query q = em.createNamedQuery( "MagacinskaKartica.findByMagacin");
	
		q.setParameter("id", id);
		return q.getResultList();
	}
	
}
