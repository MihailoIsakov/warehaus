package model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.math.BigDecimal;
import java.util.Set;


/**
 * The persistent class for the artikal database table.
 * 
 */
@Entity
@NamedQuery(name="Artikal.findAll", query="SELECT a FROM Artikal a")
@JsonInclude(Include.NON_NULL)
public class Artikal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=IDENTITY)
	@Column(name="ID_ARTIKAL")
	private int idArtikal;

	@Column(name="NAZIV_ARTIKLA")
	private String nazivArtikla;

	private BigDecimal pakovanje;

	@Column(name="SIFRA_ARTIKLA")
	private String sifraArtikla;

	//bi-directional many-to-one association to GrupaArtikala
	@ManyToOne
	@JoinColumn(name="ID_GRUPA_ARTIKALA")
	private GrupaArtikala grupaArtikala;

	//bi-directional many-to-one association to JedinicaMere
	@ManyToOne
	@JoinColumn(name="ID_JEDINICA_MERE")
	private JedinicaMere jedinicaMere;

	//bi-directional many-to-one association to MagacinskaKartica
	

	public Artikal() {
	}

	public int getIdArtikal() {
		return this.idArtikal;
	}

	public void setIdArtikal(int idArtikal) {
		this.idArtikal = idArtikal;
	}

	public String getNazivArtikla() {
		return this.nazivArtikla;
	}

	public void setNazivArtikla(String nazivArtikla) {
		this.nazivArtikla = nazivArtikla;
	}

	public BigDecimal getPakovanje() {
		return this.pakovanje;
	}

	public void setPakovanje(BigDecimal pakovanje) {
		this.pakovanje = pakovanje;
	}

	public String getSifraArtikla() {
		return this.sifraArtikla;
	}

	public void setSifraArtikla(String sifraArtikla) {
		this.sifraArtikla = sifraArtikla;
	}

	public GrupaArtikala getGrupaArtikala() {
		return this.grupaArtikala;
	}

	public void setGrupaArtikala(GrupaArtikala grupaArtikala) {
		this.grupaArtikala = grupaArtikala;
	}

	public JedinicaMere getJedinicaMere() {
		return this.jedinicaMere;
	}

	public void setJedinicaMere(JedinicaMere jedinicaMere) {
		this.jedinicaMere = jedinicaMere;
	}

	
}
