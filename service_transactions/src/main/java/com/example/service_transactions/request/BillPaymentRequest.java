package com.example.service_transactions.request;

public class BillPaymentRequest {
    private Long sourceId; // ID du portefeuille source
    private Long destinateurId; // ID du portefeuille destinataire
    private Double montant; // Montant de la transaction

    // Getters et setters
    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public Long getDestinateurId() {
        return destinateurId;
    }

    public void setDestinateurId(Long destinateurId) {
        this.destinateurId = destinateurId;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }
}
