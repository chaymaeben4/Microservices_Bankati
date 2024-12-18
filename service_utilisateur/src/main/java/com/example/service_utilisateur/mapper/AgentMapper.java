package com.example.service_utilisateur.mapper;

import org.example.dto.AgentDTO;
import org.example.entites.Agent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AgentMapper {
    AgentMapper INSTANCE = Mappers.getMapper(AgentMapper.class);
    @Mapping(source = "verified", target = "isVerified")
    AgentDTO agentToAgentDTO(Agent agent);
    @Mapping(source = "isVerified", target = "verified")
    Agent AgentDTOToAgent(AgentDTO agentDTO);

}
