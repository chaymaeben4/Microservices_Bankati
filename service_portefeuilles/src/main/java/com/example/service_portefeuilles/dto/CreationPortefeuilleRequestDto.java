package com.example.service_portefeuilles.dto;

import lombok.Data;
import org.example.enums.Devise;

@Data
public class CreationPortefeuilleRequestDto {
    private Long utilisateurId;
    private Devise devise; // Par exemple : "USD", "EUR", "MAD"
    private Double balanceInitiale; // Solde initial
}
