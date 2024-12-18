package com.example.service_transactions.notifications;

import org.example.entites.Transaction;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void sendTransactionCreatedNotification(Transaction transaction) {
        System.out.println("Notification sent for transaction creation: " + transaction.getId());
    }

    public void sendTransactionValidatedNotification(Transaction transaction) {
        System.out.println("Notification sent for transaction validation: " + transaction.getId());
    }
}
