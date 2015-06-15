package model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Set;


/**
 * The persistent class for the stavka_prometnog_dokumenta database table.
 * 
 */
@Entity
@Table(name="stavka_prometnog_dokumenta")
@NamedQuery(name="StavkaPrometnogDokumenta.findAll", query="SELECT s FROM StavkaPrometnogDokumenta s")
public class StavkaPrometnogDokumenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_STAVKA_PROMETNOG_DOKUMENTA")
	private int idStavkaPrometnogDokumenta;

	@Column(name="CENA_STAVKE")
	private BigDecimal cenaStavke;

	@Column(name="KOLICINA_PR_DOKUMENTA")
	private BigDecimal kolicinaPrDokumenta;

	private int rbr;

	@Column(name="VREDNOST_STAVKE")
	private BigDecimal vrednostStavke;
	 @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	    @JoinColumn(name="stavkaPrometnogDokumenta")

	//bi-directional many-to-one association to AnalitikaMagacinskeKartice

	private Set<AnalitikaMagacinskeKartice> analitikaMagacinskeKartices;

	//bi-directional many-to-one association to Artikal
	@ManyToOne
	@JoinColumn(name="ID_ARTIKAL")
	private Artikal artikal;

	//bi-directional many-to-one association to PrometniDokument
	@ManyToOne
	@JoinColumn(name="ID_PROMETNI_DOKUMENT")
	private PrometniDokument prometniDokument;

	public StavkaPrometnogDokumenta() {
	}

	public int getIdStavkaPrometnogDokumenta() {
		return this.idStavkaPrometnogDokumenta;
	}

	public void setIdStavkaPrometnogDokumenta(int idStavkaPrometnogDokumenta) {
		this.idStavkaPrometnogDokumenta = idStavkaPrometnogDokumenta;
	}

	public BigDecimal getCenaStavke() {
		return this.cenaStavke;
	}

	public void setCenaStavke(BigDecimal cenaStavke) {
		this.cenaStavke = cenaStavke;
	}

	public BigDecimal getKolicinaPrDokumenta() {
		return this.kolicinaPrDokumenta;
	}

	public void setKolicinaPrDokumenta(BigDecimal kolicinaPrDokumenta) {
		this.kolicinaPrDokumenta = kolicinaPrDokumenta;
	}

	public int getRbr() {
		return this.rbr;
	}

	public void setRbr(int rbr) {
		this.rbr = rbr;
	}

	public BigDecimal getVrednostStavke() {
		return this.vrednostStavke;
	}

	public void setVrednostStavke(BigDecimal vrednostStavke) {
		this.vrednostStavke = vrednostStavke;
	}

	public Set<AnalitikaMagacinskeKartice> getAnalitikaMagacinskeKartices() {
		return this.analitikaMagacinskeKartices;
	}

	public void setAnalitikaMagacinskeKartices(Set<AnalitikaMagacinskeKartice> analitikaMagacinskeKartices) {
		this.analitikaMagacinskeKartices = analitikaMagacinskeKartices;
	}

	public AnalitikaMagacinskeKartice addAnalitikaMagacinskeKartice(AnalitikaMagacinskeKartice analitikaMagacinskeKartice) {
		getAnalitikaMagacinskeKartices().add(analitikaMagacinskeKartice);
		analitikaMagacinskeKartice.setStavkaPrometnogDokumenta(this);

		return analitikaMagacinskeKartice;
	}

	public AnalitikaMagacinskeKartice removeAnalitikaMagacinskeKartice(AnalitikaMagacinskeKartice analitikaMagacinskeKartice) {
		getAnalitikaMagacinskeKartices().remove(analitikaMagacinskeKartice);
		analitikaMagacinskeKartice.setStavkaPrometnogDokumenta(null);

		return analitikaMagacinskeKartice;
	}

	public Artikal getArtikal() {
		return this.artikal;
	}

	public void setArtikal(Artikal artikal) {
		this.artikal = artikal;
	}

	public PrometniDokument getPrometniDokument() {
		return this.prometniDokument;
	}

	public void setPrometniDokument(PrometniDokument prometniDokument) {
		this.prometniDokument = prometniDokument;
	}

}