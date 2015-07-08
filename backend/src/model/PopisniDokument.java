package model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the popisni_dokument database table.
 * 
 */
@Entity
@Table(name="popisni_dokument")
@NamedQuery(name="PopisniDokument.findAll", query="SELECT p FROM PopisniDokument p")
public class PopisniDokument implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	 @GeneratedValue(strategy=IDENTITY)
	@Column(name="ID_POPISNI_DOKUMENT")
	private int idPopisniDokument;

	@Column(name="BROJ_POPISA_U_GODNI")
	private int brojPopisaUGodni;

	@Temporal(TemporalType.DATE)
	@Column(name="DATUM_POPISA")
	private Date datumPopisa;

	@Column(name="SIFRA_POPISA")
	private String sifraPopisa;

	public enum statusPredaje {
		neproknjizen, proknjizen, zakljucen,
	}

	@Column(name="STATUS_PREDAJE")
	 @Enumerated(EnumType.STRING)
	private statusPredaje statusPredaje;
	public statusPredaje getStatusPredaje() {
		return statusPredaje;
	}

	public void setStatusPredaje(statusPredaje statusPredaje) {
		this.statusPredaje = statusPredaje;
	}

	//bi-directional many-to-one association to Magacin
	@ManyToOne
	@JoinColumn(name="ID_MAGACIN")
	private Magacin magacin;

	//bi-directional many-to-one association to PoslovnaGodina
	@ManyToOne
	@JoinColumn(name="ID_POSLOVNA_GODINA")
	private PoslovnaGodina poslovnaGodina;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="popisniDokument")
    private Set<StavkaPopisa> stavke = new HashSet<StavkaPopisa>();
	
	@ManyToOne
	@JoinColumn(name="ID_ZAPOSLENI1")
	private Zaposleni zaposleni1;
	
	@ManyToOne
	@JoinColumn(name="ID_ZAPOSLENI2")
	private Zaposleni zaposleni2;
	
	@ManyToOne
	@JoinColumn(name="ID_ZAPOSLENI3")
	private Zaposleni zaposleni3;

	public PopisniDokument() {
	}

	public int getIdPopisniDokument() {
		return this.idPopisniDokument;
	}

	public void setIdPopisniDokument(int idPopisniDokument) {
		this.idPopisniDokument = idPopisniDokument;
	}

	public int getBrojPopisaUGodni() {
		return this.brojPopisaUGodni;
	}

	public void setBrojPopisaUGodni(int brojPopisaUGodni) {
		this.brojPopisaUGodni = brojPopisaUGodni;
	}

	public Date getDatumPopisa() {
		return this.datumPopisa;
	}

	public void setDatumPopisa(Date datumPopisa) {
		this.datumPopisa = datumPopisa;
	}

	public String getSifraPopisa() {
		return this.sifraPopisa;
	}

	public void setSifraPopisa(String sifraPopisa) {
		this.sifraPopisa = sifraPopisa;
	}

	public Magacin getMagacin() {
		return this.magacin;
	}

	public void setMagacin(Magacin magacin) {
		this.magacin = magacin;
	}

	public PoslovnaGodina getPoslovnaGodina() {
		return this.poslovnaGodina;
	}

	public void setPoslovnaGodina(PoslovnaGodina poslovnaGodina) {
		this.poslovnaGodina = poslovnaGodina;
	}

	public Set<StavkaPopisa> getStavke() {
		return stavke;
	}

	public void setStavke(Set<StavkaPopisa> stavke) {
		this.stavke = stavke;
	}
	
	public Zaposleni getZaposleni1() {
		return this.zaposleni1;
	}

	public void setZaposleni1(Zaposleni zaposleni1) {
		this.zaposleni1 = zaposleni1;
	}
	
	public Zaposleni getZaposleni2() {
		return this.zaposleni2;
	}

	public void setZaposleni2(Zaposleni zaposleni2) {
		this.zaposleni2 = zaposleni2;
	}
	
	public Zaposleni getZaposleni3() {
		return this.zaposleni3;
	}

	public void setZaposleni3(Zaposleni zaposleni3) {
		this.zaposleni3 = zaposleni3;
	}

}
