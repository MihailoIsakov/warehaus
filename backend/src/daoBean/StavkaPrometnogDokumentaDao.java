package daoBean;

import javax.ejb.Local;
import javax.ejb.Stateless;

import model.StavkaPrometnogDokumenta;
import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDaoBean;

@Stateless
@Local(StavkaPrometnogDokumentaDaoLocal.class)
public class StavkaPrometnogDokumentaDao extends GenericDaoBean<StavkaPrometnogDokumenta, Long> implements StavkaPrometnogDokumentaDaoLocal{

}
