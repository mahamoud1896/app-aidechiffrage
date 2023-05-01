package org.sid.entities;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Role {

	public static final String ROLE_USER = "ROLE_USER";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String roleName;
	
	@ManyToMany(mappedBy = "roles")
    private Collection<User> users;
	
	/*@OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
	private Collection<User> users;*/
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(name="roles_actions",
			  joinColumns = {
					  @JoinColumn(name="role_id", referencedColumnName="id", nullable = false, updatable = false)},
			  inverseJoinColumns = {
					  @JoinColumn(name="action_id", referencedColumnName="id", nullable = false, updatable = false)
			  })
	private Collection<Action> actions;
	
	
	
	
	
	
	
	
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Role(String roleName, Collection<Action> actions) {
		super();
		this.roleName = roleName;
		this.actions = actions;
	}

	
	


	public Role(String roleName) {
		super();
		this.roleName = roleName;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Collection<User> getUsers() {
		return users;
	}
	public void setUsers(Collection<User> users) {
		this.users = users;
	}
	public Collection<Action> getActions() {
		return actions;
	}
	public void setActions(Collection<Action> actions) {
		this.actions = actions;
	}



	@Override
	public String toString() {
		return "Role [id=" + id + ", roleName=" + roleName + ", actions=" + actions + "]";
	}
	
	
	
	
	
	
	
}
