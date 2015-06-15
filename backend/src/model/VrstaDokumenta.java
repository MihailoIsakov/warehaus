package model;

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
	@Column(name="ID_VRSTA_DOKUMENTA")
	private int idVrstaDokumenta;

	@Column(name="NAZIV_VRSTE")
	private String nazivVrste;

	@Column(name="SIFRA_VRSTE")
	private String sifraVrste;
	 @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	    @JoinColumn(name="vrstaDokumenta")

	//bi-directional many-to-one association to AnalitikaMagacinskeKartice

	private Set<AnalitikaMagacinskeKartice> analitikaMagacinskeKartices;

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

	public Set<AnalitikaMagacinskeKartice> getAnalitikaMagacinskeKartices() {
		return this.analitikaMagacinskeKartices;
	}

	public void setAnalitikaMagacinskeKartices(Set<AnalitikaMagacinskeKartice> analitikaMagacinskeKartices) {
		this.analitikaMagacinskeKartices = analitikaMagacinskeKartices;
	}

	public AnalitikaMagacinskeKartice addAnalitikaMagacinskeKartice(AnalitikaMagacinskeKartice analitikaMagacinskeKartice) {
		getAnalitikaMagacinskeKartices().add(analitikaMagacinskeKartice);
		analitikaMagacinskeKartice.setVrstaDokumenta(this);

		return analitikaMagacinskeKartice;
	}

	public AnalitikaMagacinskeKartice removeAnalitikaMagacinskeKartice(AnalitikaMagacinskeKartice analitikaMagacinskeKartice) {
		getAnalitikaMagacinskeKartices().remove(analitikaMagacinskeKartice);
		analitikaMagacinskeKartice.setVrstaDokumenta(null);

		return analitikaMagacinskeKartice;
	}

}