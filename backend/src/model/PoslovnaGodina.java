package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Set;


/**
 * The persistent class for the poslovna_godina database table.
 * 
 */
@Entity
@Table(name="poslovna_godina")
@NamedQuery(name="PoslovnaGodina.findAll", query="SELECT p FROM PoslovnaGodina p")
public class PoslovnaGodina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_POSLOVNA_GODINA")
	private int idPoslovnaGodina;

	@Column(name="BROJ_GODINE")
	private int brojGodine;

	@Column(name="ZAKLJUCENA_GODINA")
	private byte zakljucenaGodina;
	
	//bi-directional many-to-one association to Preduzece
	@ManyToOne
	@JoinColumn(name="ID_PREDUZECE")
	private Preduzece preduzece;
	
	public PoslovnaGodina() {
	}

	public int getIdPoslovnaGodina() {
		return this.idPoslovnaGodina;
	}

	public void setIdPoslovnaGodina(int idPoslovnaGodina) {
		this.idPoslovnaGodina = idPoslovnaGodina;
	}

	public int getBrojGodine() {
		return this.brojGodine;
	}

	public void setBrojGodine(int brojGodine) {
		this.brojGodine = brojGodine;
	}

	public byte getZakljucenaGodina() {
		return this.zakljucenaGodina;
	}

	public void setZakljucenaGodina(byte zakljucenaGodina) {
		this.zakljucenaGodina = zakljucenaGodina;
	}


}