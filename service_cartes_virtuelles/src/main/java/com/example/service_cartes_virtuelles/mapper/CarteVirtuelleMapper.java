package com.example.service_cartes_virtuelles.mapper;

import org.example.dto.CarteVirtuelleDTO;
import org.example.dto.UtilisateurDTO;
import org.example.entites.CarteVirtuelle;
import org.example.entites.Utilisateur;
import org.springframework.stereotype.Component;

@Component
public class CarteVirtuelleMapper {

    // Convertir une entité CarteVirtuelle en DTO
    public CarteVirtuelleDTO toDTO(CarteVirtuelle carteVirtuelle) {
        CarteVirtuelleDTO dto = new CarteVirtuelleDTO();
        dto.setId(carteVirtuelle.getId());
        dto.setNumero_carte(carteVirtuelle.getNumero_carte());
        dto.setCvv(carteVirtuelle.getCvv());
        dto.setDate_expiration(carteVirtuelle.getDate_expiration());
        dto.setLimite(carteVirtuelle.getLimite());
        dto.setUtilisateur(toUtilisateurDTO(carteVirtuelle.getUtilisateur()));
        return dto;
    }

    // Convertir un DTO CarteVirtuelle en entité
    public CarteVirtuelle toEntity(CarteVirtuelleDTO dto) {
        CarteVirtuelle carteVirtuelle = new CarteVirtuelle();
        carteVirtuelle.setId(dto.getId());
        carteVirtuelle.setNumero_carte(dto.getNumero_carte());
        carteVirtuelle.setCvv(dto.getCvv());
        carteVirtuelle.setDate_expiration(dto.getDate_expiration());
        carteVirtuelle.setLimite(dto.getLimite());
        carteVirtuelle.setUtilisateur(toUtilisateurEntity(dto.getUtilisateur()));
        return carteVirtuelle;
    }

    // Convertir une entité Utilisateur en DTO
    private UtilisateurDTO toUtilisateurDTO(Utilisateur utilisateur) {
        if (utilisateur == null) return null;
        UtilisateurDTO dto = new UtilisateurDTO();
        dto.setId(utilisateur.getId());
        dto.setNom(utilisateur.getNom());
        dto.setEmail(utilisateur.getEmail());
        return dto;
    }

    // Convertir un DTO Utilisateur en entité
    private Utilisateur toUtilisateurEntity(UtilisateurDTO dto) {
        if (dto == null) return null;
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(dto.getId());
        utilisateur.setNom(dto.getNom());
        utilisateur.setEmail(dto.getEmail());
        return utilisateur;
    }
}
