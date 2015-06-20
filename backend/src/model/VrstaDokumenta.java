package model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Set;


/**
 * The persistent class for the vrsta_dokumenta database table.
 * 
 */
@Entity
@Table(name="vrsta_dokumenta")
@NamedQuery(name="VrstaDokumenta.findAll", query="SELECT v FROM VrstaDokumenta v")
public class VrstaDokumenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	 @GeneratedValue(strategy=IDENTITY)
	@Column(name="ID_VRSTA_DOKUMENTA")
	private int idVrstaDokumenta;

	@Column(name="NAZIV_VRSTE")
	private String nazivVrste;

	@Column(name="SIFRA_VRSTE")
	private String sifraVrste;
		public VrstaDokumenta() {
	}

	public int getIdVrstaDokumenta() {
		return this.idVrstaDokumenta;
	}

	public void setIdVrstaDokumenta(int idVrstaDokumenta) {
		this.idVrstaDokumenta = idVrstaDokumenta;
	}

	public String getNazivVrste() {
		return this.nazivVrste;
	}

	public void setNazivVrste(String nazivVrste) {
		this.nazivVrste = nazivVrste;
	}

	public String getSifraVrste() {
		return this.sifraVrste;
	}

	public void setSifraVrste(String sifraVrste) {
		this.sifraVrste = sifraVrste;
	}

}