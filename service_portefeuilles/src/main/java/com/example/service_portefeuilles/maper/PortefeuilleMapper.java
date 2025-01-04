package com.example.service_portefeuilles.maper;

import com.example.service_portefeuilles.dto.PortefeuilleDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.entites.Portefeuilles;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class PortefeuilleMapper {
    @Autowired
    private ModelMapper mapper;

    public PortefeuilleDto toDTO(Portefeuilles portefeuilles) {
        return mapper.map(portefeuilles, PortefeuilleDto.class);
    }

    // Convertir un ExpenseDTO en entité Expense
    public Portefeuilles toEntity(PortefeuilleDto portefeuilleDto) {
        return mapper.map(portefeuilleDto, Portefeuilles.class);
    }
    public List<PortefeuilleDto> toDTO(List<Portefeuilles> portefeuillesList) {
        return portefeuillesList.stream()
                .map(portefeuilles -> mapper.map(portefeuilles, PortefeuilleDto.class))
                .collect(Collectors.toList());
    }
}
