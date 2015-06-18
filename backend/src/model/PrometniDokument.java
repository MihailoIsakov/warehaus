package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the prometni_dokument database table.
 * 
 */
@Entity
@Table(name="prometni_dokument")
@NamedQuery(name="PrometniDokument.findAll", query="SELECT p FROM PrometniDokument p")
public class PrometniDokument implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_PROMETNI_DOKUMENT")
	private int idPrometniDokument;

	private int broj;

	@Temporal(TemporalType.DATE)
	@Column(name="DATUM_KNJIZENJA")
	private Date datumKnjizenja;

	@Temporal(TemporalType.DATE)
	@Column(name="DATUM_NASTANKA")
	private Date datumNastanka;

	@Column(name="STATUS_DOKUMENTA")
	private String statusDokumenta;

	//bi-directional many-to-one association to Magacin
	@ManyToOne
	@JoinColumn(name="ID_MAGACIN")
	private Magacin magacin1;

	//bi-directional many-to-one association to Magacin
	@ManyToOne
	@JoinColumn(name="MAG_ID_MAGACIN")
	private Magacin magacin2;

	//bi-directional many-to-one association to PoslovnaGodina
	@ManyToOne
	@JoinColumn(name="ID_POSLOVNA_GODINA")
	private PoslovnaGodina poslovnaGodina;

	//bi-directional many-to-one association to PoslovniPartner
	@ManyToOne
	@JoinColumn(name="ID_POSLOVNI_PARTNER")
	private PoslovniPartner poslovniPartner;
	
	@ManyToOne
	@JoinColumn(name="ID_VRSTA_DOKUMENTA")
	private VrstaDokumenta vrstaDokumenta;
	public VrstaDokumenta getVrstaDokumenta() {
		return vrstaDokumenta;
	}

	public void setVrstaDokumenta(VrstaDokumenta vrstaDokumenta) {
		this.vrstaDokumenta = vrstaDokumenta;
	}

	public PrometniDokument() {
	}

	public int getIdPrometniDokument() {
		return this.idPrometniDokument;
	}

	public void setIdPrometniDokument(int idPrometniDokument) {
		this.idPrometniDokument = idPrometniDokument;
	}

	public int getBroj() {
		return this.broj;
	}

	public void setBroj(int broj) {
		this.broj = broj;
	}

	public Date getDatumKnjizenja() {
		return this.datumKnjizenja;
	}

	public void setDatumKnjizenja(Date datumKnjizenja) {
		this.datumKnjizenja = datumKnjizenja;
	}

	public Date getDatumNastanka() {
		return this.datumNastanka;
	}

	public void setDatumNastanka(Date datumNastanka) {
		this.datumNastanka = datumNastanka;
	}

	public String getStatusDokumenta() {
		return this.statusDokumenta;
	}

	public void setStatusDokumenta(String statusDokumenta) {
		this.statusDokumenta = statusDokumenta;
	}

	public Magacin getMagacin1() {
		return this.magacin1;
	}

	public void setMagacin1(Magacin magacin1) {
		this.magacin1 = magacin1;
	}

	public Magacin getMagacin2() {
		return this.magacin2;
	}

	public void setMagacin2(Magacin magacin2) {
		this.magacin2 = magacin2;
	}

	public PoslovnaGodina getPoslovnaGodina() {
		return this.poslovnaGodina;
	}

	public void setPoslovnaGodina(PoslovnaGodina poslovnaGodina) {
		this.poslovnaGodina = poslovnaGodina;
	}

	public PoslovniPartner getPoslovniPartner() {
		return this.poslovniPartner;
	}

	public void setPoslovniPartner(PoslovniPartner poslovniPartner) {
		this.poslovniPartner = poslovniPartner;
	}



	
}