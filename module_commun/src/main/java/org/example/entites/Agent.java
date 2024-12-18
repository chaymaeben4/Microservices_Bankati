package org.example.entites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "agents")
@EqualsAndHashCode(callSuper = true)

public class Agent extends Utilisateur {
    @Column(nullable = false)
    private String agence;
}
