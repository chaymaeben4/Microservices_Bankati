package org.example.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientRequestDTO {
    private String nom;
    private String prenom;
    private String email;

    private String typePieceIdentite; // C.I.N, Passeport, etc.
    private String numeroPieceIdentite;

    private LocalDate dateNaissance;

    private String adresse;

    private String numeroTelephone;

    private String numeroImmatriculationCommerce;

    private String numeroPatente;

    private String pieceIdentite;
}
