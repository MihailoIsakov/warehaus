package model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Set;


/**
 * The persistent class for the magacinska_kartica database table.
 * 
 */
@Entity
@Table(name="magacinska_kartica")
@NamedQuery(name="MagacinskaKartica.findAll", query="SELECT m FROM MagacinskaKartica m")
public class MagacinskaKartica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_MAGACINSKA_KARTICA")
	private int idMagacinskaKartica;

	@Column(name="KOL__IZLAZA")
	private BigDecimal kolIzlaza;

	@Column(name="KOL__ULAZA")
	private BigDecimal kolUlaza;

	@Column(name="POCETNO_STANJE_KOL")
	private BigDecimal pocetnoStanjeKol;

	@Column(name="POCETNO_STANJE_VR")
	private BigDecimal pocetnoStanjeVr;

	@Column(name="PROSECNA_CENA")
	private BigDecimal prosecnaCena;

	@Column(name="REDNI_BROJ_KARTICE")
	private int redniBrojKartice;

	@Column(name="VR__IZLAZA")
	private BigDecimal vrIzlaza;

	@Column(name="VR__ULAZA")
	private BigDecimal vrUlaza;
	 @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	    @JoinColumn(name="magacinskaKartica")

	//bi-directional many-to-one association to AnalitikaMagacinskeKartice

	private Set<AnalitikaMagacinskeKartice> analitikaMagacinskeKartices;

	//bi-directional many-to-one association to Artikal
	@ManyToOne
	@JoinColumn(name="ID_ARTIKAL")
	private Artikal artikal;

	//bi-directional many-to-one association to Magacin
	@ManyToOne
	@JoinColumn(name="ID_MAGACIN")
	private Magacin magacin;

	//bi-directional many-to-one association to PoslovnaGodina
	@ManyToOne
	@JoinColumn(name="ID_POSLOVNA_GODINA")
	private PoslovnaGodina poslovnaGodina;

	public MagacinskaKartica() {
	}

	public int getIdMagacinskaKartica() {
		return this.idMagacinskaKartica;
	}

	public void setIdMagacinskaKartica(int idMagacinskaKartica) {
		this.idMagacinskaKartica = idMagacinskaKartica;
	}

	public BigDecimal getKolIzlaza() {
		return this.kolIzlaza;
	}

	public void setKolIzlaza(BigDecimal kolIzlaza) {
		this.kolIzlaza = kolIzlaza;
	}

	public BigDecimal getKolUlaza() {
		return this.kolUlaza;
	}

	public void setKolUlaza(BigDecimal kolUlaza) {
		this.kolUlaza = kolUlaza;
	}

	public BigDecimal getPocetnoStanjeKol() {
		return this.pocetnoStanjeKol;
	}

	public void setPocetnoStanjeKol(BigDecimal pocetnoStanjeKol) {
		this.pocetnoStanjeKol = pocetnoStanjeKol;
	}

	public BigDecimal getPocetnoStanjeVr() {
		return this.pocetnoStanjeVr;
	}

	public void setPocetnoStanjeVr(BigDecimal pocetnoStanjeVr) {
		this.pocetnoStanjeVr = pocetnoStanjeVr;
	}

	public BigDecimal getProsecnaCena() {
		return this.prosecnaCena;
	}

	public void setProsecnaCena(BigDecimal prosecnaCena) {
		this.prosecnaCena = prosecnaCena;
	}

	public int getRedniBrojKartice() {
		return this.redniBrojKartice;
	}

	public void setRedniBrojKartice(int redniBrojKartice) {
		this.redniBrojKartice = redniBrojKartice;
	}

	public BigDecimal getVrIzlaza() {
		return this.vrIzlaza;
	}

	public void setVrIzlaza(BigDecimal vrIzlaza) {
		this.vrIzlaza = vrIzlaza;
	}

	public BigDecimal getVrUlaza() {
		return this.vrUlaza;
	}

	public void setVrUlaza(BigDecimal vrUlaza) {
		this.vrUlaza = vrUlaza;
	}

	public Set<AnalitikaMagacinskeKartice> getAnalitikaMagacinskeKartices() {
		return this.analitikaMagacinskeKartices;
	}

	public void setAnalitikaMagacinskeKartices(Set<AnalitikaMagacinskeKartice> analitikaMagacinskeKartices) {
		this.analitikaMagacinskeKartices = analitikaMagacinskeKartices;
	}

	public AnalitikaMagacinskeKartice addAnalitikaMagacinskeKartice(AnalitikaMagacinskeKartice analitikaMagacinskeKartice) {
		getAnalitikaMagacinskeKartices().add(analitikaMagacinskeKartice);
		analitikaMagacinskeKartice.setMagacinskaKartica(this);

		return analitikaMagacinskeKartice;
	}

	public AnalitikaMagacinskeKartice removeAnalitikaMagacinskeKartice(AnalitikaMagacinskeKartice analitikaMagacinskeKartice) {
		getAnalitikaMagacinskeKartices().remove(analitikaMagacinskeKartice);
		analitikaMagacinskeKartice.setMagacinskaKartica(null);

		return analitikaMagacinskeKartice;
	}

	public Artikal getArtikal() {
		return this.artikal;
	}

	public void setArtikal(Artikal artikal) {
		this.artikal = artikal;
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

}