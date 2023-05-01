package org.sid.entities;

import java.util.Collection;
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
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;




@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "TYPE_RESS",discriminatorType = DiscriminatorType.STRING, length = 3)
public class Ressource {
	
	
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotEmpty
	@Length(min = 2, max = 100)
	private String nom;
	
	@NotEmpty
	@Length(min = 2, max = 100)
	private String prenom;
	//private String typeRessource;
		
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(nullable = true)
	private Profil profil;
	//@JoinColumn(name = "profilId", referencedColumnName = "id")
	
	
	
	 //@Column(name = "userId")
	 //private Integer userId;
	 
	 
	@OneToOne(fetch = FetchType.LAZY, optional = true, cascade = 
		    CascadeType.ALL)
	@JoinColumn(name = "user_ressource")
	@JsonIgnore
	private User user;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "ressAct")
	private Collection<RessActivite> ressActivites;
	
	
	
	@ManyToMany(mappedBy = "ressources", fetch = FetchType.LAZY)
	private Collection<FicheProjet> ficheProjets;

	public Ressource() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

	public Ressource(String nom, String prenom,  Profil profil) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		
		this.profil = profil;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	

	public String getProfile() {
		return profil.getFonction();
	}
	
	public Profil getProfil() {
		return profil;
	}

	public void setProfil(Profil profil) {
		this.profil = profil;
	}


	

	public Collection<FicheProjet> getFicheProjets() {
		return ficheProjets;
	}

	public void setFicheProjets(Collection<FicheProjet> ficheProjets) {
		this.ficheProjets = ficheProjets;
	}
	
	
	
	

	@Override
	public String toString() {
		return "Ressource [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", profil=" + profil + "]";
	}








	public Collection<RessActivite> getRessActivites() {
		return ressActivites;
	}








	public void setRessActivites(Collection<RessActivite> ressActivites) {
		this.ressActivites = ressActivites;
	}






	/*
	 * 
	 * public String getTypeRessource() { return typeRessource; }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * public void setTypeRessource(String typeRessource) { this.typeRessource =
	 * typeRessource; }
	 */







	public User getUser() {
		return user;
	}








	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
	

	
	
	

}
