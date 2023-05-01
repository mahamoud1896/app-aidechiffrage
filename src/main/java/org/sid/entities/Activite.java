package org.sid.entities;


import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

//import com.chiffrageTest.service.IchiffrageService;


@Entity
public class Activite {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotEmpty
	@Length(min = 2, max = 20)
	private String titre;
	@Range(min = 1, max = 365)
	private double joursHommeConsommes;
	@Range(min = 1, max = 365)
	private double joursHommePrevus;
	
	
	//private double cout;
	//private double duree;
	//@DateTimeFormat(iso=ISO.DATE)
	//private Date dateCreation;
	@DateTimeFormat(iso=ISO.DATE)
	private Date dateDebutActivite;
	@DateTimeFormat(iso=ISO.DATE)
	private Date dateFinActivite;
	private String description;
	
	//@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@ManyToOne(fetch = FetchType.LAZY, optional=false)
	@JoinColumn(name = "fiche_projet_id")
	private FicheProjet ficheProjet;
	
	
	@Column(name = "fiche_projet_id", insertable = false, updatable = false)
	private Long ficheprojetId;
	
	 
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "activite")
	private Collection<RessActivite> ressActivites;
	
		
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private TypeActivite typeActivite;


	public Activite() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	


	public Activite(String titre, double joursHommeConsommes, double joursHommePrevus, Date dateDebutActivite,
			Date dateFinActivite, String description, Long ficheprojetId, TypeActivite typeActivite) {
		super();
		this.titre = titre;
		this.joursHommeConsommes = joursHommeConsommes;
		this.joursHommePrevus = joursHommePrevus;
		this.dateDebutActivite = dateDebutActivite;
		this.dateFinActivite = dateFinActivite;
		this.description = description;
		this.ficheprojetId = ficheprojetId;
		this.typeActivite = typeActivite;
	}



	public Date getDateDebutActivite() {
		return dateDebutActivite;
	}



	public void setDateDebutActivite(Date dateDebutActivite) {
		this.dateDebutActivite = dateDebutActivite;
	}



	public Date getDateFinActivite() {
		return dateFinActivite;
	}



	public void setDateFinActivite(Date dateFinActivite) {
		this.dateFinActivite = dateFinActivite;
	}



	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	

	public String getTitre() {
		return titre;
	}


	public void setTitre(String titre) {
		this.titre = titre;
	}


	public double getJoursHommeConsommes() {
		return joursHommeConsommes;
	}


	public void setJoursHommeConsommes(double joursHommeConsommes) {
		this.joursHommeConsommes = joursHommeConsommes;
	}


	

	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public FicheProjet getFicheProjet() {
		return ficheProjet;
	}


	public void setFicheProjet(FicheProjet ficheProjet) {
		this.ficheProjet = ficheProjet;
	}
	
	


	public double getJoursHommePrevus() {
		return joursHommePrevus;
	}





	public void setJoursHommePrevus(double joursHommePrevus) {
		this.joursHommePrevus = joursHommePrevus;
	}



	public TypeActivite getTypeActivite() {
		return typeActivite;
	}





	public void setTypeActivite(TypeActivite typeActivite) {
		this.typeActivite = typeActivite;
	}
	


	




	public Long getFicheprojetId() {
		return ficheprojetId;
	}
	
	
	




	/*
	 * @Override public String toString() { return "Activite [id=" + id + ", titre="
	 * + titre + ", joursHommeConsommes=" + joursHommeConsommes +
	 * ", joursHommePrevus=" + joursHommePrevus + ", dateCreation=" + dateCreation +
	 * ", description=" + description + ", ficheprojetId=" + ficheprojetId +
	 * ", typeActivite=" + typeActivite + "]"; }
	 */



	@Override
	public String toString() {
		return "Activite [titre=" + titre + "]";
	}



	public Collection<RessActivite> getRessActivites() {
		return ressActivites;
	}



	public void setRessActivites(Collection<RessActivite> ressActivites) {
		this.ressActivites = ressActivites;
	}

	
	



	/*
	 * public void setFicheprojetId(long ficheprojetId) { this.ficheprojetId =
	 * ficheprojetId; }
	 */




	





	
	
	
	
	

	
	
	
	
	

}
