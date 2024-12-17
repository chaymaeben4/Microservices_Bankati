package com.example.service_portefeuilles.Controller;

import com.example.service_portefeuilles.dto.CreationPortefeuilleRequestDto;
import com.example.service_portefeuilles.dto.DemandeAlimentationDto;
import com.example.service_portefeuilles.dto.SoldeResponseDto;
import com.example.service_portefeuilles.service.PortefeuilleService;
import lombok.AllArgsConstructor;
import org.example.dto.PortefeuillesDTO;
import org.example.entites.Portefeuilles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/portefeuilles")
@AllArgsConstructor
public class PortefeuilleController {

    @Autowired
    private final PortefeuilleService portefeuilleService;

    @GetMapping("/utilisateur/{utilisateurId}")
    public ResponseEntity<List<PortefeuillesDTO>> recupererPortefeuilles(@PathVariable Long utilisateurId) {
        List<PortefeuillesDTO> portefeuilles = portefeuilleService.recupererPortefeuillesParUtilisateur(utilisateurId);
        return ResponseEntity.ok(portefeuilles);
    }

    @GetMapping("/solde/{portefeuilleId}")
    public ResponseEntity<SoldeResponseDto> consulterSolde(@PathVariable Long portefeuilleId) {
        SoldeResponseDto solde = portefeuilleService.consulterSolde(portefeuilleId);
        return ResponseEntity.ok(solde);
    }

    @PostMapping("/creer")
    public ResponseEntity<String> creerPortefeuille(@RequestBody CreationPortefeuilleRequestDto request) {
        String message = portefeuilleService.creerPortefeuille(request);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/alimenter")
    public ResponseEntity<String> alimenterPortefeuille(@RequestBody DemandeAlimentationDto request) throws Exception{
        String message= portefeuilleService.rechargerPortefeuille(request);
        return ResponseEntity.ok(message);
    }
}

