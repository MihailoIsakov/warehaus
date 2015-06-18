package daoBean;

import javax.ejb.Local;
import javax.ejb.Stateless;

import model.Artikal;
import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDaoBean;

@Stateless
@Local(ArtikalDaoLocal.class)
public class ArtikalDao extends GenericDaoBean<Artikal, Long> implements ArtikalDaoLocal{


}
