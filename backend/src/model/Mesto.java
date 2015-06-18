package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Set;


/**
 * The persistent class for the mesto database table.
 * 
 */
@Entity
@NamedQuery(name="Mesto.findAll", query="SELECT m FROM Mesto m")
public class Mesto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_MESTO")
	private int idMesto;

	@Column(name="NAZIV_MESTA")
	private String nazivMesta;

	@Column(name="PTT_BROJ")
	private String pttBroj;

	@Column(name="SIFRA_MESTA")
	private String sifraMesta;
	@ManyToOne
	@JoinColumn(name="ID_DRZAVA")
	private Drzava drzava;
	
	public Mesto() {
	}

	public int getIdMesto() {
		return this.idMesto;
	}

	public void setIdMesto(int idMesto) {
		this.idMesto = idMesto;
	}

	public String getNazivMesta() {
		return this.nazivMesta;
	}

	public Drzava getDrzava() {
		return drzava;
	}

	public void setDrzava(Drzava drzava) {
		this.drzava = drzava;
	}

	public void setNazivMesta(String nazivMesta) {
		this.nazivMesta = nazivMesta;
	}

	public String getPttBroj() {
		return this.pttBroj;
	}

	public void setPttBroj(String pttBroj) {
		this.pttBroj = pttBroj;
	}

	public String getSifraMesta() {
		return this.sifraMesta;
	}

	public void setSifraMesta(String sifraMesta) {
		this.sifraMesta = sifraMesta;
	}

	
}