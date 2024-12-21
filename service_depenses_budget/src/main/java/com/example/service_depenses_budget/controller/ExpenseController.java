package com.example.service_depenses_budget.controller;

import com.example.service_depenses_budget.client.PortefeuilleClient;
import com.example.service_depenses_budget.dto.CreateExpenseRequest;
import com.example.service_depenses_budget.dto.ExpenseDTO;
import com.example.service_depenses_budget.dto.PortefeuilleDTO;
import com.example.service_depenses_budget.entity.Alert;
import com.example.service_depenses_budget.entity.Expense;
import com.example.service_depenses_budget.service.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/depenses_budget/expenses")
public class ExpenseController {



    @Autowired
    private ExpenseService expenseService;
    
    @Autowired
    private PortefeuilleClient portefeuilleClient;

    // Crée une nouvelle dépense
//    @PostMapping
//    public ResponseEntity<ExpenseDTO> createExpense(@RequestBody ExpenseDTO expenseDTO) {
//        ExpenseDTO createdExpense = expenseService.createExpense(expenseDTO);
//        return new ResponseEntity<>(createdExpense, HttpStatus.CREATED);
//    }


    //la creation d'une expense depuis un portefeuille
//    @PostMapping
//    public ResponseEntity<ExpenseDTO> createExpense(@RequestBody ExpenseDTO expenseDTO) {
//        ExpenseDTO createdExpense = expenseService.createExpense(expenseDTO);
//        return new ResponseEntity<>(createdExpense, HttpStatus.CREATED);
//    }




    // Récupère une dépense par son ID
    @GetMapping("/{id}")
    public ResponseEntity<ExpenseDTO> getExpenseById(@PathVariable Long id) {
        ExpenseDTO expense = expenseService.getExpenseById(id);
        return new ResponseEntity<>(expense, HttpStatus.OK);
    }

    // Récupère toutes les dépenses
    @GetMapping
    public ResponseEntity<List<ExpenseDTO>> getAllExpenses() {
        List<ExpenseDTO> expenses = expenseService.getAllExpenses();
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }
//    @GetMapping("/portefeuille/{id}")
//    public ResponseEntity<PortefeuilleDTO> getPortefeuille(@PathVariable Long id){
//        return new ResponseEntity<>(portefeuilleClient.getPortefeuilleById(id), HttpStatus.OK);
//    }

    @PostMapping("/create-from-portefeuille")
    public ResponseEntity<Alert> createExpenseFromPortefeuille(@RequestBody CreateExpenseRequest request) {
        try {
            // Appeler le service pour créer la dépense
            Alert alert = expenseService.createExpenseFromPortefeuille(request);

            // Retourner la réponse avec l'alerte
            return ResponseEntity.ok(alert);
        } catch (Exception e) {
            // En cas d'erreur, retourner une réponse avec un statut d'échec
            Alert errorAlert = new Alert("Erreur lors de la création de la dépense : " + e.getMessage(), LocalDate.now(), false);
            return ResponseEntity.badRequest().body(errorAlert);
        }
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<List<Expense>> getAllExpensesByPortefeuilleId(@PathVariable Long id) {
        List<Expense> expenses = expenseService.getAllExpensesByPortefeuilleId(id);
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }


    // Met à jour une dépense existante
//    @PutMapping("/{id}")
//    public ResponseEntity<ExpenseDTO> updateExpense(@PathVariable Long id, @RequestBody ExpenseDTO expenseDTO) {
//        ExpenseDTO updatedExpense = expenseService.updateExpense(id, expenseDTO);
//        return new ResponseEntity<>(updatedExpense, HttpStatus.OK);
//    }
//
//    // Supprime une dépense
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
//        expenseService.deleteExpense(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
    @PutMapping("/{depenseId}/alimenter/{montant}")
    public boolean alimenterDepense(
            @PathVariable Long depenseId,
            @PathVariable Double montant) {
        return expenseService.alimenterDepense(depenseId, montant);
    }
}
