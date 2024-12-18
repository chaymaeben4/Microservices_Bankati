package com.example.service_transactions.batch;

import com.example.service_transactions.repository.TransactionRepository;
import com.example.service_transactions.external.ExternalBankingClient;
import org.example.entites.Transaction;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReconciliationBatch {

    private final TransactionRepository transactionRepository;
    private final ExternalBankingClient bankingClient;

    public ReconciliationBatch(TransactionRepository transactionRepository, ExternalBankingClient bankingClient) {
        this.transactionRepository = transactionRepository;
        this.bankingClient = bankingClient;
    }

    @Scheduled(cron = "0 0 * * *") // Daily at midnight
    public void reconcileTransactions() {
        List<Transaction> pendingTransactions = transactionRepository.findByStatus("PENDING");

        for (Transaction transaction : pendingTransactions) {
            boolean confirmed = bankingClient.confirmTransaction(transaction.getId());
            if (confirmed) {
                transaction.setStatus("COMPLETED");
            } else {
                transaction.setStatus("FAILED");
            }
            transactionRepository.save(transaction);
        }
    }
}
