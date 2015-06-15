package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the zaposleni database table.
 * 
 */
@Entity
@NamedQuery(name="Zaposleni.findAll", query="SELECT z FROM Zaposleni z")
public class Zaposleni implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_ZAPOSLENI")
	private int idZaposleni;

	@Column(name="ADRESA_ZAPOSLENOG")
	private String adresaZaposlenog;

	@Column(name="BROJ_TELEFONA")
	private String brojTelefona;

	@Temporal(TemporalType.DATE)
	@Column(name="DATUM_RODJENJA")
	private Date datumRodjenja;

	private String ime;

	private String jmbg;

	private String prezime;

	@Column(name="SIFRA_ZAPOSLENOG")
	private String sifraZaposlenog;

	@Column(name="STATUS_ZAPOSLENOG")
	private String statusZaposlenog;
	 @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	    @JoinColumn(name="zaposleni1")

	//bi-directional many-to-one association to PopisnaKomisija
	
	private Set<PopisnaKomisija> popisnaKomisijas1;
	 @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	    @JoinColumn(name="zaposleni2")

	//bi-directional many-to-one association to PopisnaKomisija
	
	private Set<PopisnaKomisija> popisnaKomisijas2;
	 @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	    @JoinColumn(name="zaposleni3")

	//bi-directional many-to-one association to PopisnaKomisija
	
	private Set<PopisnaKomisija> popisnaKomisijas3;

	//bi-directional many-to-one association to Mesto
	@ManyToOne
	@JoinColumn(name="ID_MESTO")
	private Mesto mesto;

	//bi-directional many-to-one association to Preduzece
	@ManyToOne
	@JoinColumn(name="ID_PREDUZECE")
	private Preduzece preduzece;

	//bi-directional many-to-one association to RadnoMesto
	@ManyToOne
	@JoinColumn(name="ID_RADNO_MESTO")
	private RadnoMesto radnoMesto;

	public Zaposleni() {
	}

	public int getIdZaposleni() {
		return this.idZaposleni;
	}

	public void setIdZaposleni(int idZaposleni) {
		this.idZaposleni = idZaposleni;
	}

	public String getAdresaZaposlenog() {
		return this.adresaZaposlenog;
	}

	public void setAdresaZaposlenog(String adresaZaposlenog) {
		this.adresaZaposlenog = adresaZaposlenog;
	}

	public String getBrojTelefona() {
		return this.brojTelefona;
	}

	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}

	public Date getDatumRodjenja() {
		return this.datumRodjenja;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getJmbg() {
		return this.jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getSifraZaposlenog() {
		return this.sifraZaposlenog;
	}

	public void setSifraZaposlenog(String sifraZaposlenog) {
		this.sifraZaposlenog = sifraZaposlenog;
	}

	public String getStatusZaposlenog() {
		return this.statusZaposlenog;
	}

	public void setStatusZaposlenog(String statusZaposlenog) {
		this.statusZaposlenog = statusZaposlenog;
	}

	public Set<PopisnaKomisija> getPopisnaKomisijas1() {
		return this.popisnaKomisijas1;
	}

	public void setPopisnaKomisijas1(Set<PopisnaKomisija> popisnaKomisijas1) {
		this.popisnaKomisijas1 = popisnaKomisijas1;
	}

	public PopisnaKomisija addPopisnaKomisijas1(PopisnaKomisija popisnaKomisijas1) {
		getPopisnaKomisijas1().add(popisnaKomisijas1);
		popisnaKomisijas1.setZaposleni1(this);

		return popisnaKomisijas1;
	}

	public PopisnaKomisija removePopisnaKomisijas1(PopisnaKomisija popisnaKomisijas1) {
		getPopisnaKomisijas1().remove(popisnaKomisijas1);
		popisnaKomisijas1.setZaposleni1(null);

		return popisnaKomisijas1;
	}

	public Set<PopisnaKomisija> getPopisnaKomisijas2() {
		return this.popisnaKomisijas2;
	}

	public void setPopisnaKomisijas2(Set<PopisnaKomisija> popisnaKomisijas2) {
		this.popisnaKomisijas2 = popisnaKomisijas2;
	}

	public PopisnaKomisija addPopisnaKomisijas2(PopisnaKomisija popisnaKomisijas2) {
		getPopisnaKomisijas2().add(popisnaKomisijas2);
		popisnaKomisijas2.setZaposleni2(this);

		return popisnaKomisijas2;
	}

	public PopisnaKomisija removePopisnaKomisijas2(PopisnaKomisija popisnaKomisijas2) {
		getPopisnaKomisijas2().remove(popisnaKomisijas2);
		popisnaKomisijas2.setZaposleni2(null);

		return popisnaKomisijas2;
	}

	public Set<PopisnaKomisija> getPopisnaKomisijas3() {
		return this.popisnaKomisijas3;
	}

	public void setPopisnaKomisijas3(Set<PopisnaKomisija> popisnaKomisijas3) {
		this.popisnaKomisijas3 = popisnaKomisijas3;
	}

	public PopisnaKomisija addPopisnaKomisijas3(PopisnaKomisija popisnaKomisijas3) {
		getPopisnaKomisijas3().add(popisnaKomisijas3);
		popisnaKomisijas3.setZaposleni3(this);

		return popisnaKomisijas3;
	}

	public PopisnaKomisija removePopisnaKomisijas3(PopisnaKomisija popisnaKomisijas3) {
		getPopisnaKomisijas3().remove(popisnaKomisijas3);
		popisnaKomisijas3.setZaposleni3(null);

		return popisnaKomisijas3;
	}

	public Mesto getMesto() {
		return this.mesto;
	}

	public void setMesto(Mesto mesto) {
		this.mesto = mesto;
	}

	public Preduzece getPreduzece() {
		return this.preduzece;
	}

	public void setPreduzece(Preduzece preduzece) {
		this.preduzece = preduzece;
	}

	public RadnoMesto getRadnoMesto() {
		return this.radnoMesto;
	}

	public void setRadnoMesto(RadnoMesto radnoMesto) {
		this.radnoMesto = radnoMesto;
	}

}