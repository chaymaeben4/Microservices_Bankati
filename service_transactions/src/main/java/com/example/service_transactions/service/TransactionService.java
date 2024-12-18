package com.example.service_transactions.service;

import com.example.service_transactions.repository.TransactionRepository;
import com.example.service_transactions.request.BillPaymentRequest;
import org.example.entites.Portefeuilles;
import org.example.entites.Transaction;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    // Créer une transaction
    public Transaction creerTransaction(BillPaymentRequest request) {
        // Créez des objets factices Portefeuilles (à adapter si vous avez un accès aux données réelles)
        Portefeuilles sourcePortefeuille = new Portefeuilles();
        sourcePortefeuille.setId(request.getSourceId());
        sourcePortefeuille.setBalance(1000.0); // Exemple

        Portefeuilles destinateurPortefeuille = new Portefeuilles();
        destinateurPortefeuille.setId(request.getDestinateurId());
        destinateurPortefeuille.setBalance(2000.0); // Exemple

        // Créez une transaction avec les données reçues
        Transaction transaction = new Transaction();
        transaction.setMontant(request.getMontant());
        transaction.setDestinateur(destinateurPortefeuille);
        transaction.setDestinataire(sourcePortefeuille);
        transaction.setStatus("PENDING");
        transaction.setDate(LocalDateTime.now());

        // Enregistrez la transaction
        return transactionRepository.save(transaction);
    }

    // Valider une transaction
    public Transaction validerTransaction(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction introuvable !"));
        transaction.setStatus("COMPLETED");
        return transactionRepository.save(transaction);
    }

    // Trouver les transactions envoyées par un utilisateur
    public List<Transaction> trouverParUtilisateurSource(Long utilisateurId) {
        return transactionRepository.findByDestinateurUtilisateurId(utilisateurId);
    }

    // Trouver les transactions reçues par un utilisateur
    public List<Transaction> trouverParUtilisateurDestinataire(Long utilisateurId) {
        return transactionRepository.findByDestinataireUtilisateurId(utilisateurId);
    }
}
