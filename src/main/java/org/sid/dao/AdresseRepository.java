package org.sid.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.sid.entities.AdresseClient;

public interface AdresseRepository extends JpaRepository<AdresseClient, Long> {

}
