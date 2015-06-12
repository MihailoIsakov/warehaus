package daoBean;

import javax.ejb.Local;
import javax.ejb.Stateless;

import model.PrometniDokument;
import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDaoBean;

@Stateless
@Local(PrometniDokumentDaoLocal.class)
public class PrometniDokumentDao extends GenericDaoBean<PrometniDokument, Long> implements PrometniDokumentDaoLocal{

}
