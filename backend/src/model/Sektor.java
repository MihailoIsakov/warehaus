package model;

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
	@Column(name="ID_SEKTOR")
	private int idSektor;

	@Column(name="NAZIV_SEKTORA")
	private String nazivSektora;

	@Column(name="SIFRA_SEKTORA")
	private String sifraSektora;
	 @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	    @JoinColumn(name="sektor")

	//bi-directional many-to-one association to Magacin

	private Set<Magacin> magacins;

	//bi-directional many-to-one association to Preduzece
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

	public Set<Magacin> getMagacins() {
		return this.magacins;
	}

	public void setMagacins(Set<Magacin> magacins) {
		this.magacins = magacins;
	}

	public Magacin addMagacin(Magacin magacin) {
		getMagacins().add(magacin);
		magacin.setSektor(this);

		return magacin;
	}

	public Magacin removeMagacin(Magacin magacin) {
		getMagacins().remove(magacin);
		magacin.setSektor(null);

		return magacin;
	}

	public Preduzece getPreduzece() {
		return this.preduzece;
	}

	public void setPreduzece(Preduzece preduzece) {
		this.preduzece = preduzece;
	}

}