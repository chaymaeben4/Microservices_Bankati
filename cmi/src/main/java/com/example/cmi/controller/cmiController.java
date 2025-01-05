package com.example.cmi.controller;

import com.example.cmi.dto.CreationPortefeuilleRequestDto;
import com.example.cmi.dto.DemandeAlimentationDto;
import com.example.cmi.model.CompteBancaire;
import com.example.cmi.service.CmiService;
import com.example.service_portefeuilles.model.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cmi")
public class cmiController {

    @Autowired
    private CmiService cmiService;

    @PostMapping("/alimenterPortefeuille")
    public ResponseEntity<Alert> alimenterPortefeuille(@RequestBody DemandeAlimentationDto demande) {
        Alert response = cmiService.alimenterPortefeuille(demande);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/creerPortefeuille")
    public ResponseEntity<Alert> creerPortefeuille(@RequestBody CreationPortefeuilleRequestDto request) {
        Alert response = cmiService.creerPortefeuille(request);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{compteId}/assigner-utilisateur")
    public ResponseEntity<CompteBancaire> assignerUtilisateur(@PathVariable String NRcompte, @RequestParam Long utilisateurId) {
        CompteBancaire compteMisAJour = cmiService.assignerUtilisateur(NRcompte, utilisateurId);
        return ResponseEntity.ok(compteMisAJour);
    }
}
