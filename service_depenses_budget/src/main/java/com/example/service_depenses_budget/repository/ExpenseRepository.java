package com.example.service_depenses_budget.repository;


import com.example.service_depenses_budget.entity.Category;
import com.example.service_depenses_budget.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    boolean existsByCategoryAndPortefeuilleId(Category category, Long portefeuilleId);
    List<Expense> findAllByPortefeuilleId(Long portefeuilleId);

}
