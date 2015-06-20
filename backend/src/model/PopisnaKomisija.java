package model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the popisna_komisija database table.
 * 
 */
@Entity
@Table(name="popisna_komisija")
@NamedQuery(name="PopisnaKomisija.findAll", query="SELECT p FROM PopisnaKomisija p")
public class PopisnaKomisija implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	 @GeneratedValue(strategy=IDENTITY)
	@Column(name="ID_POPISNA_KOMISIJA")
	private int idPopisnaKomisija;

	@Column(name="SIFRA_KOMISIJE")
	private String sifraKomisije;

	//bi-directional many-to-one association to PopisniDokument
	@ManyToOne
	@JoinColumn(name="ID_POPISNI_DOKUMENT")
	private PopisniDokument popisniDokument;

	//bi-directional many-to-one association to Zaposleni
	@ManyToOne
	@JoinColumn(name="ZAP_ID_ZAPOSLENI")
	private Zaposleni zaposleni1;

	//bi-directional many-to-one association to Zaposleni
	@ManyToOne
	@JoinColumn(name="ID_ZAPOSLENI")
	private Zaposleni zaposleni2;

	//bi-directional many-to-one association to Zaposleni
	@ManyToOne
	@JoinColumn(name="ZAP_ID_ZAPOSLENI2")
	private Zaposleni zaposleni3;

	public PopisnaKomisija() {
	}

	public int getIdPopisnaKomisija() {
		return this.idPopisnaKomisija;
	}

	public void setIdPopisnaKomisija(int idPopisnaKomisija) {
		this.idPopisnaKomisija = idPopisnaKomisija;
	}

	public String getSifraKomisije() {
		return this.sifraKomisije;
	}

	public void setSifraKomisije(String sifraKomisije) {
		this.sifraKomisije = sifraKomisije;
	}

	public PopisniDokument getPopisniDokument() {
		return this.popisniDokument;
	}

	public void setPopisniDokument(PopisniDokument popisniDokument) {
		this.popisniDokument = popisniDokument;
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