package com.example.service_utilisateur.controller;

import com.example.service_utilisateur.mapper.AgentMapper;
import com.example.service_utilisateur.mapper.ClientMapper;
import com.example.service_utilisateur.repository.AgentRepository;
import com.example.service_utilisateur.repository.ClientRepository;
import org.example.dto.AgentDTO;
import org.example.dto.ClientRequestDTO;
import org.example.entites.Agent;
import org.example.entites.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.service_utilisateur.service.UtilisateurService;

@RestController
@RequestMapping("/api")

public class UtilisateurController {

    @Autowired
    final private UtilisateurService utilisateurService;
    final private AgentRepository agentRepository;
    final private ClientRepository clientRepository;


    public UtilisateurController(UtilisateurService utilisateurService, AgentRepository agentRepository, ClientRepository clientRepository, AgentRepository agentRepository1, ClientRepository clientRepository1) {
        this.utilisateurService = utilisateurService;
        this.agentRepository = agentRepository1;
        this.clientRepository = clientRepository1;
    }



    @PostMapping("/agents")
//    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<Object> creerAgent(@RequestBody Agent agent) {
        String errorMessage = utilisateurService.creerAgent(agent);

        if (errorMessage != null) {
            return ResponseEntity.badRequest().body(errorMessage);
        }

        return ResponseEntity.ok(agent);
    }
    // Endpoint pour mettre à jour un agent
    @PutMapping("/agents/{id}")
    public ResponseEntity<Object> updateAgent(@PathVariable Long id, @RequestBody AgentDTO agentDTO) {
        // Appel de la fonction service pour mettre à jour l'agent
        String errorMessage = utilisateurService.updateAgent(id, agentDTO);

        if (errorMessage != null) {
            // Retourner une réponse d'erreur avec message
            return ResponseEntity.badRequest().body(errorMessage);
        }

        // Retourner l'agent mis à jour en base de données
        Agent updatedAgent = agentRepository.findById(id).orElse(null);

        if (updatedAgent == null) {
            return ResponseEntity.notFound().build();
        }

        AgentDTO updatedAgentDTO = AgentMapper.INSTANCE.agentToAgentDTO(updatedAgent);
        return ResponseEntity.ok(updatedAgentDTO);
    }





    // Endpoint pour supprimer un agent
    @DeleteMapping("/agents/{id}")
    public ResponseEntity<Object> deleteAgent(@PathVariable Long id) {
        String result = utilisateurService.deleteAgent(id);

        if (result.equals("Agent deleted successfully")) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint pour créer un client
    @PostMapping("/clients")
// @PreAuthorize("hasRole('ADMIN')") // Seul un ADMIN peut créer des clients
    public ResponseEntity<Object> creerClient(@RequestBody ClientRequestDTO clientDTO) {
        // Appel de la méthode service pour créer le client
        String errorMessage = utilisateurService.creerClient(clientDTO);

        if (errorMessage != null) {
            // Si une erreur a été renvoyée (ex: email déjà utilisé)
            return ResponseEntity.badRequest().body(errorMessage);
        }

        // Si tout va bien, renvoi du client créé
        return ResponseEntity.ok(clientDTO);
    }
    @PutMapping("/clients/{id}")
    public ResponseEntity<Object> updateClient(@PathVariable Long id, @RequestBody ClientRequestDTO clientDTO) {
        String errorMessage = utilisateurService.updateClient(id, clientDTO);

        if (errorMessage != null) {
            return ResponseEntity.badRequest().body(errorMessage);
        }

        Client updatedClient = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client non trouvé après mise à jour."));

        ClientRequestDTO updatedClientDTO = ClientMapper.INSTANCE.clientToClientRequestDTO(updatedClient);
        return ResponseEntity.ok(updatedClientDTO);
    }



    // Endpoint pour supprimer un client
    @DeleteMapping("/clients/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable Long id) {
        String result = utilisateurService.deleteClient(id);

        if (result.equals("Client deleted successfully")) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }




}
