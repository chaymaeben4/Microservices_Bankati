package com.example.service_depenses_budget.repository;


import com.example.service_depenses_budget.entity.Category;
import com.example.service_depenses_budget.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUserId(Long userId);

    Collection<Object> findByUserIdAndCategory(Long userId, Category category);
}
