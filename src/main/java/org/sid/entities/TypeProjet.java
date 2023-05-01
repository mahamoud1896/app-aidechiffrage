package org.sid.entities;


import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;



@Entity(name="metier")
public class TypeProjet {

	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "typeProjetId")
	private Long id;
	@Column(unique=true)
	@NotEmpty
	@Length(min = 2, max = 60)
	private String nom;
	
	// @OneToOne(mappedBy = "typeProjet")
	//private FicheProjet ficheProjet;
	
	@OneToMany(mappedBy = "typeProjet")
	private Collection<FicheProjet> ficheProjets;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(nullable = true)
	private Offre offre;

	public TypeProjet() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public TypeProjet(String nom, Offre offre) {
		super();
		this.nom = nom;
		this.offre = offre;
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
	
	
	



	@Override
	public String toString() {
		return "TypeProjet [id=" + id + ", nom=" + nom + ", offre=" + offre + "]";
	}



	public Offre getOffre() {
		return offre;
	}



	public void setOffre(Offre offre) {
		this.offre = offre;
	}

	

	
	
	
	
	 
}
