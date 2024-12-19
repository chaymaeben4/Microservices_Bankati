package com.example.service_cartes_virtuelles.repo;

import org.example.entites.CarteVirtuelle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarteVirtuelleRepository extends JpaRepository<CarteVirtuelle, Long> {
    List<CarteVirtuelle> findByUtilisateurId(Long utilisateurId);
}
