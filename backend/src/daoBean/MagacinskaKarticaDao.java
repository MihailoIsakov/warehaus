package daoBean;

import javax.ejb.Local;
import javax.ejb.Stateless;

import model.MagacinskaKartica;
import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDaoBean;

@Stateless
@Local(MagacinskaKarticaDaoLocal.class)
public class MagacinskaKarticaDao extends GenericDaoBean<MagacinskaKartica, Long> implements MagacinskaKarticaDaoLocal{

}
