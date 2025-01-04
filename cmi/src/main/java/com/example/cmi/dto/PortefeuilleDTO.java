package com.example.cmi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.enums.Devise;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PortefeuilleDTO {
    private Long id;
    private Double balance;
    private Devise devise;

}
