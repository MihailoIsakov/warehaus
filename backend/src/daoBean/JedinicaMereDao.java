package daoBean;

import javax.ejb.Local;
import javax.ejb.Stateless;

import model.JedinicaMere;
import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDaoBean;

@Stateless
@Local(JedinicaMereDaoLocal.class)
public class JedinicaMereDao extends GenericDaoBean<JedinicaMere, Long> implements JedinicaMereDaoLocal{

}
