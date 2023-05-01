package org.sid.entities;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="adresses")
public class AdresseClient {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable=false, length=50)
	private String pays;
	@Column(nullable=false, length=50)
	private String ville;
	@Column(nullable=false, length=50)
	private String nomAdresse;
	
	
	@OneToOne(mappedBy = "adresseClient")
	private Client client;
	
	
	/*
	 * @ManyToMany
	 * 
	 * @JoinTable( name="adresse_pays", joinColumns
	 * = @JoinColumn(name="adresse_id"), inverseJoinColumns
	 * = @JoinColumn(name="pays_id") ) private Collection<PaysClient> paysClients;
	 */



	public AdresseClient() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	



	public AdresseClient(String pays, String ville, String nomAdresse, Client client) {
		super();
		this.pays = pays;
		this.ville = ville;
		this.nomAdresse = nomAdresse;
		this.client = client;
	}




	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNomAdresse() {
		return nomAdresse;
	}



	public void setNomAdresse(String nomAdresse) {
		this.nomAdresse = nomAdresse;
	}



	public String getPays() {
		return pays;
	}



	public void setPays(String pays) {
		this.pays = pays;
	}



	public String getVille() {
		return ville;
	}



	public void setVille(String ville) {
		this.ville = ville;
	}



	public Client getClient() {
		return client;
	}



	public void setClient(Client client) {
		this.client = client;
	}



	
	

}
