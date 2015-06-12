package daoBean;

import javax.ejb.Local;
import javax.ejb.Stateless;

import model.VrstaDokumenta;
import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDaoBean;

@Stateless
@Local(VrstaDokumentaDaoLocal.class)
public class VrstaDokumentaDao extends GenericDaoBean<VrstaDokumenta, Long> implements VrstaDokumentaDaoLocal{

}
