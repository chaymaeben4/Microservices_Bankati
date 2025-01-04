package com.example.service_cartes_virtuelles.controleur;

import com.example.service_cartes_virtuelles.service.CarteVirtuelleService;
import org.example.dto.CarteVirtuelleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Map<String, String>> creerCarte(@RequestBody CarteVirtuelleDTO carteVirtuelleDTO) {
        try {
            // Appeler la méthode `creerCarte` et récupérer le message
            String message = carteVirtuelleService.creerCarte(carteVirtuelleDTO);

            // Retourner la réponse au format JSON
            Map<String, String> response = new HashMap<>();
            response.put("message", message);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            // Gérer les exceptions et renvoyer une erreur au format JSON
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors de la création de la carte: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
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

    @GetMapping("/{cvv}")
    public ResponseEntity<CarteVirtuelleDTO> getCarteByCvv(@PathVariable String cvv) {
        CarteVirtuelleDTO carte = carteVirtuelleService.getCarteByCvv(cvv);
        return ResponseEntity.ok(carte);
    }

    @PutMapping("/{id}/debit")
    public ResponseEntity<String> debitCard(@PathVariable Long id, @RequestParam Double amount) {
        carteVirtuelleService.debitCard(id, amount);
        return ResponseEntity.ok("Montant débité avec succès");
    }

    @GetMapping("/id-by-cvv")
    public ResponseEntity<Long> getCardIdByCvv(@RequestParam String cvv) {
        Long id = carteVirtuelleService.getCardIdByCvv(cvv);
        return ResponseEntity.ok(id);
    }


}
