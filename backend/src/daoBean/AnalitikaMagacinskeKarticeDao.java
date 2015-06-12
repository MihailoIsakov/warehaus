package daoBean;

import javax.ejb.Local;
import javax.ejb.Stateless;

import model.AnalitikaMagacinskeKartice;
import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDaoBean;

@Stateless
@Local(AnalitikaMagacinskeKarticeDaoLocal.class)
public class AnalitikaMagacinskeKarticeDao extends GenericDaoBean<AnalitikaMagacinskeKartice, Long> implements AnalitikaMagacinskeKarticeDaoLocal{

}
