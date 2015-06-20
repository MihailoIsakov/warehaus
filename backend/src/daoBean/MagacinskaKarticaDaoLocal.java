package daoBean;

import java.util.List;

import model.Magacin;
import model.MagacinskaKartica;
import model.PrometniDokument;
import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDao;

public interface MagacinskaKarticaDaoLocal extends GenericDao<MagacinskaKartica, Long> {


	public List<Object> findByMagacin(Integer id);

	public MagacinskaKartica findByMagaciniArtikaliPG(int idMagacin, int idArtikal, int idPG) throws Exception;

	void update(PrometniDokument entity, Magacin magacin, int brMagacina) throws Exception;
	

}
