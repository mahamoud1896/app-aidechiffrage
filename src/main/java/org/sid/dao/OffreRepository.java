package org.sid.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.sid.entities.Offre;


public interface OffreRepository extends JpaRepository<Offre, Long> {

	@Query("SELECT o FROM Offre o WHERE CONCAT(o.nomOffre) LIKE %?1%")
	List<Offre> searchOffre(String keyword);
	
	/*
	 * @Query(value="select nom_offre, macolonne from offre  ", nativeQuery=true)
	 * List<Offre> objAnneeObjectif();
	 */
	
}
