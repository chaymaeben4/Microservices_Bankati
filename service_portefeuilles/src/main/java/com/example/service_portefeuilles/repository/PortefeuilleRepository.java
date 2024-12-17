package com.example.service_portefeuilles.repository;

import org.example.entites.Portefeuilles;
import org.example.enums.Devise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PortefeuilleRepository extends JpaRepository<Portefeuilles,Long> {
    List<Portefeuilles> findByUtilisateurId(Long utilisateurId);

    Optional<Portefeuilles> findByUtilisateurIdAndDevise(Long utilisateurId, Devise devise);

}
