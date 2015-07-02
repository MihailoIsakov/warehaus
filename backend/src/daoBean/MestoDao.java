package daoBean;

import javax.ejb.Local;
import javax.ejb.Stateless;

import model.Mesto;
import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDaoBean;

@Stateless
@Local(MestoDaoLocal.class)
public class MestoDao extends GenericDaoBean<Mesto, Integer> implements MestoDaoLocal{

}
