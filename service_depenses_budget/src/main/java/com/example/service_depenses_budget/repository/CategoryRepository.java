package com.example.service_depenses_budget.repository;


import com.example.service_depenses_budget.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name); // Recherche de catégorie par nom
}
