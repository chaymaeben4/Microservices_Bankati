package com.example.service_depenses_budget.service;

import com.example.service_depenses_budget.client.PortefeuilleClient;
import com.example.service_depenses_budget.dto.CreateExpenseRequest;
import com.example.service_depenses_budget.dto.ExpenseDTO;
import com.example.service_depenses_budget.dto.PortefeuilleDTO;
import com.example.service_depenses_budget.entity.Alert;
import com.example.service_depenses_budget.entity.Expense;
import com.example.service_depenses_budget.repository.ExpenseRepository;
import com.example.service_depenses_budget.mapper.ExpenseMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class ExpenseService {

    @Autowired
    private  ExpenseRepository expenseRepository;

    @Autowired
    private  ExpenseMapper expenseMapper;

    @Autowired
    private PortefeuilleClient portefeuilleClient;

    // Crée une nouvelle dépense
    public ExpenseDTO createExpense(ExpenseDTO expenseDTO) {
        Expense expense = expenseMapper.toEntity(expenseDTO);
        expense = expenseRepository.save(expense);
        return expenseMapper.toDTO(expense);
    }

    // Récupère une dépense par son ID
    public ExpenseDTO getExpenseById(Long id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
        return expenseMapper.toDTO(expense);
    }

    // Mettre à jour une expense existante avec mise à jour du budget courant
//    public ExpenseDTO updateExpense(Long id, ExpenseDTO expenseDTO) {
//        // Récupérer l'ancienne dépense
//        Expense existingExpense = expenseRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Expense not found"));
//
//        // Déterminer le budget associé avant modification
//        Budget oldBudget = existingExpense.getBudget();
//
//        // Retirer l'ancienne dépense de la liste des dépenses du budget précédent
//        oldBudget.getExpenses().remove(existingExpense);
//        // Retirer l'ancien montant du budget courant
//        oldBudget.setCurrentSpent(oldBudget.getCurrentSpent() - existingExpense.getAmount());
//        budgetRepository.save(oldBudget);
//
//        // Mettre à jour les informations de la dépense
//        existingExpense.setAmount(expenseDTO.getAmount());
//        existingExpense.setDescription(expenseDTO.getDescription());
//        existingExpense.setDate(expenseDTO.getDate());
//
//        // Catégoriser à nouveau si nécessaire
//        Expense updatedExpense = expenseCategorizationService.categorizeExpense(existingExpense);
//
//        // Ajouter le nouveau montant au budget courant
//        Budget newBudget = updatedExpense.getBudget();
//        newBudget.getExpenses().add(updatedExpense); // Ajouter à la liste des dépenses
//        newBudget.setCurrentSpent(newBudget.getCurrentSpent() + updatedExpense.getAmount());
//        budgetRepository.save(newBudget);
//
//        // Sauvegarder les modifications de la dépense
//        updatedExpense = expenseRepository.save(updatedExpense);
//
//        // Retourner le DTO mis à jour
//        return expenseMapper.toDTO(updatedExpense);
//    }

    // Supprime une dépense par son ID
//    public void deleteExpense(Long id) {
//        // Récupérer la dépense
//        Expense existingExpense = expenseRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Expense not found"));
//
//        // Récupérer le budget associé
//        Budget budget = existingExpense.getBudget();
//
//        // Retirer la dépense du budget
//        budget.getExpenses().remove(existingExpense);
//        budget.setCurrentSpent(budget.getCurrentSpent() - existingExpense.getAmount());
//        budgetRepository.save(budget);
//
//        // Supprimer la dépense
//        expenseRepository.deleteById(id);    }

    // Récupère toutes les dépenses
    public List<ExpenseDTO> getAllExpenses() {
        List<Expense> expenses = expenseRepository.findAll();
        return expenseMapper.toDTO(expenses);
    }


    @Transactional
    public Alert createExpenseFromPortefeuille(CreateExpenseRequest request) {
        // Récupérer les informations du portefeuille
        PortefeuilleDTO portefeuille = portefeuilleClient.getPortefeuilleById(request.getPortefeuilleId());

        if (portefeuille == null) {
            return new Alert("Portefeuille introuvable", LocalDate.now(), false);
        }

        // Vérifier qu'il existe déjà une dépense avec la même catégorie pour ce portefeuille
        boolean existingExpense = expenseRepository.existsByCategoryAndPortefeuilleId(request.getCategory(), request.getPortefeuilleId());
        if (existingExpense) {
            return new Alert("Une dépense avec cette catégorie existe déjà pour ce portefeuille", LocalDate.now(), false);
        }

        // Vérifier que le montant de la dépense ne dépasse pas le solde du portefeuille
        if (request.getMontant() > portefeuille.getBalance()) {
            return new Alert("Le montant de la dépense dépasse le solde du portefeuille", LocalDate.now(), false);
        }

        // Créer la dépense
        Expense expense = Expense.builder()
                .amount(request.getMontant())
                .description(request.getDescription())
                .category(request.getCategory())
                .devise(portefeuille.getDevise())
                .portefeuilleId(request.getPortefeuilleId())
                .build();
        expense = expenseRepository.save(expense);

        // Mettre à jour le solde du portefeuille
        portefeuilleClient.updatePortefeuille(request.getPortefeuilleId(), request.getMontant());

        // Retourner une alerte de succès
        return new Alert("Dépense créée avec succès", LocalDate.now(), true);
    }

    public List<Expense> getAllExpensesByPortefeuilleId(Long id){
        return expenseRepository.findAllByPortefeuilleId(id);
    }

    @Transactional
    public boolean alimenterDepense(Long depenseId, Double montantSupplementaire) {
        // Récupérer la dépense
        Expense expense = expenseRepository.findById(depenseId)
                .orElseThrow(() -> new RuntimeException("Dépense introuvable"));

        // Mettre à jour le montant
        expense.setAmount(expense.getAmount() + montantSupplementaire);
        expenseRepository.save(expense);

        return true;
    }


}
