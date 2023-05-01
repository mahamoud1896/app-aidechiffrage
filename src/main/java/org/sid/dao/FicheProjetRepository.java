package org.sid.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.sid.entities.EtatProjet;
import org.sid.entities.FicheProjet;

@Repository
public interface FicheProjetRepository extends JpaRepository<FicheProjet, Long> {
	
	
	@Query(value="select nom_projet,  DATEDIFF(date_fin_effective, date_debut_effective) AS date_difference from fiche_projet GROUP BY nom_projet",
			nativeQuery=true)
	List<Object> getDelaiByProjet();
	
	@Query(value="select nom_projet,  DATEDIFF(date_fin_prev, date_debut_prev) AS date_diff from fiche_projet GROUP BY nom_projet",
			nativeQuery=true)
	List<Object> getDelaiByProject();
	
	@Query(value="select nom_offre,  as nomOffer, (select type_projet_type_projet_id,  SUM(cout_global) AS somme from fiche_projet GROUP BY type_projet_type_projet_id)",
			nativeQuery=true)
	List<Object> getMontantByOffre();
	
	@Query(value="select nom_offre,  SUM(cout_global) from offre, fiche_projet, metier WHERE offre.id = metier.offre_id AND metier.type_projet_id = fiche_projet.type_projet_type_projet_id GROUP BY nom_offre ",
			nativeQuery=true)
	List<Object> getMontantByOffres();
	
	@Query(value="select COUNT(nom_projet), AVG(DATEDIFF(date_fin_prev, date_debut_prev)),AVG(DATEDIFF(date_fin_effective, date_debut_effective)) from fiche_projet",
			nativeQuery=true)
	List<Object> getDelaiMoyen();
	
	@Query(value="select COUNT(type_projet_type_projet_id) from fiche_projet GROUP BY type_projet_type_projet_id",
			nativeQuery=true)
	List<Object> offreProgress();
	
	@Query(value="select SUM(cout_global) from fiche_projet ",
			nativeQuery=true)
	double pourcentageObjectif();
	
	
//	@Query(value="select",
//			nativeQuery=true)
//	Long ressFicheProjetId(Long idProjet);
	
	FicheProjet findByEtatProjet(EtatProjet etatProjet);
	
	
	
	

}
