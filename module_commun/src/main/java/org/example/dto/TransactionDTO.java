package org.example.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionDTO {
    private Long id;
    private PortefeuillesDTO destinateur;
    private PortefeuillesDTO destinataire;
    private Double montant;
    private String status; // PENDING, COMPLETED, FAILED
    private LocalDateTime date;
}