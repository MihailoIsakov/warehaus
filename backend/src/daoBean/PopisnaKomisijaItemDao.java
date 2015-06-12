package daoBean;

import javax.ejb.Local;
import javax.ejb.Stateless;

import model.PopisnaKomisija;
import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDaoBean;

@Stateless
@Local(PopisnaKomisijaDaoLocal.class)
public class PopisnaKomisijaItemDao extends GenericDaoBean<PopisnaKomisija, Long> implements PopisnaKomisijaDaoLocal{

}
