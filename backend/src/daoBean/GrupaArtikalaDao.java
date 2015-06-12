package daoBean;

import javax.ejb.Local;
import javax.ejb.Stateless;

import model.GrupaArtikala;
import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDaoBean;

@Stateless
@Local(GrupaArtikalaDaoLocal.class)
public class GrupaArtikalaDao extends GenericDaoBean<GrupaArtikala, Long> implements GrupaArtikalaDaoLocal{

}
