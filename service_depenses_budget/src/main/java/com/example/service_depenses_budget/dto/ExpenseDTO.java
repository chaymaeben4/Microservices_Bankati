package com.example.service_depenses_budget.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDTO {
    private Long id;
    private Long userId;
    private Double amount;
    private String description;
    private LocalDate date;
    private Long categoryId; // Référence à la catégorie
}
