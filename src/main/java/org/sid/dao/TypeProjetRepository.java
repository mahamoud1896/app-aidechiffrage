package org.sid.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TypeProjetRepository extends JpaRepository<org.sid.entities.TypeProjet, Long> {

}
