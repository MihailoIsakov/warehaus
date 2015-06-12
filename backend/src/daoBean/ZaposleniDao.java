package daoBean;

import javax.ejb.Local;
import javax.ejb.Stateless;

import model.Zaposleni;
import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDaoBean;

@Stateless
@Local(ZaposleniDaoLocal.class)
public class ZaposleniDao extends GenericDaoBean<Zaposleni, Long> implements ZaposleniDaoLocal{

}
