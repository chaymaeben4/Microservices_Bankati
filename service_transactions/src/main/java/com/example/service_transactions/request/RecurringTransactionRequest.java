package com.example.service_transactions.request;

public class RecurringTransactionRequest {

    private Long source;
    private Long destinataire;
    private double amount;

    // Getters and Setters
    public Long getSource() {
        return source;
    }

    public void setSource(Long source) {
        this.source = source;
    }

    public Long getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(Long destinataire) {
        this.destinataire = destinataire;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
