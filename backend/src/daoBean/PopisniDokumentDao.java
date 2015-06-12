package daoBean;

import javax.ejb.Local;
import javax.ejb.Stateless;

import model.PopisniDokument;
import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDaoBean;

@Stateless
@Local(PopisniDokumentDaoLocal.class)
public class PopisniDokumentDao extends GenericDaoBean<PopisniDokument, Long> implements PopisniDokumentDaoLocal{

}
