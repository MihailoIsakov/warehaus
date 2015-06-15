package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Set;


/**
 * The persistent class for the radno_mesto database table.
 * 
 */
@Entity
@Table(name="radno_mesto")
@NamedQuery(name="RadnoMesto.findAll", query="SELECT r FROM RadnoMesto r")
public class RadnoMesto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_RADNO_MESTO")
	private int idRadnoMesto;

	@Column(name="NAZIV_RADNOG_MESTA")
	private String nazivRadnogMesta;

	@Column(name="SIFRA_RADNOG_MESTA")
	private String sifraRadnogMesta;
	 @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	    @JoinColumn(name="radnoMesto")

	//bi-directional many-to-one association to Zaposleni
	
	private Set<Zaposleni> zaposlenis;

	public RadnoMesto() {
	}

	public int getIdRadnoMesto() {
		return this.idRadnoMesto;
	}

	public void setIdRadnoMesto(int idRadnoMesto) {
		this.idRadnoMesto = idRadnoMesto;
	}

	public String getNazivRadnogMesta() {
		return this.nazivRadnogMesta;
	}

	public void setNazivRadnogMesta(String nazivRadnogMesta) {
		this.nazivRadnogMesta = nazivRadnogMesta;
	}

	public String getSifraRadnogMesta() {
		return this.sifraRadnogMesta;
	}

	public void setSifraRadnogMesta(String sifraRadnogMesta) {
		this.sifraRadnogMesta = sifraRadnogMesta;
	}

	public Set<Zaposleni> getZaposlenis() {
		return this.zaposlenis;
	}

	public void setZaposlenis(Set<Zaposleni> zaposlenis) {
		this.zaposlenis = zaposlenis;
	}

	public Zaposleni addZaposleni(Zaposleni zaposleni) {
		getZaposlenis().add(zaposleni);
		zaposleni.setRadnoMesto(this);

		return zaposleni;
	}

	public Zaposleni removeZaposleni(Zaposleni zaposleni) {
		getZaposlenis().remove(zaposleni);
		zaposleni.setRadnoMesto(null);

		return zaposleni;
	}

}