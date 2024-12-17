package org.example.entites;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "clients")
public class Client extends Utilisateur {

    @Column(nullable = false)
    private String typePieceIdentite; // C.I.N, Passeport, etc.

    @Column(nullable = false)
    private String numeroPieceIdentite;

    private LocalDate dateNaissance;

    @Column(nullable = false)
    private String adresse;

    @Column(nullable = false)
    private String numeroTelephone;

    @Column(unique = true, nullable = false)
    private String numeroImmatriculationCommerce;

    @Column(nullable = false)
    private String numeroPatente;

    @Column(nullable = false)
    private String pieceIdentite;


    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<Portefeuilles> portefeuilles;

    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<CarteVirtuelle> virtualCards;

    // Autres attributs si nécessaire
}
