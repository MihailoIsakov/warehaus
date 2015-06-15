package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Set;


/**
 * The persistent class for the mesto database table.
 * 
 */
@Entity
@NamedQuery(name="Mesto.findAll", query="SELECT m FROM Mesto m")
public class Mesto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_MESTO")
	private int idMesto;

	@Column(name="NAZIV_MESTA")
	private String nazivMesta;

	@Column(name="PTT_BROJ")
	private String pttBroj;

	@Column(name="SIFRA_MESTA")
	private String sifraMesta;

	//bi-directional many-to-one association to Magacin
	@OneToMany(mappedBy="mesto", fetch=FetchType.EAGER)
	private Set<Magacin> magacins;

	//bi-directional many-to-one association to Drzava
	@ManyToOne
	@JoinColumn(name="ID_DRZAVA")
	private Drzava drzava;
	 @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	    @JoinColumn(name="mesto")

	//bi-directional many-to-one association to PoslovniPartner
	
	private Set<PoslovniPartner> poslovniPartners;
	 @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	    @JoinColumn(name="mesto")

	//bi-directional many-to-one association to Preduzece
	
	private Set<Preduzece> preduzeces;
	 @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	    @JoinColumn(name="mesto")

	//bi-directional many-to-one association to Zaposleni

	private Set<Zaposleni> zaposlenis;

	public Mesto() {
	}

	public int getIdMesto() {
		return this.idMesto;
	}

	public void setIdMesto(int idMesto) {
		this.idMesto = idMesto;
	}

	public String getNazivMesta() {
		return this.nazivMesta;
	}

	public void setNazivMesta(String nazivMesta) {
		this.nazivMesta = nazivMesta;
	}

	public String getPttBroj() {
		return this.pttBroj;
	}

	public void setPttBroj(String pttBroj) {
		this.pttBroj = pttBroj;
	}

	public String getSifraMesta() {
		return this.sifraMesta;
	}

	public void setSifraMesta(String sifraMesta) {
		this.sifraMesta = sifraMesta;
	}

	public Set<Magacin> getMagacins() {
		return this.magacins;
	}

	public void setMagacins(Set<Magacin> magacins) {
		this.magacins = magacins;
	}

	public Magacin addMagacin(Magacin magacin) {
		getMagacins().add(magacin);
		magacin.setMesto(this);

		return magacin;
	}

	public Magacin removeMagacin(Magacin magacin) {
		getMagacins().remove(magacin);
		magacin.setMesto(null);

		return magacin;
	}

	public Drzava getDrzava() {
		return this.drzava;
	}

	public void setDrzava(Drzava drzava) {
		this.drzava = drzava;
	}

	public Set<PoslovniPartner> getPoslovniPartners() {
		return this.poslovniPartners;
	}

	public void setPoslovniPartners(Set<PoslovniPartner> poslovniPartners) {
		this.poslovniPartners = poslovniPartners;
	}

	public PoslovniPartner addPoslovniPartner(PoslovniPartner poslovniPartner) {
		getPoslovniPartners().add(poslovniPartner);
		poslovniPartner.setMesto(this);

		return poslovniPartner;
	}

	public PoslovniPartner removePoslovniPartner(PoslovniPartner poslovniPartner) {
		getPoslovniPartners().remove(poslovniPartner);
		poslovniPartner.setMesto(null);

		return poslovniPartner;
	}

	public Set<Preduzece> getPreduzeces() {
		return this.preduzeces;
	}

	public void setPreduzeces(Set<Preduzece> preduzeces) {
		this.preduzeces = preduzeces;
	}

	public Preduzece addPreduzece(Preduzece preduzece) {
		getPreduzeces().add(preduzece);
		preduzece.setMesto(this);

		return preduzece;
	}

	public Preduzece removePreduzece(Preduzece preduzece) {
		getPreduzeces().remove(preduzece);
		preduzece.setMesto(null);

		return preduzece;
	}

	public Set<Zaposleni> getZaposlenis() {
		return this.zaposlenis;
	}

	public void setZaposlenis(Set<Zaposleni> zaposlenis) {
		this.zaposlenis = zaposlenis;
	}

	public Zaposleni addZaposleni(Zaposleni zaposleni) {
		getZaposlenis().add(zaposleni);
		zaposleni.setMesto(this);

		return zaposleni;
	}

	public Zaposleni removeZaposleni(Zaposleni zaposleni) {
		getZaposlenis().remove(zaposleni);
		zaposleni.setMesto(null);

		return zaposleni;
	}

}