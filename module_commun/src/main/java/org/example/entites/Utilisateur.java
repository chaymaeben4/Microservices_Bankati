package org.example.entites;


import jakarta.persistence.*;
import lombok.Data;
import org.example.enums.Role;

import java.util.List;

@Data
@Entity
@Table(name = "utilisateurs")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private Role role; // CLIENT, AGENT, ADMIN

    @Column(nullable = false)
    private String mdp;

    @Column(nullable = false)
    private boolean isVerified = false; // Validation avec OTP


    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<Notification> notifications;
}



