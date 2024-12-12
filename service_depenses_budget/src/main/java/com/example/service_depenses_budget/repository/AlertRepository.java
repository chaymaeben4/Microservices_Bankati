package com.example.service_depenses_budget.repository;

import com.example.service_depenses_budget.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRepository extends JpaRepository<Alert,Long> {
}
