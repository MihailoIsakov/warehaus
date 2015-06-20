package model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import model.AnalitikaMagacinskeKartice.smer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
 * The persistent class for the magacinska_kartica database table.
 * 
 */
@Entity
@Table(name="magacinska_kartica")
@NamedQueries({
@NamedQuery(name="MagacinskaKartica.findAll", query="SELECT m FROM MagacinskaKartica m"),
@NamedQuery(name="MagacinskaKartica.findByMagacin", query="SELECT k FROM MagacinskaKartica k WHERE k.magacin.idMagacin like :id"),
@NamedQuery(name="MagacinskaKartica.findByMagaciniArtikaliPG", query="FROM MagacinskaKartica k WHERE k.magacin.idMagacin like :idMagacin and k.artikal.idArtikal like :idArtikal and k.poslovnaGodina.idPoslovnaGodina like :idPG")
})
@JsonInclude(Include.NON_NULL)
public class MagacinskaKartica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	 @GeneratedValue(strategy=IDENTITY)
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
		//bi-directional many-to-one association to Magacin
	@ManyToOne
	@JoinColumn(name="ID_MAGACIN")
	private Magacin magacin;

	@ManyToOne
	@JoinColumn(name="ID_ARTIKAL")
	private Artikal artikal;
	
	@ManyToOne
	@JoinColumn(name="ID_POSLOVNA_GODINA")
	private PoslovnaGodina poslovnaGodina;
	
	public MagacinskaKartica() {
	}

	
	public MagacinskaKartica(StavkaPrometnogDokumenta stavkaPrometnogDokumenta, smer smer) {
		
		if(smer.equals(model.AnalitikaMagacinskeKartice.smer.I)){
			this.setKolIzlaza(stavkaPrometnogDokumenta.getKolicinaPrDokumenta());
			this.setVrIzlaza(stavkaPrometnogDokumenta.getVrednostStavke());
			this.setKolUlaza(new BigDecimal(0));
			this.setVrUlaza(new BigDecimal(0));
			this.setArtikal(stavkaPrometnogDokumenta.getArtikal());
			this.setPocetnoStanjeKol(new BigDecimal(0));
			this.setPocetnoStanjeVr(new BigDecimal(0));
			this.setProsecnaCena(stavkaPrometnogDokumenta.getCenaStavke());
		
		
		}
		else{
			this.setKolUlaza(this.getKolUlaza().add(stavkaPrometnogDokumenta.getKolicinaPrDokumenta()));
			this.setVrUlaza(vrUlaza.add(stavkaPrometnogDokumenta.getVrednostStavke()));
		}
		this.setProsecnaCena(vrIzlaza.add(vrUlaza).add(pocetnoStanjeVr).divide(kolIzlaza.add(kolUlaza).add(pocetnoStanjeKol), 2, RoundingMode.HALF_UP));
		
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

	public Artikal getArtikal() {
		return artikal;
	}

	public void setArtikal(Artikal artikal) {
		this.artikal = artikal;
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

	public PoslovnaGodina getPoslovnaGodina() {
		return poslovnaGodina;
	}


	public void setPoslovnaGodina(PoslovnaGodina poslovnaGodina) {
		this.poslovnaGodina = poslovnaGodina;
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




	public Magacin getMagacin() {
		return this.magacin;
	}

	public void setMagacin(Magacin magacin) {
		this.magacin = magacin;
	}


	public void update(StavkaPrometnogDokumenta stavkaPrometnogDokumenta, smer smer) {
		if(smer.equals(model.AnalitikaMagacinskeKartice.smer.I)){
			this.setKolIzlaza(this.getKolIzlaza().add(stavkaPrometnogDokumenta.getKolicinaPrDokumenta()));
			this.setVrIzlaza(vrIzlaza.add(stavkaPrometnogDokumenta.getVrednostStavke()));
		
		}
		else{
			this.setKolUlaza(this.getKolUlaza().add(stavkaPrometnogDokumenta.getKolicinaPrDokumenta()));
			this.setVrUlaza(vrUlaza.add(stavkaPrometnogDokumenta.getVrednostStavke()));
		}
		this.setProsecnaCena(vrIzlaza.add(vrUlaza).add(pocetnoStanjeVr).divide(kolIzlaza.add(kolUlaza).add(pocetnoStanjeKol), 2, RoundingMode.HALF_UP));
		
	}


}