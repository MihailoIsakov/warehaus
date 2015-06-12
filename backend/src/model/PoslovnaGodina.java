package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the poslovna_godina database table.
 * 
 */
@Entity
@Table(name="poslovna_godina")
@NamedQuery(name="PoslovnaGodina.findAll", query="SELECT p FROM PoslovnaGodina p")
public class PoslovnaGodina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_POSLOVNA_GODINA")
	private int idPoslovnaGodina;

	@Column(name="BROJ_GODINE")
	private int brojGodine;

	@Column(name="ZAKLJUCENA_GODINA")
	private byte zakljucenaGodina;

	//bi-directional many-to-one association to MagacinskaKartica
	@OneToMany(mappedBy="poslovnaGodina")
	private List<MagacinskaKartica> magacinskaKarticas;

	//bi-directional many-to-one association to PopisniDokument
	@OneToMany(mappedBy="poslovnaGodina")
	private List<PopisniDokument> popisniDokuments;

	//bi-directional many-to-one association to Preduzece
	@ManyToOne
	@JoinColumn(name="ID_PREDUZECE")
	private Preduzece preduzece;

	//bi-directional many-to-one association to PrometniDokument
	@OneToMany(mappedBy="poslovnaGodina")
	private List<PrometniDokument> prometniDokuments;

	public PoslovnaGodina() {
	}

	public int getIdPoslovnaGodina() {
		return this.idPoslovnaGodina;
	}

	public void setIdPoslovnaGodina(int idPoslovnaGodina) {
		this.idPoslovnaGodina = idPoslovnaGodina;
	}

	public int getBrojGodine() {
		return this.brojGodine;
	}

	public void setBrojGodine(int brojGodine) {
		this.brojGodine = brojGodine;
	}

	public byte getZakljucenaGodina() {
		return this.zakljucenaGodina;
	}

	public void setZakljucenaGodina(byte zakljucenaGodina) {
		this.zakljucenaGodina = zakljucenaGodina;
	}

	public List<MagacinskaKartica> getMagacinskaKarticas() {
		return this.magacinskaKarticas;
	}

	public void setMagacinskaKarticas(List<MagacinskaKartica> magacinskaKarticas) {
		this.magacinskaKarticas = magacinskaKarticas;
	}

	public MagacinskaKartica addMagacinskaKartica(MagacinskaKartica magacinskaKartica) {
		getMagacinskaKarticas().add(magacinskaKartica);
		magacinskaKartica.setPoslovnaGodina(this);

		return magacinskaKartica;
	}

	public MagacinskaKartica removeMagacinskaKartica(MagacinskaKartica magacinskaKartica) {
		getMagacinskaKarticas().remove(magacinskaKartica);
		magacinskaKartica.setPoslovnaGodina(null);

		return magacinskaKartica;
	}

	public List<PopisniDokument> getPopisniDokuments() {
		return this.popisniDokuments;
	}

	public void setPopisniDokuments(List<PopisniDokument> popisniDokuments) {
		this.popisniDokuments = popisniDokuments;
	}

	public PopisniDokument addPopisniDokument(PopisniDokument popisniDokument) {
		getPopisniDokuments().add(popisniDokument);
		popisniDokument.setPoslovnaGodina(this);

		return popisniDokument;
	}

	public PopisniDokument removePopisniDokument(PopisniDokument popisniDokument) {
		getPopisniDokuments().remove(popisniDokument);
		popisniDokument.setPoslovnaGodina(null);

		return popisniDokument;
	}

	public Preduzece getPreduzece() {
		return this.preduzece;
	}

	public void setPreduzece(Preduzece preduzece) {
		this.preduzece = preduzece;
	}

	public List<PrometniDokument> getPrometniDokuments() {
		return this.prometniDokuments;
	}

	public void setPrometniDokuments(List<PrometniDokument> prometniDokuments) {
		this.prometniDokuments = prometniDokuments;
	}

	public PrometniDokument addPrometniDokument(PrometniDokument prometniDokument) {
		getPrometniDokuments().add(prometniDokument);
		prometniDokument.setPoslovnaGodina(this);

		return prometniDokument;
	}

	public PrometniDokument removePrometniDokument(PrometniDokument prometniDokument) {
		getPrometniDokuments().remove(prometniDokument);
		prometniDokument.setPoslovnaGodina(null);

		return prometniDokument;
	}

}