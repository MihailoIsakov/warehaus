package model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the vrsta_dokumenta database table.
 * 
 */
@Entity
@Table(name="vrsta_dokumenta")
@NamedQueries({
	@NamedQuery(name="VrstaDokumenta.findAll", query="SELECT v FROM VrstaDokumenta v"),
	@NamedQuery(name="VrstaDokumenta.findByName", query="SELECT v FROM VrstaDokumenta v WHERE v.nazivVrste like :name")
})
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