package com.example.service_transactions.controller;

import com.example.service_transactions.request.BillPaymentRequest;
import com.example.service_transactions.service.TransactionService;
import org.example.entites.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/creer")
    public ResponseEntity<Transaction> creerTransaction(@RequestBody BillPaymentRequest request) {
        Transaction transaction = transactionService.creerTransaction(request);
        return ResponseEntity.ok(transaction);
    }

    @PutMapping("/{id}/valider")
    public ResponseEntity<Transaction> validerTransaction(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.validerTransaction(id));
    }

    @GetMapping("/utilisateur/source/{utilisateurId}")
    public ResponseEntity<List<Transaction>> trouverParUtilisateurSource(@PathVariable Long utilisateurId) {
        return ResponseEntity.ok(transactionService.trouverParUtilisateurSource(utilisateurId));
    }

    @GetMapping("/utilisateur/destinataire/{utilisateurId}")
    public ResponseEntity<List<Transaction>> trouverParUtilisateurDestinataire(@PathVariable Long utilisateurId) {
        return ResponseEntity.ok(transactionService.trouverParUtilisateurDestinataire(utilisateurId));
    }
}
