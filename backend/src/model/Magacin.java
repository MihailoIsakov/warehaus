package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Set;


/**
 * The persistent class for the magacin database table.
 * 
 */
@Entity
@NamedQuery(name="Magacin.findAll", query="SELECT m FROM Magacin m")
public class Magacin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_MAGACIN")
	private int idMagacin;

	@Column(name="ADRESA_MAGACINA")
	private String adresaMagacina;

	@Column(name="NAZIV_MAGACINA")
	private String nazivMagacina;

	@Column(name="SIFRA_MAGACINA")
	private String sifraMagacina;

	//bi-directional many-to-one association to Mesto
	@ManyToOne
	@JoinColumn(name="ID_MESTO")
	private Mesto mesto;

	//bi-directional many-to-one association to Sektor
	@ManyToOne
	@JoinColumn(name="ID_SEKTOR")
	private Sektor sektor;

	//bi-directional many-to-one association to MagacinskaKartica
	 @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	    @JoinColumn(name="magacin")

	private Set<MagacinskaKartica> magacinskaKarticas;

	//bi-directional many-to-one association to PopisniDokument
	 @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	    @JoinColumn(name="magacin")


	private Set<PopisniDokument> popisniDokuments;
	 @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	    @JoinColumn(name="magacin1")


	private Set<PrometniDokument> prometniDokuments1;
	 @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	    @JoinColumn(name="magacin2")

	//bi-directional many-to-one association to PrometniDokument

	private Set<PrometniDokument> prometniDokuments2;

	public Magacin() {
	}

	public int getIdMagacin() {
		return this.idMagacin;
	}

	public void setIdMagacin(int idMagacin) {
		this.idMagacin = idMagacin;
	}

	public String getAdresaMagacina() {
		return this.adresaMagacina;
	}

	public void setAdresaMagacina(String adresaMagacina) {
		this.adresaMagacina = adresaMagacina;
	}

	public String getNazivMagacina() {
		return this.nazivMagacina;
	}

	public void setNazivMagacina(String nazivMagacina) {
		this.nazivMagacina = nazivMagacina;
	}

	public String getSifraMagacina() {
		return this.sifraMagacina;
	}

	public void setSifraMagacina(String sifraMagacina) {
		this.sifraMagacina = sifraMagacina;
	}

	public Mesto getMesto() {
		return this.mesto;
	}

	public void setMesto(Mesto mesto) {
		this.mesto = mesto;
	}

	public Sektor getSektor() {
		return this.sektor;
	}

	public void setSektor(Sektor sektor) {
		this.sektor = sektor;
	}

	public Set<MagacinskaKartica> getMagacinskaKarticas() {
		return this.magacinskaKarticas;
	}

	public void setMagacinskaKarticas(Set<MagacinskaKartica> magacinskaKarticas) {
		this.magacinskaKarticas = magacinskaKarticas;
	}

	public MagacinskaKartica addMagacinskaKartica(MagacinskaKartica magacinskaKartica) {
		getMagacinskaKarticas().add(magacinskaKartica);
		magacinskaKartica.setMagacin(this);

		return magacinskaKartica;
	}

	public MagacinskaKartica removeMagacinskaKartica(MagacinskaKartica magacinskaKartica) {
		getMagacinskaKarticas().remove(magacinskaKartica);
		magacinskaKartica.setMagacin(null);

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
		popisniDokument.setMagacin(this);

		return popisniDokument;
	}

	public PopisniDokument removePopisniDokument(PopisniDokument popisniDokument) {
		getPopisniDokuments().remove(popisniDokument);
		popisniDokument.setMagacin(null);

		return popisniDokument;
	}

	public Set<PrometniDokument> getPrometniDokuments1() {
		return this.prometniDokuments1;
	}

	public void setPrometniDokuments1(Set<PrometniDokument> prometniDokuments1) {
		this.prometniDokuments1 = prometniDokuments1;
	}

	public PrometniDokument addPrometniDokuments1(PrometniDokument prometniDokuments1) {
		getPrometniDokuments1().add(prometniDokuments1);
		prometniDokuments1.setMagacin1(this);

		return prometniDokuments1;
	}

	public PrometniDokument removePrometniDokuments1(PrometniDokument prometniDokuments1) {
		getPrometniDokuments1().remove(prometniDokuments1);
		prometniDokuments1.setMagacin1(null);

		return prometniDokuments1;
	}

	public Set<PrometniDokument> getPrometniDokuments2() {
		return this.prometniDokuments2;
	}

	public void setPrometniDokuments2(Set<PrometniDokument> prometniDokuments2) {
		this.prometniDokuments2 = prometniDokuments2;
	}

	public PrometniDokument addPrometniDokuments2(PrometniDokument prometniDokuments2) {
		getPrometniDokuments2().add(prometniDokuments2);
		prometniDokuments2.setMagacin2(this);

		return prometniDokuments2;
	}

	public PrometniDokument removePrometniDokuments2(PrometniDokument prometniDokuments2) {
		getPrometniDokuments2().remove(prometniDokuments2);
		prometniDokuments2.setMagacin2(null);

		return prometniDokuments2;
	}

}