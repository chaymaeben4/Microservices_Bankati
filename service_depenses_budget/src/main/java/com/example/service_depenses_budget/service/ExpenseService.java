package com.example.service_depenses_budget.service;

import com.example.service_depenses_budget.dto.ExpenseDTO;
import com.example.service_depenses_budget.entity.Budget;
import com.example.service_depenses_budget.entity.Expense;
import com.example.service_depenses_budget.repository.BudgetRepository;
import com.example.service_depenses_budget.repository.ExpenseRepository;
import com.example.service_depenses_budget.mapper.ExpenseMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    private final ExpenseMapper expenseMapper;
    private final ExpenseCategorizationService expenseCategorizationService;
    private final BudgetRepository budgetRepository;
    // Crée une nouvelle dépense
    public ExpenseDTO createExpense(ExpenseDTO expenseDTO) {
        Expense expense = expenseMapper.toEntity(expenseDTO);
        expense = expenseRepository.save(expense);
        return expenseMapper.toDTO(expense);
    }

    // Ajouter une dépense avec catégorisation automatique
    public ExpenseDTO addExpenseWithAutoCategory(ExpenseDTO expenseDTO) {
        // Convertir le DTO en entité
        Expense expense = expenseMapper.toEntity(expenseDTO);

        // Catégoriser la dépense
        Expense categorizedExpense = expenseCategorizationService.categorizeExpense(expense);

        // Ajouter le montant de la dépense au budget courant
        Budget budget = categorizedExpense.getBudget();
        budget.setCurrentSpent(budget.getCurrentSpent() + categorizedExpense.getAmount());

        Expense savedExpense = expenseRepository.save(categorizedExpense);

        budget.getExpenses().add(savedExpense); // Ajouter à la liste des dépenses

        budgetRepository.save(budget);
        log.info("category to save : {}" , savedExpense.getId() );
        // Retourner le DTO de la dépense catégorisée
        return expenseMapper.toDTO(savedExpense);
    }



    // Récupère une dépense par son ID
    public ExpenseDTO getExpenseById(Long id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
        return expenseMapper.toDTO(expense);
    }

    // Mettre à jour une expense existante avec mise à jour du budget courant
    public ExpenseDTO updateExpense(Long id, ExpenseDTO expenseDTO) {
        // Récupérer l'ancienne dépense
        Expense existingExpense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        // Déterminer le budget associé avant modification
        Budget oldBudget = existingExpense.getBudget();

        // Retirer l'ancienne dépense de la liste des dépenses du budget précédent
        oldBudget.getExpenses().remove(existingExpense);
        // Retirer l'ancien montant du budget courant
        oldBudget.setCurrentSpent(oldBudget.getCurrentSpent() - existingExpense.getAmount());
        budgetRepository.save(oldBudget);

        // Mettre à jour les informations de la dépense
        existingExpense.setAmount(expenseDTO.getAmount());
        existingExpense.setDescription(expenseDTO.getDescription());
        existingExpense.setDate(expenseDTO.getDate());

        // Catégoriser à nouveau si nécessaire
        Expense updatedExpense = expenseCategorizationService.categorizeExpense(existingExpense);

        // Ajouter le nouveau montant au budget courant
        Budget newBudget = updatedExpense.getBudget();
        newBudget.getExpenses().add(updatedExpense); // Ajouter à la liste des dépenses
        newBudget.setCurrentSpent(newBudget.getCurrentSpent() + updatedExpense.getAmount());
        budgetRepository.save(newBudget);

        // Sauvegarder les modifications de la dépense
        updatedExpense = expenseRepository.save(updatedExpense);

        // Retourner le DTO mis à jour
        return expenseMapper.toDTO(updatedExpense);
    }

    // Supprime une dépense par son ID
    public void deleteExpense(Long id) {
        // Récupérer la dépense
        Expense existingExpense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        // Récupérer le budget associé
        Budget budget = existingExpense.getBudget();

        // Retirer la dépense du budget
        budget.getExpenses().remove(existingExpense);
        budget.setCurrentSpent(budget.getCurrentSpent() - existingExpense.getAmount());
        budgetRepository.save(budget);

        // Supprimer la dépense
        expenseRepository.deleteById(id);    }

    // Récupère toutes les dépenses
    public List<ExpenseDTO> getAllExpenses() {
        List<Expense> expenses = expenseRepository.findAll();
        return expenseMapper.toDTO(expenses);
    }
}
