package model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.Set;


/**
 * The persistent class for the jedinica_mere database table.
 * 
 */
@Entity
@Table(name="jedinica_mere")
@NamedQuery(name="JedinicaMere.findAll", query="SELECT j FROM JedinicaMere j")
@JsonInclude(Include.NON_NULL)
public class JedinicaMere implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_JEDINICA_MERE")
	private int idJedinicaMere;

	@Column(name="NAZIV_JEDINICE_MERE")
	private String nazivJediniceMere;

	@Column(name="SIFRA_JEDINICE_MERE")
	private String sifraJediniceMere;

	//bi-directional many-to-one association to Artikal
	


	public JedinicaMere() {
	}

	public int getIdJedinicaMere() {
		return this.idJedinicaMere;
	}

	public void setIdJedinicaMere(int idJedinicaMere) {
		this.idJedinicaMere = idJedinicaMere;
	}

	public String getNazivJediniceMere() {
		return this.nazivJediniceMere;
	}

	public void setNazivJediniceMere(String nazivJediniceMere) {
		this.nazivJediniceMere = nazivJediniceMere;
	}

	public String getSifraJediniceMere() {
		return this.sifraJediniceMere;
	}

	public void setSifraJediniceMere(String sifraJediniceMere) {
		this.sifraJediniceMere = sifraJediniceMere;
	}

	


}