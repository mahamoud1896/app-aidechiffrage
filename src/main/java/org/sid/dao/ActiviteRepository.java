package org.sid.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.sid.entities.Activite;

@Repository
public interface ActiviteRepository extends JpaRepository<Activite, Long> {
	
	
	// liste des activites d'une fiche de projet
	 @Query("select a from Activite a where a.ficheProjet.id=?1")
	 List<Activite> getAllFicheProjActivities(Long idFicheProjet);


}
