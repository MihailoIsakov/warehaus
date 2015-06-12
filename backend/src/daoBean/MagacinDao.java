package daoBean;

import javax.ejb.Local;
import javax.ejb.Stateless;

import model.Magacin;
import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDaoBean;

@Stateless
@Local(MagacinDaoLocal.class)
public class MagacinDao extends GenericDaoBean<Magacin, Long> implements MagacinDaoLocal{

}
