package daoBean;

import javax.ejb.Local;
import javax.ejb.Stateless;

import model.PoslovnaGodina;
import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDaoBean;

@Stateless
@Local(PoslovnaGodinaDaoLocal.class)
public class PoslovnaGodinaDao extends GenericDaoBean<PoslovnaGodina, Long> implements PoslovnaGodinaDaoLocal{

}
