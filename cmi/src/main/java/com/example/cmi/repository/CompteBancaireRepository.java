package com.example.cmi.repository;

import com.example.cmi.model.CompteBancaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompteBancaireRepository extends JpaRepository<CompteBancaire,Long> {
    CompteBancaire findByUtilisateurId(Long utilisateurId);
}
