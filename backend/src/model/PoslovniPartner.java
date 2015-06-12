package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the poslovni_partner database table.
 * 
 */
@Entity
@Table(name="poslovni_partner")
@NamedQuery(name="PoslovniPartner.findAll", query="SELECT p FROM PoslovniPartner p")
public class PoslovniPartner implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_POSLOVNI_PARTNER")
	private int idPoslovniPartner;

	@Column(name="ADRESA_PARTNERA")
	private String adresaPartnera;

	@Column(name="BROJ_TELEFONA_PARTNERA")
	private String brojTelefonaPartnera;

	@Column(name="KONTAKT_OSOBA")
	private String kontaktOsoba;

	@Column(name="MAIL_ADRESA")
	private String mailAdresa;

	@Column(name="NAZIV_PARTNERA")
	private String nazivPartnera;

	@Column(name="SIFRA_PARTNERA")
	private String sifraPartnera;

	@Column(name="VRSTA_PARTNERA")
	private String vrstaPartnera;

	//bi-directional many-to-one association to Mesto
	@ManyToOne
	@JoinColumn(name="ID_MESTO")
	private Mesto mesto;

	//bi-directional many-to-one association to PrometniDokument
	@OneToMany(mappedBy="poslovniPartner")
	private List<PrometniDokument> prometniDokuments;

	public PoslovniPartner() {
	}

	public int getIdPoslovniPartner() {
		return this.idPoslovniPartner;
	}

	public void setIdPoslovniPartner(int idPoslovniPartner) {
		this.idPoslovniPartner = idPoslovniPartner;
	}

	public String getAdresaPartnera() {
		return this.adresaPartnera;
	}

	public void setAdresaPartnera(String adresaPartnera) {
		this.adresaPartnera = adresaPartnera;
	}

	public String getBrojTelefonaPartnera() {
		return this.brojTelefonaPartnera;
	}

	public void setBrojTelefonaPartnera(String brojTelefonaPartnera) {
		this.brojTelefonaPartnera = brojTelefonaPartnera;
	}

	public String getKontaktOsoba() {
		return this.kontaktOsoba;
	}

	public void setKontaktOsoba(String kontaktOsoba) {
		this.kontaktOsoba = kontaktOsoba;
	}

	public String getMailAdresa() {
		return this.mailAdresa;
	}

	public void setMailAdresa(String mailAdresa) {
		this.mailAdresa = mailAdresa;
	}

	public String getNazivPartnera() {
		return this.nazivPartnera;
	}

	public void setNazivPartnera(String nazivPartnera) {
		this.nazivPartnera = nazivPartnera;
	}

	public String getSifraPartnera() {
		return this.sifraPartnera;
	}

	public void setSifraPartnera(String sifraPartnera) {
		this.sifraPartnera = sifraPartnera;
	}

	public String getVrstaPartnera() {
		return this.vrstaPartnera;
	}

	public void setVrstaPartnera(String vrstaPartnera) {
		this.vrstaPartnera = vrstaPartnera;
	}

	public Mesto getMesto() {
		return this.mesto;
	}

	public void setMesto(Mesto mesto) {
		this.mesto = mesto;
	}

	public List<PrometniDokument> getPrometniDokuments() {
		return this.prometniDokuments;
	}

	public void setPrometniDokuments(List<PrometniDokument> prometniDokuments) {
		this.prometniDokuments = prometniDokuments;
	}

	public PrometniDokument addPrometniDokument(PrometniDokument prometniDokument) {
		getPrometniDokuments().add(prometniDokument);
		prometniDokument.setPoslovniPartner(this);

		return prometniDokument;
	}

	public PrometniDokument removePrometniDokument(PrometniDokument prometniDokument) {
		getPrometniDokuments().remove(prometniDokument);
		prometniDokument.setPoslovniPartner(null);

		return prometniDokument;
	}

}