
  package org.sid.entities;
  
  import java.util.Collection;
  
  
  import javax.persistence.Entity; import javax.persistence.FetchType; import
  javax.persistence.GeneratedValue; import javax.persistence.GenerationType;
  import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
  
  @Entity 
  public class RessActivite {
  
  
  
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  private Long duree;
  
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ressource_id")
  private Ressource ressAct;
  
  
  @ManyToOne(fetch = FetchType.LAZY, optional=false)
  @JoinColumn(name = "activite_id")
  private Activite activite;
  
  
  public RessActivite() { super(); // TODO Auto-generated constructor stub }
  
  }
  
  
  
  
  public RessActivite(Long duree, Ressource ressAct, Activite activite) {
	super();
	this.duree = duree;
	this.ressAct = ressAct;
	this.activite = activite;
}




public Long getId() { return id; }
  
  public void setId(Long id) { this.id = id; }
  
  public Long getDuree() { return duree; }
  
  public void setDuree(Long duree) { this.duree = duree; }






public Activite getActivite() {
	return activite;
}



public void setActivite(Activite activite) {
	this.activite = activite;
}




public Ressource getRessAct() {
	return ressAct;
}




public void setRessAct(Ressource ressAct) {
	this.ressAct = ressAct;
}




@Override
public String toString() {
	return "RessActivite [id=" + id + ", duree=" + duree + ", ressAct=" + ressAct + ", activite=" + activite + "]";
}







 
  
  
  
  
  }
 