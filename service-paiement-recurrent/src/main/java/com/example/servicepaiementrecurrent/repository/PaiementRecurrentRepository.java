package com.example.servicepaiementrecurrent.repository;

import org.example.entites.PaiementRecurrent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaiementRecurrentRepository extends JpaRepository<PaiementRecurrent , Long> {
    List<PaiementRecurrent> findByStatus(String active);
}
