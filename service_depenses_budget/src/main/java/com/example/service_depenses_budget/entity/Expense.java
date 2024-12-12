package com.example.service_depenses_budget.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId; // Identifiant de l'utilisateur (peut être une référence à un service utilisateur)

    private Double amount; // Montant de la dépense

    private String description; // Description de la dépense

    private LocalDate date; // Date de la dépense

    @ManyToOne
    @JoinColumn(name = "budget_id") // Relier la dépense à un budget
    private Budget budget; // Budget auquel la dépense appartient

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category; // Catégorie de la dépense

}
