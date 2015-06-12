package daoBean;

import javax.ejb.Local;
import javax.ejb.Stateless;

import model.Drzava;
import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDaoBean;

@Stateless
@Local(DrzavaDaoLocal.class)
public class DrzavaDao extends GenericDaoBean<Drzava, Long> implements DrzavaDaoLocal{

}
