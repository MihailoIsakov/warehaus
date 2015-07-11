package model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
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
	 @GeneratedValue(strategy=IDENTITY)
	@Column(name="ID_POSLOVNA_GODINA")
	private int idPoslovnaGodina;

	@Column(name="BROJ_GODINE")
	private int brojGodine;

	@Column(name="ZAKLJUCENA_GODINA")
	private boolean zakljucenaGodina;
	
	//bi-directional many-to-one association to Preduzece
	@ManyToOne
	@JoinColumn(name="ID_PREDUZECE")
	private Preduzece preduzece;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy="poslovnaGodina")
	  private Set<PrometniDokument> promDoc = new HashSet<PrometniDokument>();
	
	
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

	public boolean getZakljucenaGodina() {
		return this.zakljucenaGodina;
	}

	public void setZakljucenaGodina(boolean zakljucenaGodina) {
		this.zakljucenaGodina = zakljucenaGodina;
	}

	public Preduzece getPreduzece() {
		return preduzece;
	}

	public void setPreduzece(Preduzece preduzece) {
		this.preduzece = preduzece;
	}

	public Set<PrometniDokument> getPromDoc() {
		return promDoc;
	}

	public void setPromDoc(Set<PrometniDokument> promDoc) {
		this.promDoc = promDoc;
	}


}