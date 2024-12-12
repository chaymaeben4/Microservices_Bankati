package com.example.service_depenses_budget.mapper;

import com.example.service_depenses_budget.dto.AlertDTO;
import com.example.service_depenses_budget.entity.Alert;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class AlertMapper {

    private final ModelMapper modelMapper;

    // Mapper une entité Alert en AlertDTO
    public AlertDTO toDTO(Alert alert) {
        return modelMapper.map(alert, AlertDTO.class);
    }

    // Mapper un AlertDTO en entité Alert
    public Alert toEntity(AlertDTO alertDTO) {
        return modelMapper.map(alertDTO, Alert.class);
    }

    // Mapper une liste d'entités Alert en une liste de AlertDTO
    public List<AlertDTO> toDTO(List<Alert> alerts) {
        return alerts.stream()
                .map(alert -> modelMapper.map(alert, AlertDTO.class))
                .collect(Collectors.toList());
    }
}
