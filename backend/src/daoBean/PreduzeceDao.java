package daoBean;

import javax.ejb.Local;
import javax.ejb.Stateless;

import model.Preduzece;
import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDaoBean;

@Stateless
@Local(PreduzeceDaoLocal.class)
public class PreduzeceDao extends GenericDaoBean<Preduzece, Integer> implements PreduzeceDaoLocal{

}
