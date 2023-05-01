package org.sid.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class DepensesFixes {
	
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String libelle;
	private double montant;
	
	//@ManyToOne(fetch = FetchType.LAZY, optional=false)
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(nullable = true)
	private FicheProjet ficheProjet;
	
	public DepensesFixes() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public DepensesFixes(String libelle, double montant, FicheProjet ficheProjet) {
		super();
		this.libelle = libelle;
		this.montant = montant;
		this.ficheProjet = ficheProjet;
	}




	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public FicheProjet getFicheProjet() {
		return ficheProjet;
	}

	public void setFicheProjet(FicheProjet ficheProjet) {
		this.ficheProjet = ficheProjet;
	}




	@Override
	public String toString() {
		return "DepensesFixes [id=" + id + ", libelle=" + libelle + ", montant=" + montant + ", ficheProjet="
				+ ficheProjet + "]";
	}
	
	
	
	
	
	

}
