package com.example.service_transactions.repository;

import org.example.entites.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByStatus(String status);
    // Trouver toutes les transactions où l'utilisateur est le destinateur via son portefeuille
    List<Transaction> findByDestinateurUtilisateurId(Long utilisateurId);

    // Trouver toutes les transactions où l'utilisateur est le destinataire via son portefeuille
    List<Transaction> findByDestinataireUtilisateurId(Long utilisateurId);
}
