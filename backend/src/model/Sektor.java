package model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Set;


/**
 * The persistent class for the sektor database table.
 * 
 */
@Entity
@NamedQuery(name="Sektor.findAll", query="SELECT s FROM Sektor s")
public class Sektor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	 @GeneratedValue(strategy=IDENTITY)
	@Column(name="ID_SEKTOR")
	private int idSektor;

	@Column(name="NAZIV_SEKTORA")
	private String nazivSektora;

	@Column(name="SIFRA_SEKTORA")
	private String sifraSektora;
	@ManyToOne
	@JoinColumn(name="ID_PREDUZECE")
	private Preduzece preduzece;

	public Sektor() {
	}

	public int getIdSektor() {
		return this.idSektor;
	}

	public void setIdSektor(int idSektor) {
		this.idSektor = idSektor;
	}

	public String getNazivSektora() {
		return this.nazivSektora;
	}

	public void setNazivSektora(String nazivSektora) {
		this.nazivSektora = nazivSektora;
	}

	public String getSifraSektora() {
		return this.sifraSektora;
	}

	public void setSifraSektora(String sifraSektora) {
		this.sifraSektora = sifraSektora;
	}


	public Preduzece getPreduzece() {
		return this.preduzece;
	}

	public void setPreduzece(Preduzece preduzece) {
		this.preduzece = preduzece;
	}

}