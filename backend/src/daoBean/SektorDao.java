package daoBean;

import javax.ejb.Local;
import javax.ejb.Stateless;

import model.Sektor;
import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDaoBean;

@Stateless
@Local(SektorDaoLocal.class)
public class SektorDao extends GenericDaoBean<Sektor, Integer> implements SektorDaoLocal{

}
