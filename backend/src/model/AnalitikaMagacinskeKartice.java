package model;

import static javax.persistence.GenerationType.IDENTITY;

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
@NamedQueries({
	@NamedQuery(name="AnalitikaMagacinskeKartice.findAll", query="SELECT a FROM AnalitikaMagacinskeKartice a"),
	@NamedQuery(name="AnalitikaMagacinskeKartice.findByMagCardId", query="SELECT a FROM AnalitikaMagacinskeKartice a WHERE a.magacinskaKartica.idMagacinskaKartica like :id")
})
public class AnalitikaMagacinskeKartice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	 @GeneratedValue(strategy=IDENTITY)
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

	public enum smer{
		I,U
	}
	 @Enumerated(EnumType.STRING)
	private smer smer;

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
	

	public AnalitikaMagacinskeKartice() {
	}

	public AnalitikaMagacinskeKartice(PrometniDokument entity,
			StavkaPrometnogDokumenta stavkaPrometnogDokumenta2) {
		setCena(stavkaPrometnogDokumenta2.getCenaStavke());
		setDatumPromene(entity.getDatumNastanka());
		setKolicina(stavkaPrometnogDokumenta2.getKolicinaPrDokumenta());
		this.setStavkaPrometnogDokumenta(stavkaPrometnogDokumenta2);
		this.setVrednost(stavkaPrometnogDokumenta2.getVrednostStavke());
	}

	public AnalitikaMagacinskeKartice(
			StavkaPrometnogDokumenta stavkaPrometnogDokumenta2) {
	
		this.cena = stavkaPrometnogDokumenta2.getCenaStavke();
		this.datumPromene = stavkaPrometnogDokumenta2.getPrometniDokument().getDatumKnjizenja();
		this.kolicina = stavkaPrometnogDokumenta2.getKolicinaPrDokumenta();
		this.sifraDokumenta = String.valueOf(stavkaPrometnogDokumenta2.getPrometniDokument().getBroj());
		this.stavkaPrometnogDokumenta = stavkaPrometnogDokumenta2;
		this.vrednost = stavkaPrometnogDokumenta2.getVrednostStavke();
		
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

	
	public smer getSmer() {
		return smer;
	}

	public void setSmer(smer smer) {
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



}