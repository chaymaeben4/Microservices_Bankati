package org.example.entites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "agents")
public class Agent extends Utilisateur {
    @Column(nullable = false)
    private String agence;
}
