package com.example.service_utilisateur.service;

import com.example.service_utilisateur.mapper.ClientMapper;
import com.example.service_utilisateur.repository.AgentRepository;
import com.example.service_utilisateur.repository.ClientRepository;
import org.example.dto.AgentDTO;
import org.example.dto.ClientRequestDTO;
import org.example.entites.Agent;
import org.example.entites.Client;
import org.example.entites.Utilisateur;
import org.example.enums.Role;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.service_utilisateur.repository.UtilisateurRepository;


import java.util.Arrays;

import static org.example.enums.Role.AGENT;

@Service
public class UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;
    private final AgentRepository agentRepository;
    private final ClientRepository clientRepository;

    public UtilisateurService(UtilisateurRepository utilisateurRepository,
                              AgentRepository agentRepository,
                              ClientRepository clientRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.agentRepository = agentRepository;
        this.clientRepository = clientRepository;
    }

    // Fonction pour mettre à jour un agent
    public String updateAgent(Long id, AgentDTO agentDTO) {
        // Vérification si l'agent existe déjà dans la base
        Agent existingAgent = agentRepository.findById(id)
                .orElse(null);

        if (existingAgent == null) {
            return "Agent non trouvé.";
        }

        // Vérification si l'email est déjà utilisé par un autre agent
        if (!existingAgent.getEmail().equals(agentDTO.getEmail()) && utilisateurRepository.findByEmail(agentDTO.getEmail()).isPresent()) {
            return "L'email est déjà utilisé.";
        }
        // Mise à jour des attributs de l'agent (uniquement si non-null)
        if (agentDTO.getNom() != null) {
            existingAgent.setNom(agentDTO.getNom());
        }
        if (agentDTO.getPrenom() != null) {
            existingAgent.setPrenom(agentDTO.getPrenom());
        }
        if (agentDTO.getEmail() != null) {
            existingAgent.setEmail(agentDTO.getEmail());
        }
        if (agentDTO.getAgence() != null) {
            existingAgent.setAgence(agentDTO.getAgence());
        }

        if (agentDTO.getIsVerified() != null) {
            existingAgent.setVerified(agentDTO.getIsVerified());
        }


        agentRepository.save(existingAgent);

        return null;  // Retourne null si tout est ok
    }





    // Créer un agent
    public String creerAgent(Agent agent) {
        // Vérification de l'email unique
        if (utilisateurRepository.findByEmail(agent.getEmail()).isPresent()) {
            return "L'email est déjà utilisé.";
        }

        // Vérification de l'agence
        if (agent.getAgence() == null || agent.getAgence().isEmpty()) {
            return "L'agence doit être spécifiée pour un agent.";
        }

        agent.setRole(Role.AGENT);
        agent.setVerified(false);
        agent.setMdp(generatePassword());

        // Enregistrement de l'agent
        agentRepository.save(agent);
        return null; // Si tout est OK, retour d'une valeur nulle
    }

    public String deleteAgent(Long id) {
        return agentRepository.findById(id)
                .map(agent -> {
                    agentRepository.delete(agent);
                    return "Client deleted successfully"; // Return a message

                })
                .orElse("Agent not found");
    }



    public String creerClient(ClientRequestDTO clientDTO) {
        // Vérification de l'email unique
        if (utilisateurRepository.findByEmail(clientDTO.getEmail()).isPresent()) {
            return "L'email est déjà utilisé.";
        }

        if (clientRepository.findByNumeroTelephone(clientDTO.getNumeroTelephone()).isPresent()) {
            return "Le numéro de téléphone est déjà utilisé.";
        }
        if (clientRepository.findByNumeroPieceIdentite(clientDTO.getNumeroPieceIdentite()).isPresent()) {
            return "Le numéro de pièce d'identité est déjà utilisé.";
        }
        // Vérification des autres attributs spécifiques à Client
        if (clientRepository.findByNumeroImmatriculationCommerce(clientDTO.getNumeroImmatriculationCommerce()).isPresent()) {
            return "Le numéro d'immatriculation commerciale est déjà utilisé.";
        }

        if (clientRepository.findByNumeroPatente(clientDTO.getNumeroPatente()).isPresent()) {
            return "Le numéro de patente est déjà utilisé.";
        }

        // Création du client
        Client client = ClientMapper.INSTANCE.clientRequestDTOToClient(clientDTO);
        client.setMdp(generatePassword());
        client.setRole(Role.CLIENT);
        client.setVerified(false);

        // Enregistrement du client
        clientRepository.save(client);
        return null; // Si tout va bien, retour d'une valeur nulle
    }

    // Générer un mot de passe aléatoire
    public static String generatePassword() {
        PasswordGenerator generator = new PasswordGenerator();
        CharacterRule lowerCaseRule = new CharacterRule(EnglishCharacterData.LowerCase, 2);
        CharacterRule upperCaseRule = new CharacterRule(EnglishCharacterData.UpperCase, 2);
        CharacterRule digitRule = new CharacterRule(EnglishCharacterData.Digit, 2);
        CharacterRule specialCharRule = new CharacterRule(EnglishCharacterData.Special, 2);
        return generator.generatePassword(12, Arrays.asList(
                lowerCaseRule, upperCaseRule, digitRule, specialCharRule
        ));
    }
    public String updateClient(Long id, ClientRequestDTO clientDTO) {
        Client existingClient = clientRepository.findById(id)
                .orElse(null);

        if (existingClient == null) {
            return "Client non trouvé.";
        }

        // Vérifier si l'email est unique
        if (!existingClient.getEmail().equals(clientDTO.getEmail()) &&
                utilisateurRepository.findByEmail(clientDTO.getEmail()).isPresent()) {
            return "L'email est déjà utilisé.";
        }

        // Vérifier si le numéro de téléphone est unique
        if (!existingClient.getNumeroTelephone().equals(clientDTO.getNumeroTelephone()) &&
                clientRepository.findByNumeroTelephone(clientDTO.getNumeroTelephone()).isPresent()) {
            return "Le numéro de téléphone est déjà utilisé.";
        }

        // Vérifier si le numéro de pièce d'identité est unique
        if (!existingClient.getNumeroPieceIdentite().equals(clientDTO.getNumeroPieceIdentite()) &&
                clientRepository.findByNumeroPieceIdentite(clientDTO.getNumeroPieceIdentite()).isPresent()) {
            return "Le numéro de pièce d'identité est déjà utilisé.";
        }

        // Vérifier si le numéro d'immatriculation commerciale est unique
        if (clientDTO.getNumeroImmatriculationCommerce() != null &&
                !clientDTO.getNumeroImmatriculationCommerce().equals(existingClient.getNumeroImmatriculationCommerce()) &&
                clientRepository.findByNumeroImmatriculationCommerce(clientDTO.getNumeroImmatriculationCommerce()).isPresent()) {
            return "Le numéro d'immatriculation commerciale est déjà utilisé.";
        }

        // Vérifier si le numéro de patente est unique
        if (clientDTO.getNumeroPatente() != null &&
                !clientDTO.getNumeroPatente().equals(existingClient.getNumeroPatente()) &&
                clientRepository.findByNumeroPatente(clientDTO.getNumeroPatente()).isPresent()) {
            return "Le numéro de patente est déjà utilisé.";
        }

        // Mise à jour des champs
        if (clientDTO.getNom() != null) {
            existingClient.setNom(clientDTO.getNom());
        }
        if (clientDTO.getPrenom() != null) {
            existingClient.setPrenom(clientDTO.getPrenom());
        }
        if (clientDTO.getEmail() != null) {
            existingClient.setEmail(clientDTO.getEmail());
        }
        if (clientDTO.getNumeroTelephone() != null) {
            existingClient.setNumeroTelephone(clientDTO.getNumeroTelephone());
        }
        if (clientDTO.getNumeroPieceIdentite() != null) {
            existingClient.setNumeroPieceIdentite(clientDTO.getNumeroPieceIdentite());
        }
        if (clientDTO.getNumeroImmatriculationCommerce() != null) {
            existingClient.setNumeroImmatriculationCommerce(clientDTO.getNumeroImmatriculationCommerce());
        }
        if (clientDTO.getNumeroPatente() != null) {
            existingClient.setNumeroPatente(clientDTO.getNumeroPatente());
        }
        if (clientDTO.getAdresse() != null) {
            existingClient.setAdresse(clientDTO.getAdresse());
        }

        clientRepository.save(existingClient);
        return null;
    }

    public String deleteClient(Long id) {
        return clientRepository.findById(id)
                .map(client -> {
                    clientRepository.delete(client);
                    return "Client deleted successfully";
                })
                .orElse("Client not found");
    }



}


