package com.example.service_utilisateur.mapper;

import org.example.dto.ClientRequestDTO;
import org.example.entites.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    ClientRequestDTO clientToClientRequestDTO(Client client);

    Client clientRequestDTOToClient(ClientRequestDTO clientRequestDTO);
}
