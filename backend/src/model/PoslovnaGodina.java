package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Set;


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
	 @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	    @JoinColumn(name="poslovnaGodina")

	//bi-directional many-to-one association to MagacinskaKartica

	private Set<MagacinskaKartica> magacinskaKarticas;
	 @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	    @JoinColumn(name="poslovnaGodina")

	//bi-directional many-to-one association to PopisniDokument

	private Set<PopisniDokument> popisniDokuments;

	//bi-directional many-to-one association to Preduzece
	@ManyToOne
	@JoinColumn(name="ID_PREDUZECE")
	private Preduzece preduzece;
	 @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	    @JoinColumn(name="poslovnaGodina")

	//bi-directional many-to-one association to PrometniDokument

	private Set<PrometniDokument> prometniDokuments;

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

	public Set<MagacinskaKartica> getMagacinskaKarticas() {
		return this.magacinskaKarticas;
	}

	public void setMagacinskaKarticas(Set<MagacinskaKartica> magacinskaKarticas) {
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

	public Set<PopisniDokument> getPopisniDokuments() {
		return this.popisniDokuments;
	}

	public void setPopisniDokuments(Set<PopisniDokument> popisniDokuments) {
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

	public Set<PrometniDokument> getPrometniDokuments() {
		return this.prometniDokuments;
	}

	public void setPrometniDokuments(Set<PrometniDokument> prometniDokuments) {
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