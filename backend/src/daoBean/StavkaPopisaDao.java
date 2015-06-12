package daoBean;

import javax.ejb.Local;
import javax.ejb.Stateless;

import model.StavkaPopisa;
import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDaoBean;

@Stateless
@Local(StavkaPopisaDaoLocal.class)
public class StavkaPopisaDao extends GenericDaoBean<StavkaPopisa, Long> implements StavkaPopisaDaoLocal{

}
