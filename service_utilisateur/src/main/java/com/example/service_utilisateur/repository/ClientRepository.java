package com.example.service_utilisateur.repository;

import org.example.entites.Client;
import org.example.entites.Utilisateur;
import org.example.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByNumeroPieceIdentite(String numeroPieceIdentite);
    Optional<Client> findByNumeroImmatriculationCommerce(String numImmatriculation);
    Optional<Client> findByNumeroTelephone(String numeroTelephone);
    Optional<Client> findByNumeroPatente(String numeroPatente);


}
