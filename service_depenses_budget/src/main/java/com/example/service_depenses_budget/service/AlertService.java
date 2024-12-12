package com.example.service_depenses_budget.service;

import com.example.service_depenses_budget.dto.AlertDTO;
import com.example.service_depenses_budget.entity.Alert;
import com.example.service_depenses_budget.repository.AlertRepository;
import com.example.service_depenses_budget.mapper.AlertMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private AlertMapper alertMapper;

    // Crée une nouvelle alerte
    public AlertDTO createAlert(AlertDTO alertDTO) {
        Alert alert = alertMapper.toEntity(alertDTO);
        alert = alertRepository.save(alert);
        return alertMapper.toDTO(alert);
    }

    // Récupère une alerte par son ID
    public AlertDTO getAlertById(Long id) {
        Alert alert = alertRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alert not found"));
        return alertMapper.toDTO(alert);
    }

    // Met à jour une alerte existante
    public AlertDTO updateAlert(Long id, AlertDTO alertDTO) {
        Alert alert = alertRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alert not found"));
        alert.setAlertMessage(alertDTO.getAlertMessage());
        alert.setAlertDate(alertDTO.getAlertDate());
        alert = alertRepository.save(alert);
        return alertMapper.toDTO(alert);
    }

    // Supprime une alerte par son ID
    public void deleteAlert(Long id) {
        alertRepository.deleteById(id);
    }

    // Récupère toutes les alertes
    public List<AlertDTO> getAllAlerts() {
        List<Alert> alerts = alertRepository.findAll();
        return alertMapper.toDTO(alerts);
    }
}
