package com.example.service_depenses_budget.dto;


import com.example.service_depenses_budget.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateExpenseRequest {
    private Long portefeuilleId;
    private String description;
    private Category category;
    private Double montant;
}
