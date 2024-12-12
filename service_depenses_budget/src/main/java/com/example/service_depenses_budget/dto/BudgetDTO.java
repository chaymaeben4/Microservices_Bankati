package com.example.service_depenses_budget.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BudgetDTO {
    private Long id;
    private Long userId;
    private Long categoryId; // Référence à la catégorie
    private Double budget_limit;
    private Double currentSpent = 0.0; // Montant total dépensé pour ce budget
    private LocalDate startDate;
    private LocalDate endDate;
    private Double alertThreshold;
}
