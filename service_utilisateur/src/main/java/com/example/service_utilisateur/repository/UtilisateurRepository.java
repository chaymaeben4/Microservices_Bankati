package com.example.service_utilisateur.repository;

import org.example.entites.Utilisateur;
import org.example.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    // Trouver tous les utilisateurs par rôle (Agent, Client, Admin)
    List<Utilisateur> findByRole(Role role);

    // Trouver un utilisateur par email
    Optional<Utilisateur> findByEmail(String email);

    // Trouver les agents
    default List<Utilisateur> findAgents() {
        return findByRole(Role.AGENT);
    }

    // Trouver les clients
    default List<Utilisateur> findClients() {
        return findByRole(Role.CLIENT);
    }

    // Trouver les admins
    default List<Utilisateur> findAdmins() {
        return findByRole(Role.ADMIN);
    }

}
