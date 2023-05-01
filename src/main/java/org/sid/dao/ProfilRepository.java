package org.sid.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.sid.entities.Profil;

@Repository
public interface ProfilRepository extends JpaRepository<Profil, Long> {

	@Query("SELECT p FROM Profil p WHERE CONCAT(p.fonction, ' ', p.coutHommeHeure, ' ', p.coutHommeJour, ' ', p.marge) LIKE %?1%")
	List<Profil> searchProfil(String keyword);
}
