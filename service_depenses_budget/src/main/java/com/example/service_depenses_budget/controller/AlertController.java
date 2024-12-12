package com.example.service_depenses_budget.controller;

import com.example.service_depenses_budget.dto.AlertDTO;
import com.example.service_depenses_budget.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
public class AlertController {

    @Autowired
    private AlertService alertService;

    // Crée une nouvelle alerte
    @PostMapping
    public ResponseEntity<AlertDTO> createAlert(@RequestBody AlertDTO alertDTO) {
        AlertDTO createdAlert = alertService.createAlert(alertDTO);
        return new ResponseEntity<>(createdAlert, HttpStatus.CREATED);
    }

    // Récupère une alerte par son ID
    @GetMapping("/{id}")
    public ResponseEntity<AlertDTO> getAlertById(@PathVariable Long id) {
        AlertDTO alert = alertService.getAlertById(id);
        return new ResponseEntity<>(alert, HttpStatus.OK);
    }

    // Récupère toutes les alertes
    @GetMapping
    public ResponseEntity<List<AlertDTO>> getAllAlerts() {
        List<AlertDTO> alerts = alertService.getAllAlerts();
        return new ResponseEntity<>(alerts, HttpStatus.OK);
    }

    // Met à jour une alerte existante
    @PutMapping("/{id}")
    public ResponseEntity<AlertDTO> updateAlert(@PathVariable Long id, @RequestBody AlertDTO alertDTO) {
        AlertDTO updatedAlert = alertService.updateAlert(id, alertDTO);
        return new ResponseEntity<>(updatedAlert, HttpStatus.OK);
    }

    // Supprime une alerte
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlert(@PathVariable Long id) {
        alertService.deleteAlert(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
