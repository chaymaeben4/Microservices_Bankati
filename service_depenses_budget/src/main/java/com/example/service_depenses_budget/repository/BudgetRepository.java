package com.example.service_depenses_budget.repository;

import com.example.service_depenses_budget.entity.Budget;
import com.example.service_depenses_budget.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface BudgetRepository extends JpaRepository<Budget,Long> {
    Budget findByUserIdAndCategory(Long userId, Category category);

    Collection<Budget> findByCategoryAndUserId(Category category, Long userId);
}
