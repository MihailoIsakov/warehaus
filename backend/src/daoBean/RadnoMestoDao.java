package daoBean;

import javax.ejb.Local;
import javax.ejb.Stateless;

import model.RadnoMesto;
import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDaoBean;

@Stateless
@Local(RadnoMestoDaoLocal.class)
public class RadnoMestoDao extends GenericDaoBean<RadnoMesto, Long> implements RadnoMestoDaoLocal{

}
