package com.example.service_portefeuilles.service;

import com.example.service_portefeuilles.dto.CreationPortefeuilleRequestDto;
import com.example.service_portefeuilles.dto.DemandeAlimentationDto;
import com.example.service_portefeuilles.dto.SoldeResponseDto;
import com.example.service_portefeuilles.model.Alimentation;
import com.example.service_portefeuilles.repository.AlimentationRepository;
import com.example.service_portefeuilles.repository.CompteBancaireRepository;
import com.example.service_portefeuilles.repository.PortefeuilleRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.dto.PortefeuillesDTO;
import org.example.entites.CompteBancaire;
import org.example.entites.Portefeuilles;
import org.example.enums.Devise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PortefeuilleService {

    @Autowired
    private PortefeuilleRepository portefeuilleRepository;

    @Autowired
    private CompteBancaireRepository compteBancaireRepository;

    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private AlimentationRepository alimentationRepository;

    public List<PortefeuillesDTO> recupererPortefeuillesParUtilisateur(Long utilisateurId) {
        return portefeuilleRepository.findByUtilisateurId(utilisateurId)
                .stream()
                .map(p -> new PortefeuillesDTO(p.getId(),utilisateurId, p.getBalance(), p.getDevise()))
                .collect(Collectors.toList());
    }


    public SoldeResponseDto consulterSolde(Long portefeuilleId) {
        Portefeuilles portefeuille = portefeuilleRepository.findById(portefeuilleId)
                .orElseThrow(() -> new RuntimeException("Portefeuille non trouvé !"));
        return new SoldeResponseDto(portefeuille.getBalance(), portefeuille.getDevise());
    }


    public String creerPortefeuille(CreationPortefeuilleRequestDto request) {
        if(portefeuilleRepository.findByUtilisateurIdAndDevise(request.getUtilisateurId(), request.getDevise()).isPresent()){
            return "Un portefeuille existe déjà pour cet utilisateur et cette devise.";
        }
        Portefeuilles portefeuille = new Portefeuilles();
        portefeuille.setUtilisateur(utilisateurService.findById(request.getUtilisateurId()));
        portefeuille.setDevise(request.getDevise());
        portefeuille.setBalance(request.getBalanceInitiale());

        portefeuilleRepository.save(portefeuille);

        return "Portefeuille créé avec succès avec la devise " + request.getDevise();
    }


    public String rechargerPortefeuille(DemandeAlimentationDto request) throws Exception {

        // Récupérer le portefeuille
        Portefeuilles portefeuille = portefeuilleRepository.findById(request.getPortefeuilleId())
                .orElseThrow(() -> new Exception("Portefeuille introuvable"));

        // Récupérer le compte bancaire
        CompteBancaire compteBancaire = compteBancaireRepository.findById(request.getCompteId())
                .orElseThrow(() -> new Exception("Compte bancaire introuvable"));

        // Vérifier que le compte a suffisamment de solde
        if (compteBancaire.getSolde() < request.getMontant()) {
            return "Solde insuffisant sur le compte bancaire";
        }

        // Déduire le montant du compte bancaire
        compteBancaire.setSolde(compteBancaire.getSolde() - request.getMontant());
        compteBancaireRepository.save(compteBancaire);

        // Ajouter le montant au portefeuille
        portefeuille.setBalance(portefeuille.getBalance() + request.getMontant());
        portefeuilleRepository.save(portefeuille);

        // Enregistrer la transaction
        Alimentation alimentation = new Alimentation();
        alimentation.setMontant(request.getMontant());
        alimentation.setDestinateur(compteBancaire);
        alimentation.setDestinataire(portefeuille);
        alimentationRepository.save(alimentation);

        // Retourner un message de succès
        return "Le portefeuille a été rechargé avec succès avec un montant de " + request.getMontant() + " " + compteBancaire.getDevise();
    }
}
