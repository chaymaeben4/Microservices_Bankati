package com.example.service_portefeuilles.repository;

import com.example.service_portefeuilles.model.Alimentation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlimentationRepository extends JpaRepository<Alimentation,Long> {
}
