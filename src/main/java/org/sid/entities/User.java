package org.sid.entities;

import java.util.Collection;
import java.util.Iterator;

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

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class User {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userId")
	private Long userId;
	@Column(nullable = false, unique = true, length = 45)
	private String username;
	//private String email;
	private String password;
	private Boolean active;
	//private Boolean accountVerified;
	//private int failedLoginAttempts;
	@Enumerated(EnumType.STRING)
    private Provider provider;
 
    public Provider getProvider() {
        return provider;
    }
 
    public void setProvider(Provider provider) {
        this.provider = provider;
    }
	
	@OneToMany(mappedBy = "user")
	private Collection<FicheProjet> ficheProjets;
	
	
	/*@ManyToOne
	@JoinColumn(name="USER_ROLE")
	private Role role; */
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST) 
    @JoinTable( 
        name = "users_roles", 
        joinColumns = @JoinColumn(
          name = "user_id", referencedColumnName = "userId"), 
        inverseJoinColumns = @JoinColumn(
          name = "role_id", referencedColumnName = "id")) 
    private Collection<Role> roles;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade 
	        = CascadeType.ALL)
	private Ressource ressource;


	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

	public User(String username, String password, Boolean active, Collection<Role> roles) {
		super();
		this.username = username;
		this.password = password;
		this.active = active;
		this.roles = roles;
	}




	public String getUsername() {
		return username;
	}


	public  void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Boolean getActive() {
		return active;
	}


	public void setActive(Boolean active) {
		this.active = active;
	}


	public Collection<FicheProjet> getFicheProjets() {
		return ficheProjets;
	}


	public void setFicheProjets(Collection<FicheProjet> ficheProjets) {
		this.ficheProjets = ficheProjets;
	}


	



	public Long getUserId() {
		return userId;
	}





	public void setUserId(Long userId) {
		this.userId = userId;
	}





	public Collection<Role> getRoles() {
		return roles;
	}




	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}




	



	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", active=" + active
				+ ", roles=" + roles + "]";
	}

	public boolean hasRole(String roleName) {
		// TODO Auto-generated method stub
		 Iterator<Role> iterator = this.roles.iterator();
	        while (iterator.hasNext()) {
	            Role role = iterator.next();
	            if (role.getRoleName().equals(roleName)) {
	                return true;
	            }
	        }
		return false;
	}

	public Ressource getRessource() {
		return ressource;
	}

	public void setRessource(Ressource ressource) {
		this.ressource = ressource;
	}
	
	
	public void addRole(Role role) {
		
		this.roles.add(role);
	}
	
	
	

	
	

}
