package daoBean;

import javax.ejb.Local;
import javax.ejb.Stateless;

import model.PoslovniPartner;
import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDaoBean;

@Stateless
@Local(PoslovniPartnerDaoLocal.class)
public class PoslovniPartnerDao extends GenericDaoBean<PoslovniPartner, Integer> implements PoslovniPartnerDaoLocal{

}
