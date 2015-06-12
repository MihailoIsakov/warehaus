package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the stavka_popisa database table.
 * 
 */
@Entity
@Table(name="stavka_popisa")
@NamedQuery(name="StavkaPopisa.findAll", query="SELECT s FROM StavkaPopisa s")
public class StavkaPopisa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_STAVKA_POPISA")
	private int idStavkaPopisa;

	@Column(name="CENA_AKTUELNA_PRI_POPISU")
	private BigDecimal cenaAktuelnaPriPopisu;

	@Column(name="KOLICINA_PO_POPISU")
	private BigDecimal kolicinaPoPopisu;

	@Column(name="KOLICINA_U_KARTICI")
	private BigDecimal kolicinaUKartici;

	@Column(name="REDNI_BROJ_STAVKE")
	private int redniBrojStavke;

	@Column(name="VREDNOST_PO_POPISU")
	private BigDecimal vrednostPoPopisu;

	@Column(name="VREDNOST_U_KARTICI")
	private BigDecimal vrednostUKartici;

	//bi-directional many-to-one association to Artikal
	@ManyToOne
	@JoinColumn(name="ID_ARTIKAL")
	private Artikal artikal;

	//bi-directional many-to-one association to PopisniDokument
	@ManyToOne
	@JoinColumn(name="ID_POPISNI_DOKUMENT")
	private PopisniDokument popisniDokument;

	public StavkaPopisa() {
	}

	public int getIdStavkaPopisa() {
		return this.idStavkaPopisa;
	}

	public void setIdStavkaPopisa(int idStavkaPopisa) {
		this.idStavkaPopisa = idStavkaPopisa;
	}

	public BigDecimal getCenaAktuelnaPriPopisu() {
		return this.cenaAktuelnaPriPopisu;
	}

	public void setCenaAktuelnaPriPopisu(BigDecimal cenaAktuelnaPriPopisu) {
		this.cenaAktuelnaPriPopisu = cenaAktuelnaPriPopisu;
	}

	public BigDecimal getKolicinaPoPopisu() {
		return this.kolicinaPoPopisu;
	}

	public void setKolicinaPoPopisu(BigDecimal kolicinaPoPopisu) {
		this.kolicinaPoPopisu = kolicinaPoPopisu;
	}

	public BigDecimal getKolicinaUKartici() {
		return this.kolicinaUKartici;
	}

	public void setKolicinaUKartici(BigDecimal kolicinaUKartici) {
		this.kolicinaUKartici = kolicinaUKartici;
	}

	public int getRedniBrojStavke() {
		return this.redniBrojStavke;
	}

	public void setRedniBrojStavke(int redniBrojStavke) {
		this.redniBrojStavke = redniBrojStavke;
	}

	public BigDecimal getVrednostPoPopisu() {
		return this.vrednostPoPopisu;
	}

	public void setVrednostPoPopisu(BigDecimal vrednostPoPopisu) {
		this.vrednostPoPopisu = vrednostPoPopisu;
	}

	public BigDecimal getVrednostUKartici() {
		return this.vrednostUKartici;
	}

	public void setVrednostUKartici(BigDecimal vrednostUKartici) {
		this.vrednostUKartici = vrednostUKartici;
	}

	public Artikal getArtikal() {
		return this.artikal;
	}

	public void setArtikal(Artikal artikal) {
		this.artikal = artikal;
	}

	public PopisniDokument getPopisniDokument() {
		return this.popisniDokument;
	}

	public void setPopisniDokument(PopisniDokument popisniDokument) {
		this.popisniDokument = popisniDokument;
	}

}