package org.sid.entities;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
public class TypeActivite {

	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "type_activite_id")
	private Long id;
	@NotEmpty
	@Length(min = 2, max = 20)
	private String nomActivite;
	
	
	
	@OneToMany(mappedBy = "typeActivite")
	private Collection<Activite> activites;



	public TypeActivite() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	

	public TypeActivite(Long id, String nomActivite) {
		super();
		this.id = id;
		this.nomActivite = nomActivite;
	}

	



	public TypeActivite(String nomActivite) {
		super();
		this.nomActivite = nomActivite;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNomActivite() {
		return nomActivite;
	}



	public void setNomActivite(String nomActivite) {
		this.nomActivite = nomActivite;
	}





	@Override
	public String toString() {
		return "TypeActivite [id=" + id + ", nomActivite=" + nomActivite + "]";
	}



	
	 
	 
	 
	 
	
	
}
