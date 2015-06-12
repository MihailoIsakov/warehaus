package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the drzava database table.
 * 
 */
@Entity
@NamedQuery(name="Drzava.findAll", query="SELECT d FROM Drzava d")
public class Drzava implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_DRZAVA")
	private int idDrzava;

	@Column(name="NAZIV_DRZAVE")
	private String nazivDrzave;

	@Column(name="SIFRA_DRZAVE")
	private String sifraDrzave;

	//bi-directional many-to-one association to Mesto
	@OneToMany(mappedBy="drzava")
	private List<Mesto> mestos;

	public Drzava() {
	}

	public int getIdDrzava() {
		return this.idDrzava;
	}

	public void setIdDrzava(int idDrzava) {
		this.idDrzava = idDrzava;
	}

	public String getNazivDrzave() {
		return this.nazivDrzave;
	}

	public void setNazivDrzave(String nazivDrzave) {
		this.nazivDrzave = nazivDrzave;
	}

	public String getSifraDrzave() {
		return this.sifraDrzave;
	}

	public void setSifraDrzave(String sifraDrzave) {
		this.sifraDrzave = sifraDrzave;
	}

	public List<Mesto> getMestos() {
		return this.mestos;
	}

	public void setMestos(List<Mesto> mestos) {
		this.mestos = mestos;
	}

	public Mesto addMesto(Mesto mesto) {
		getMestos().add(mesto);
		mesto.setDrzava(this);

		return mesto;
	}

	public Mesto removeMesto(Mesto mesto) {
		getMestos().remove(mesto);
		mesto.setDrzava(null);

		return mesto;
	}

}