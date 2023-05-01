package org.sid.dao;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.sid.entities.Ressource;



@Repository
public interface RessourceRepository extends JpaRepository<Ressource, Long> {

	
	
	// liste des ressources d'une fiche de projet
		 @Query("select r from Ressource r left join fetch r.ficheProjets f where f.id=?1")
		 List<Ressource> getAllFicheProjRess(Long idFicheProjet);
	
		 
		 @Query(value="select * from Ressource where id=:id ", nativeQuery=true)
			Ressource ressFicheProj(Long id);
}
