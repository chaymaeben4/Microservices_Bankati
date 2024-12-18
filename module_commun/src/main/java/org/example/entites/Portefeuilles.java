package org.example.entites;

import jakarta.persistence.*;
import lombok.Data;
import org.example.enums.Devise;

import java.util.List;

@Data
@Entity
@Table(name = "portefeuilles")
public class Portefeuilles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(nullable = false)
    private Double balance;

    @Column(nullable = false)
    private Devise devise;

    @OneToMany(mappedBy = "destinateur", cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<Transaction> Transactions_sortantes;

    @OneToMany(mappedBy = "destinataire", cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<Transaction> Transactions_entrantes;
}

