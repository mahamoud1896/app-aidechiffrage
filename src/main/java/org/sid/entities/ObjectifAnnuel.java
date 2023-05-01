package org.sid.entities;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//import org.hibernate.validator.constraints.NotEmpty;

@Entity(name="parametres")
public class ObjectifAnnuel {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Double objectifAnuuel;
	@NotNull
	@Size(min=4)
	private String annee;
	
	
	
	public ObjectifAnnuel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
		
	public ObjectifAnnuel(Double objectifAnuuel, String annee) {
		super();
		this.objectifAnuuel = objectifAnuuel;
		this.annee = annee;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getObjectifAnuuel() {
		return objectifAnuuel;
	}
	public void setObjectifAnuuel(Double objectifAnuuel) {
		this.objectifAnuuel = objectifAnuuel;
	}
	public String getAnnee() {
		return annee;
	}
	public void setAnnee(String annee) {
		this.annee = annee;
	}


	@Override
	public String toString() {
		return "ObjectifAnnuel [id=" + id + ", objectifAnuuel=" + objectifAnuuel + ", annee=" + annee + "]";
	}
	
	
	

	
	
}
