package com.example.service_paiement_multidevises.repository;

import org.example.entites.Portefeuilles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortefeuillesRepository extends JpaRepository<Portefeuilles , Long> {
}
