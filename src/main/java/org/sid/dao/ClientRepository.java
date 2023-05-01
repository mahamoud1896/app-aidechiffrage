package org.sid.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.sid.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

	
}
