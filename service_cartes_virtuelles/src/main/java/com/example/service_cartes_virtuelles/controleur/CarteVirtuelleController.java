package com.example.service_cartes_virtuelles.controleur;

import com.example.service_cartes_virtuelles.service.CarteVirtuelleService;
import org.example.dto.CarteVirtuelleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cartes-virtuelles")
public class CarteVirtuelleController {

    @Autowired
    private CarteVirtuelleService carteVirtuelleService;

    /**
     * Récupère toutes les cartes pour un utilisateur.
     *
     * @param utilisateurId ID de l'utilisateur
     * @return Liste de cartes virtuelles
     */
    @GetMapping("/utilisateur/{utilisateurId}")
    public ResponseEntity<List<CarteVirtuelleDTO>> getCartesParUtilisateur(@PathVariable Long utilisateurId) {
        List<CarteVirtuelleDTO> cartes = carteVirtuelleService.getCartesParUtilisateur(utilisateurId);
        return ResponseEntity.ok(cartes);
    }

    /**
     * Crée une nouvelle carte virtuelle.
     *
     * @param carteVirtuelleDTO DTO de la carte à créer
     * @return Carte virtuelle créée
     */
    @PostMapping
    public ResponseEntity<CarteVirtuelleDTO> creerCarte(@RequestBody CarteVirtuelleDTO carteVirtuelleDTO) {
        CarteVirtuelleDTO nouvelleCarte = carteVirtuelleService.creerCarte(carteVirtuelleDTO);
        return ResponseEntity.ok(nouvelleCarte);
    }

    /**
     * Supprime une carte virtuelle par ID.
     *
     * @param carteId ID de la carte à supprimer
     * @return Réponse de confirmation
     */
    @DeleteMapping("/{carteId}")
    public ResponseEntity<Void> supprimerCarte(@PathVariable Long carteId) {
        carteVirtuelleService.supprimerCarte(carteId);
        return ResponseEntity.noContent().build();
    }
}
