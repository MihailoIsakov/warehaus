package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the grupa_artikala database table.
 * 
 */
@Entity
@Table(name="grupa_artikala")
@NamedQuery(name="GrupaArtikala.findAll", query="SELECT g FROM GrupaArtikala g")
public class GrupaArtikala implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_GRUPA_ARTIKALA")
	private int idGrupaArtikala;

	@Column(name="NAZIV_GRUPE")
	private String nazivGrupe;

	@Column(name="SIFRA_GRUPE")
	private String sifraGrupe;

	//bi-directional many-to-one association to Artikal
	@OneToMany(mappedBy="grupaArtikala")
	private List<Artikal> artikals;

	//bi-directional many-to-one association to Preduzece
	@ManyToOne
	@JoinColumn(name="ID_PREDUZECE")
	private Preduzece preduzece;

	public GrupaArtikala() {
	}

	public int getIdGrupaArtikala() {
		return this.idGrupaArtikala;
	}

	public void setIdGrupaArtikala(int idGrupaArtikala) {
		this.idGrupaArtikala = idGrupaArtikala;
	}

	public String getNazivGrupe() {
		return this.nazivGrupe;
	}

	public void setNazivGrupe(String nazivGrupe) {
		this.nazivGrupe = nazivGrupe;
	}

	public String getSifraGrupe() {
		return this.sifraGrupe;
	}

	public void setSifraGrupe(String sifraGrupe) {
		this.sifraGrupe = sifraGrupe;
	}

	public List<Artikal> getArtikals() {
		return this.artikals;
	}

	public void setArtikals(List<Artikal> artikals) {
		this.artikals = artikals;
	}

	public Artikal addArtikal(Artikal artikal) {
		getArtikals().add(artikal);
		artikal.setGrupaArtikala(this);

		return artikal;
	}

	public Artikal removeArtikal(Artikal artikal) {
		getArtikals().remove(artikal);
		artikal.setGrupaArtikala(null);

		return artikal;
	}

	public Preduzece getPreduzece() {
		return this.preduzece;
	}

	public void setPreduzece(Preduzece preduzece) {
		this.preduzece = preduzece;
	}

}