package org.example.entites;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "cartes_virtuelles")
public class CarteVirtuelle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(nullable = false, unique = true)
    private String numero_carte;

    @Column(nullable = false)
    private String cvv;

    @Column(nullable = false)
    private LocalDate date_expiration;

    @Column(nullable = false)
    private Double limite;
}
