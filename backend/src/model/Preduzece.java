package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the preduzece database table.
 * 
 */
@Entity
@NamedQuery(name="Preduzece.findAll", query="SELECT p FROM Preduzece p")
public class Preduzece implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_PREDUZECE")
	private int idPreduzece;

	private String adresa;

	private String fax;

	@Column(name="NAZIV_PREDUZECA")
	private String nazivPreduzeca;

	@Column(name="SIFRA_PREDUZECA")
	private String sifraPreduzeca;

	private String telefon;

	//bi-directional many-to-one association to GrupaArtikala
	@OneToMany(mappedBy="preduzece")
	private List<GrupaArtikala> grupaArtikalas;

	//bi-directional many-to-one association to PoslovnaGodina
	@OneToMany(mappedBy="preduzece")
	private List<PoslovnaGodina> poslovnaGodinas;

	//bi-directional many-to-one association to Mesto
	@ManyToOne
	@JoinColumn(name="ID_MESTO")
	private Mesto mesto;

	//bi-directional many-to-one association to Sektor
	@OneToMany(mappedBy="preduzece")
	private List<Sektor> sektors;

	//bi-directional many-to-one association to Zaposleni
	@OneToMany(mappedBy="preduzece")
	private List<Zaposleni> zaposlenis;

	public Preduzece() {
	}

	public int getIdPreduzece() {
		return this.idPreduzece;
	}

	public void setIdPreduzece(int idPreduzece) {
		this.idPreduzece = idPreduzece;
	}

	public String getAdresa() {
		return this.adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getNazivPreduzeca() {
		return this.nazivPreduzeca;
	}

	public void setNazivPreduzeca(String nazivPreduzeca) {
		this.nazivPreduzeca = nazivPreduzeca;
	}

	public String getSifraPreduzeca() {
		return this.sifraPreduzeca;
	}

	public void setSifraPreduzeca(String sifraPreduzeca) {
		this.sifraPreduzeca = sifraPreduzeca;
	}

	public String getTelefon() {
		return this.telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public List<GrupaArtikala> getGrupaArtikalas() {
		return this.grupaArtikalas;
	}

	public void setGrupaArtikalas(List<GrupaArtikala> grupaArtikalas) {
		this.grupaArtikalas = grupaArtikalas;
	}

	public GrupaArtikala addGrupaArtikala(GrupaArtikala grupaArtikala) {
		getGrupaArtikalas().add(grupaArtikala);
		grupaArtikala.setPreduzece(this);

		return grupaArtikala;
	}

	public GrupaArtikala removeGrupaArtikala(GrupaArtikala grupaArtikala) {
		getGrupaArtikalas().remove(grupaArtikala);
		grupaArtikala.setPreduzece(null);

		return grupaArtikala;
	}

	public List<PoslovnaGodina> getPoslovnaGodinas() {
		return this.poslovnaGodinas;
	}

	public void setPoslovnaGodinas(List<PoslovnaGodina> poslovnaGodinas) {
		this.poslovnaGodinas = poslovnaGodinas;
	}

	public PoslovnaGodina addPoslovnaGodina(PoslovnaGodina poslovnaGodina) {
		getPoslovnaGodinas().add(poslovnaGodina);
		poslovnaGodina.setPreduzece(this);

		return poslovnaGodina;
	}

	public PoslovnaGodina removePoslovnaGodina(PoslovnaGodina poslovnaGodina) {
		getPoslovnaGodinas().remove(poslovnaGodina);
		poslovnaGodina.setPreduzece(null);

		return poslovnaGodina;
	}

	public Mesto getMesto() {
		return this.mesto;
	}

	public void setMesto(Mesto mesto) {
		this.mesto = mesto;
	}

	public List<Sektor> getSektors() {
		return this.sektors;
	}

	public void setSektors(List<Sektor> sektors) {
		this.sektors = sektors;
	}

	public Sektor addSektor(Sektor sektor) {
		getSektors().add(sektor);
		sektor.setPreduzece(this);

		return sektor;
	}

	public Sektor removeSektor(Sektor sektor) {
		getSektors().remove(sektor);
		sektor.setPreduzece(null);

		return sektor;
	}

	public List<Zaposleni> getZaposlenis() {
		return this.zaposlenis;
	}

	public void setZaposlenis(List<Zaposleni> zaposlenis) {
		this.zaposlenis = zaposlenis;
	}

	public Zaposleni addZaposleni(Zaposleni zaposleni) {
		getZaposlenis().add(zaposleni);
		zaposleni.setPreduzece(this);

		return zaposleni;
	}

	public Zaposleni removeZaposleni(Zaposleni zaposleni) {
		getZaposlenis().remove(zaposleni);
		zaposleni.setPreduzece(null);

		return zaposleni;
	}

}