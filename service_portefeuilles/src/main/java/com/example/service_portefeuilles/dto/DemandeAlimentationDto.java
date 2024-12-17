package com.example.service_portefeuilles.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemandeAlimentationDto {
    private Long compteId;
    private Long portefeuilleId;
    private Double montant;
}
