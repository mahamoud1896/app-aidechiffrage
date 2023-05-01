package org.sid.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.sid.entities.TypeActivite;

@Repository
public interface TypeActiviteRepository extends JpaRepository<TypeActivite, Long> {

}
