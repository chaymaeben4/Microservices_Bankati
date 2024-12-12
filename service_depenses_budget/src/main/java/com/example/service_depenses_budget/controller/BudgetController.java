package com.example.service_depenses_budget.controller;

import com.example.service_depenses_budget.dto.BudgetDTO;
import com.example.service_depenses_budget.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    // Crée un nouveau budget
//    @PostMapping
//    public ResponseEntity<BudgetDTO> createBudget(@RequestBody BudgetDTO budgetDTO) {
//        BudgetDTO createdBudget = budgetService.createBudget(budgetDTO);
//        return new ResponseEntity<>(createdBudget, HttpStatus.CREATED);
//    }

    @PostMapping
    public ResponseEntity<BudgetDTO> createBudge(
            @RequestBody BudgetDTO budgetDTO) {

        // Créer le budget avec la catégorie choisie
        BudgetDTO createdBudget = budgetService.createBudgetWithCategory(budgetDTO);
        return new ResponseEntity<>(createdBudget, HttpStatus.CREATED);
    }

    // Récupère un budget par son ID
    @GetMapping("/{id}")
    public ResponseEntity<BudgetDTO> getBudgetById(@PathVariable Long id) {
        BudgetDTO budget = budgetService.getBudgetById(id);
        return new ResponseEntity<>(budget, HttpStatus.OK);
    }

    // Récupère tous les budgets
    @GetMapping
    public ResponseEntity<List<BudgetDTO>> getAllBudgets() {
        List<BudgetDTO> budgets = budgetService.getAllBudgets();
        return new ResponseEntity<>(budgets, HttpStatus.OK);
    }

    // Met à jour un budget existant
    @PutMapping("/{id}")
    public ResponseEntity<BudgetDTO> updateBudget(@PathVariable Long id, @RequestBody BudgetDTO budgetDTO) {
        BudgetDTO updatedBudget = budgetService.updateBudget(id, budgetDTO);
        return new ResponseEntity<>(updatedBudget, HttpStatus.OK);
    }

    // Supprime un budget
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudget(@PathVariable Long id) {
        budgetService.deleteBudget(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
