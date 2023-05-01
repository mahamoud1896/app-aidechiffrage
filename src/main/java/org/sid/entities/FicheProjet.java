package org.sid.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


@Entity(name="fiche_projet")
public class FicheProjet implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	//private static final long serialVersionUID = -7072168330829106498L;
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(unique=true, name="nom_projet")
	//@NotEmpty
	@NotEmpty
	@Length(min = 2, max = 20)
	private String nomProjet;
	@NotEmpty
	private String description;
	
	
	//private String respProjet;
	@DateTimeFormat(iso=ISO.DATE)
	
	private Date dateDebutPrev;
	@DateTimeFormat(iso=ISO.DATE)
	private Date dateFinPrev;
	@DateTimeFormat(iso=ISO.DATE)
	@Column(name="date_debut_effective")
	private Date dateDebutEffective;
	@DateTimeFormat(iso=ISO.DATE)
	@Column(name="date_fin_effective")
	private Date dateFinEffective;
	
	//@DateTimeFormat(iso=ISO.DATE)
	//private Date datePaiement;
	
	@Enumerated(EnumType.STRING)
    private EtatProjet etatProjet;
	
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY,mappedBy = "ficheProjet")
	private Collection<DepensesFixes> depensesFixes;
	
	
	//private Double objectifAnnuel;
	
	private String descSynthetique;
	
	private Double coutGlobal;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "ficheProjet")
	private Collection<Activite> activites;
	//@ManyToMany(mappedBy = "ficheProjets", fetch = FetchType.LAZY)
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name="ress_ficheProj",
			  joinColumns = {
					  @JoinColumn(name="ficheProjet_id", referencedColumnName="id", nullable = false, updatable = false)},
			  inverseJoinColumns = {
					  @JoinColumn(name="ressource_id", referencedColumnName="id", nullable = true, updatable = false)
			  })
	private Collection<Ressource> ressources;
	
	//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	//@JoinColumn(name = "client_id")
	private Client client;
	
	
	/* @Column(name = "typeProjetId")
	    @NotNull(message = "Select fiche project type!")
	    private Integer typeProjetId; */
	 
	 @ManyToOne(fetch = FetchType.LAZY, optional = false)
	 private TypeProjet typeProjet;
	
	
	
	@ManyToOne
	private User user;
	
	

	public FicheProjet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	



	public FicheProjet(String nomProjet, String description, Date dateDebutPrev, Date dateFinPrev,
			Date dateDebutEffective, Date dateFinEffective, EtatProjet etatProjet,
			Collection<DepensesFixes> depensesFixes, String descSynthetique, Double coutGlobal,
			Collection<Ressource> ressources, Client client, TypeProjet typeProjet) {
		super();
		this.nomProjet = nomProjet;
		this.description = description;
		this.dateDebutPrev = dateDebutPrev;
		this.dateFinPrev = dateFinPrev;
		this.dateDebutEffective = dateDebutEffective;
		this.dateFinEffective = dateFinEffective;
		this.etatProjet = etatProjet;
		this.depensesFixes = depensesFixes;
		this.descSynthetique = descSynthetique;
		this.coutGlobal = coutGlobal;
		this.ressources = ressources;
		this.client = client;
		this.typeProjet = typeProjet;
	}





	public FicheProjet(String nomProjet, String description, Date dateDebutPrev, Date dateFinPrev,
			Date dateDebutEffective, Date dateFinEffective, EtatProjet etatProjet,
			String descSynthetique, Double coutGlobal, Collection<Ressource> ressources, TypeProjet typeProjet) {
		super();
		this.nomProjet = nomProjet;
		this.description = description;
		this.dateDebutPrev = dateDebutPrev;
		this.dateFinPrev = dateFinPrev;
		this.dateDebutEffective = dateDebutEffective;
		this.dateFinEffective = dateFinEffective;
		
		this.etatProjet = etatProjet;
		this.descSynthetique = descSynthetique;
		this.coutGlobal = coutGlobal;
		this.ressources = ressources;
		this.typeProjet = typeProjet;
	}







	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNomProjet() {
		return nomProjet;
	}



	public void setNomProjet(String nomProjet) {
		this.nomProjet = nomProjet;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}





	public Date getDateDebutPrev() {
		return dateDebutPrev;
	}



	public void setDateDebutPrev(Date dateDebutPrev) {
		this.dateDebutPrev = dateDebutPrev;
	}



	public Date getDateFinPrev() {
		return dateFinPrev;
	}



	public void setDateFinPrev(Date dateFinPrev) {
		this.dateFinPrev = dateFinPrev;
	}



	public Date getDateDebutEffective() {
		return dateDebutEffective;
	}
	
	public long getNombreRess() {
		
		return getRessources().size();
	}
	
	
	public long getDelai() {
		
		long dateBeforeInMs = getDateFinEffective().getTime(); 
		long dateAfterInMs = getDateDebutEffective().getTime();
		
		long timeDiff = Math.abs(dateAfterInMs - dateBeforeInMs);
		
		long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
		 
		return daysDiff;
		
		//return getDateFinEffective().getTime() - getDateDebutEffective().getTime() ;
	}


	public void setDateDebutEffective(Date dateDebutEffective) {
		this.dateDebutEffective = dateDebutEffective;
	}



	public Date getDateFinEffective() {
		return dateFinEffective;
	}



	public void setDateFinEffective(Date dateFinEffective) {
		this.dateFinEffective = dateFinEffective;
	}



	public Collection<Activite> getActivites() {
		return activites;
	}



	public void setActivites(Collection<Activite> activites) {
		this.activites = activites;
	}



	public Collection<Ressource> getRessources() {
		return ressources;
	}



	public void setRessources(Collection<Ressource> ressources) {
		this.ressources = ressources;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}
	
	



	public TypeProjet getTypeProjet() {
		return typeProjet;
	}




	public void setTypeProjet(TypeProjet typeProjet) {
		this.typeProjet = typeProjet;
	}




	public String getDescSynthetique() {
		return descSynthetique;
	}




	public void setDescSynthetique(String descSynthetique) {
		this.descSynthetique = descSynthetique;
	}




	public Double getCoutGlobal() {
		return coutGlobal;
	}




	public void setCoutGlobal(Double coutGlobal) {
		this.coutGlobal = coutGlobal;
	}

	
	



	public EtatProjet getEtatProjet() {
		return etatProjet;
	}







	public void setEtatProjet(EtatProjet etatProjet) {
		this.etatProjet = etatProjet;
	}




	public Client getClient() {
		return client;
	}




	public void setClient(Client client) {
		this.client = client;
	}


	
	
	

	






	



	

	
	

	

}
