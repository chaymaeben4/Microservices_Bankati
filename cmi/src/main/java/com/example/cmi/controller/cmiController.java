package com.example.cmi.controller;

import com.example.cmi.dto.CreationPortefeuilleRequestDto;
import com.example.cmi.dto.DemandeAlimentationDto;
import com.example.cmi.service.CmiService;
import com.example.service_portefeuilles.model.Alert;
import com.example.service_portefeuilles.service.PortefeuilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cmi")
public class cmiController {

    @Autowired
    private CmiService cmiService;

    @PostMapping("/creerPortefeuille")
    public ResponseEntity<Alert> creerPortefeuille(@RequestBody CreationPortefeuilleRequestDto request) {
        System.out.println(
                "----------------------------------------------------------------"
        );
        System.out.println(request.getutilisateurId()+"---"+request.getDevise()+"---"+request.getBalanceInitiale());
        System.out.println(
                "----------------------------------------------------------------"
        );
        Alert alert = cmiService.creerPortefeuille(request);
        return ResponseEntity.ok(alert);
    }
    @PostMapping("/alimenterPortefeuille")
    public ResponseEntity<Alert> alimenterPortefeuille(@RequestBody DemandeAlimentationDto demande) {
        Alert alert = cmiService.alimenterPortefeuille(demande);
        return ResponseEntity.ok(alert);
    }

}
