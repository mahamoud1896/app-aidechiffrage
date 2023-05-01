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
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Offre {
	
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(unique=true)
	@NotEmpty
	@Length(min = 2, max = 20)
	private String nomOffre;
	
	
	@OneToMany(mappedBy = "offre",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Collection<TypeProjet> typeProjets;


	public Offre() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Offre(String nomOffre) {
		super();
		this.nomOffre = nomOffre;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNomOffre() {
		return nomOffre;
	}


	public void setNomOffre(String nomOffre) {
		this.nomOffre = nomOffre;
	}


	public Collection<TypeProjet> getTypeProjets() {
		return typeProjets;
	}


	public void setTypeProjets(Collection<TypeProjet> typeProjets) {
		this.typeProjets = typeProjets;
	}


	@Override
	public String toString() {
		return "Offre [id=" + id + ", nomOffre=" + nomOffre + "]";
	}


	
	
	
	

}
