package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Set;


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

	//bi-directional many-to-one association to Mesto
	@ManyToOne
	@JoinColumn(name="ID_MESTO")
	private Mesto mesto;
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

	public Mesto getMesto() {
		return mesto;
	}

	public void setMesto(Mesto mesto) {
		this.mesto = mesto;
	}


}