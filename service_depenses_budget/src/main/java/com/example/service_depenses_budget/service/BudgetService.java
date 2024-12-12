package com.example.service_depenses_budget.service;

import com.example.service_depenses_budget.dto.BudgetDTO;
import com.example.service_depenses_budget.entity.Budget;
import com.example.service_depenses_budget.entity.Category;
import com.example.service_depenses_budget.repository.BudgetRepository;
import com.example.service_depenses_budget.mapper.BudgetMapper;
import com.example.service_depenses_budget.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class BudgetService {

    private final BudgetRepository budgetRepository;

    private final BudgetMapper budgetMapper;
    private final CategoryRepository categoryRepository;

    // Crée un nouveau budget
//    public BudgetDTO createBudget(BudgetDTO budgetDTO) {
//        Budget budget = budgetMapper.toEntity(budgetDTO);
//        budget = budgetRepository.save(budget);
//        return budgetMapper.toDTO(budget);
//    }

    // Créer un budget avec une catégorie choisie
    public BudgetDTO createBudgetWithCategory(BudgetDTO budgetDTO) {
        // Récupérer la catégorie en fonction de l'ID
        Category category = categoryRepository.findById(budgetDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // Créer le budget
        Budget budget = budgetMapper.toEntity(budgetDTO);
        budget.setCategory(category); // Associer la catégorie au budget

        // Enregistrer le budget
        budget = budgetRepository.save(budget);
        log.info("le budget a ajouter {}",budget.getId());
        // Retourner le DTO du budget créé
        return budgetMapper.toDTO(budget);
    }


    // Récupère un budget par son ID
    public BudgetDTO getBudgetById(Long id) {
        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Budget not found"));
        return budgetMapper.toDTO(budget);
    }

    // Met à jour un budget existant
    public BudgetDTO updateBudget(Long id, BudgetDTO budgetDTO) {
        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Budget not found"));
        budget.setBudget_limit(budgetDTO.getBudget_limit());
        budget.setStartDate(budgetDTO.getStartDate());
        budget.setEndDate(budgetDTO.getEndDate());
        budget = budgetRepository.save(budget);
        return budgetMapper.toDTO(budget);
    }

    // Supprime un budget par son ID
    public void deleteBudget(Long id) {
        budgetRepository.deleteById(id);
    }

    // Récupère tous les budgets
    public List<BudgetDTO> getAllBudgets() {
        List<Budget> budgets = budgetRepository.findAll();
        return budgetMapper.toDTO(budgets);
    }
}
