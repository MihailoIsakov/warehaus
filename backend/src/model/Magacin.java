package model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.Set;


/**
 * The persistent class for the magacin database table.
 * 
 */
@Entity
@NamedQuery(name="Magacin.findAll", query="SELECT m FROM Magacin m")
@JsonInclude(Include.NON_NULL)
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



}