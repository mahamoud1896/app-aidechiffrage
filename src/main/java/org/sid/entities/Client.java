package org.sid.entities;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Client {
	
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotEmpty
	@Length(min = 2, max = 50)
	private String nomClient;
	
	
	
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
	private AdresseClient adresseClient;
	
	//@LazyCollection(LazyCollectionOption.FALSE)
	//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "client")
	private Collection<FicheProjet> ficheProjets;
	
	public String getNomClient() {
		return nomClient;
	}
	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}
	
	
	
	
	
	
	
	
	public AdresseClient getAdresseClient() {
		return adresseClient;
	}
	public void setAdresseClient(AdresseClient adresseClient) {
		this.adresseClient = adresseClient;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	public Collection<FicheProjet> getFicheProjets() {
		return ficheProjets;
	}
	public void setFicheProjets(Collection<FicheProjet> ficheProjets) {
		this.ficheProjets = ficheProjets;
	}
	
	
	
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Client(String nomClient, AdresseClient adresseClient) {
		super();
		this.nomClient = nomClient;
		this.adresseClient = adresseClient;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
