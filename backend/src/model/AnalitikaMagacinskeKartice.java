package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the analitika_magacinske_kartice database table.
 * 
 */
@Entity
@Table(name="analitika_magacinske_kartice")
@NamedQuery(name="AnalitikaMagacinskeKartice.findAll", query="SELECT a FROM AnalitikaMagacinskeKartice a")
public class AnalitikaMagacinskeKartice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_ANALITIKA_MAGACINSKE_KARTICE")
	private int idAnalitikaMagacinskeKartice;

	private BigDecimal cena;

	@Temporal(TemporalType.DATE)
	@Column(name="DATUM_PROMENE")
	private Date datumPromene;

	private BigDecimal kolicina;

	@Column(name="REDNI_BROJ")
	private int redniBroj;

	@Column(name="SIFRA_DOKUMENTA")
	private String sifraDokumenta;

	private String smer;

	private BigDecimal vrednost;

	//bi-directional many-to-one association to MagacinskaKartica
	@ManyToOne
	@JoinColumn(name="ID_MAGACINSKA_KARTICA")
	private MagacinskaKartica magacinskaKartica;

	//bi-directional many-to-one association to StavkaPrometnogDokumenta
	@ManyToOne
	@JoinColumn(name="ID_STAVKA_PROMETNOG_DOKUMENTA")
	private StavkaPrometnogDokumenta stavkaPrometnogDokumenta;

	//bi-directional many-to-one association to VrstaDokumenta
	@ManyToOne
	@JoinColumn(name="ID_VRSTA_DOKUMENTA")
	private VrstaDokumenta vrstaDokumenta;

	public AnalitikaMagacinskeKartice() {
	}

	public int getIdAnalitikaMagacinskeKartice() {
		return this.idAnalitikaMagacinskeKartice;
	}

	public void setIdAnalitikaMagacinskeKartice(int idAnalitikaMagacinskeKartice) {
		this.idAnalitikaMagacinskeKartice = idAnalitikaMagacinskeKartice;
	}

	public BigDecimal getCena() {
		return this.cena;
	}

	public void setCena(BigDecimal cena) {
		this.cena = cena;
	}

	public Date getDatumPromene() {
		return this.datumPromene;
	}

	public void setDatumPromene(Date datumPromene) {
		this.datumPromene = datumPromene;
	}

	public BigDecimal getKolicina() {
		return this.kolicina;
	}

	public void setKolicina(BigDecimal kolicina) {
		this.kolicina = kolicina;
	}

	public int getRedniBroj() {
		return this.redniBroj;
	}

	public void setRedniBroj(int redniBroj) {
		this.redniBroj = redniBroj;
	}

	public String getSifraDokumenta() {
		return this.sifraDokumenta;
	}

	public void setSifraDokumenta(String sifraDokumenta) {
		this.sifraDokumenta = sifraDokumenta;
	}

	public String getSmer() {
		return this.smer;
	}

	public void setSmer(String smer) {
		this.smer = smer;
	}

	public BigDecimal getVrednost() {
		return this.vrednost;
	}

	public void setVrednost(BigDecimal vrednost) {
		this.vrednost = vrednost;
	}

	public MagacinskaKartica getMagacinskaKartica() {
		return this.magacinskaKartica;
	}

	public void setMagacinskaKartica(MagacinskaKartica magacinskaKartica) {
		this.magacinskaKartica = magacinskaKartica;
	}

	public StavkaPrometnogDokumenta getStavkaPrometnogDokumenta() {
		return this.stavkaPrometnogDokumenta;
	}

	public void setStavkaPrometnogDokumenta(StavkaPrometnogDokumenta stavkaPrometnogDokumenta) {
		this.stavkaPrometnogDokumenta = stavkaPrometnogDokumenta;
	}

	public VrstaDokumenta getVrstaDokumenta() {
		return this.vrstaDokumenta;
	}

	public void setVrstaDokumenta(VrstaDokumenta vrstaDokumenta) {
		this.vrstaDokumenta = vrstaDokumenta;
	}

}