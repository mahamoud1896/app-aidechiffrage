package org.sid.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Action {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String actionName;
	
	
	@ManyToMany(mappedBy = "actions", fetch = FetchType.LAZY)
	private Collection<Role> roles;
	
	
	
	public Action() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public Action(String actionName) {
		super();
		
		this.actionName = actionName;
	}



	



	public Collection<Role> getRoles() {
		return roles;
	}





	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}







	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getActionName() {
		return actionName;
	}




	public void setActionName(String actionName) {
		this.actionName = actionName;
	}




	@Override
	public String toString() {
		return "Action [id=" + id + ", actionName=" + actionName + "]";
	}
	
	
	
	
	
	
	

}
