package org.example.dto;

import lombok.Data;
import org.example.enums.Devise;

import java.util.List;

@Data
public class PortefeuillesDTO {
    private Long id;
    private Long utilisateurId;
    private Double balance;
    private Devise devise; // USD, MAD, EUR
//    private List<TransactionDTO> transactions_sortantes;
//    private List<TransactionDTO> transactions_entrantes;
}
