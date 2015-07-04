package daoBean;

import java.util.Iterator;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import model.Magacin;

import org.hibernate.Criteria;
import org.hibernate.Session;

import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDaoBean;

@Stateless
@Local(MagacinDaoLocal.class)
public class MagacinDao extends GenericDaoBean<Magacin, Integer> implements MagacinDaoLocal{
/*
	@SuppressWarnings("unchecked")
	@Override
	public List<Magacin> findAll() {
		Session session = getHibernateSession();
		Criteria criteria = session.createCriteria(entityType);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);	
		List<Magacin> lista = criteria.list();
		Iterator<Magacin> it = lista.iterator();
		while (it.hasNext()) {
			Magacin type = (Magacin) it.next();
			type.getMagacinskaKarticas().size();
			
		}
		return lista;
	}
		*/
}
