package model;

import static javax.persistence.GenerationType.IDENTITY;

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
	 @GeneratedValue(strategy=IDENTITY)
	@Column(name="ID_RADNO_MESTO")
	private int idRadnoMesto;

	@Column(name="NAZIV_RADNOG_MESTA")
	private String nazivRadnogMesta;

	@Column(name="SIFRA_RADNOG_MESTA")
	private String sifraRadnogMesta;
	

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


}