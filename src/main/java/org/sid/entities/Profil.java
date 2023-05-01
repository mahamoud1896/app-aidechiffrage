package org.sid.entities;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

@Entity
public class Profil {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(unique=true)
	//@NotEmpty
	//@Size(min=2, max=20 , message = "fonction should have at least 2 characters")
	@Length(min = 2, max = 20)
	private String fonction;
	
	
	//@NotNull
	@Range(min = 8, max = 5000000)
	private double coutHommeHeure;
	
	//@NotNull
	@Range(min = 8, max = 5000000)
	private double coutHommeJour;
	
	//@NotNull
	@Range(min = 8, max = 5000000)
	private double marge;
	
	
	@OneToMany(mappedBy = "profil", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Collection<Ressource> ressources;
	
	

	public Profil() {
		super();
		// TODO Auto-generated constructor stub
	}

		
	

	public Profil(String fonction, double coutHommeHeure, double coutHommeJour, double marge) {
		super();
		this.fonction = fonction;
		
		this.coutHommeHeure = coutHommeHeure;
		this.coutHommeJour = coutHommeJour;
		this.marge = marge;
	}

	public String getFonction() {
		return fonction;
	}

	public void setFonction(String fonction) {
		this.fonction = fonction;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getCoutHommeHeure() {
		return coutHommeHeure;
	}

	public void setCoutHommeHeure(double coutHommeHeure) {
		this.coutHommeHeure = coutHommeHeure;
	}

	public double getCoutHommeJour() {
		return coutHommeJour;
	}

	public void setCoutHommeJour(double coutHommeJour) {
		this.coutHommeJour = coutHommeJour;
	}

	public double getMarge() {
		return marge;
	}

	public void setMarge(double marge) {
		this.marge = marge;
	}




	@Override
	public String toString() {
		return "Profil [id=" + id + ", fonction=" + fonction + ", coutHommeHeure=" + coutHommeHeure
				+ ", coutHommeJour=" + coutHommeJour + ", marge=" + marge + "]";
	}
	
	
	
	

}
