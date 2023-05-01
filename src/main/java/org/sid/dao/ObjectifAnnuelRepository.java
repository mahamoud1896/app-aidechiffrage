package org.sid.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.sid.entities.ObjectifAnnuel;

@Repository
public interface ObjectifAnnuelRepository extends JpaRepository<ObjectifAnnuel, Long> {
	
	@Query(value="select objectif_anuuel from parametres ",
			nativeQuery=true)
	double objectifAnn();
	
	@Query(value="select annee from parametres ",
			nativeQuery=true)
	double anneeObjectif();
	
	@Query(value="select annee, objectif_anuuel as obj from parametres GROUP BY annee HAVING obj = ((select SUM(cout_global) from fiche_projet)/obj) ",
			nativeQuery=true)
	List<ObjectifAnnuel> objAnneeObjectif();

}
