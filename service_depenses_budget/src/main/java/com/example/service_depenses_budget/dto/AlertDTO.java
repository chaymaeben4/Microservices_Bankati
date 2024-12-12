package com.example.service_depenses_budget.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlertDTO {
    private Long id;
    private Long userId;
    private Long categoryId; // Référence à la catégorie
    private String alertMessage;
    private LocalDate alertDate;
}
