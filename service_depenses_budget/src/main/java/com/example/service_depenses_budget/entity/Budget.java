package com.example.service_depenses_budget.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId; // Identifiant de l'utilisateur

    @OneToMany(mappedBy = "budget") // Relation inversée
    private List<Expense> expenses; // Liste des dépenses associées à ce budget

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category; // Catégorie associée au budget

    private Double budget_limit; // Limite du budget

    private Double currentSpent = 0.0; // Montant total dépensé pour ce budget

    private LocalDate startDate; // Date de début du budget

    private LocalDate endDate; // Date de fin du budget

    private Double alertThreshold; // Seuil d'alerte (exemple : 80% du budget)
}

