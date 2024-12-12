package com.example.service_depenses_budget.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId; // Identifiant de l'utilisateur

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category; // Catégorie concernée

    private String alertMessage; // Message de l'alerte

    private LocalDate alertDate; // Date de l'alerte
}

