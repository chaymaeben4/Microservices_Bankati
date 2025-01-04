package com.example.cmi.service;

import com.example.cmi.client.PortefeuilleClient;
import com.example.cmi.dto.DemandeAlimentationDto;
import com.example.cmi.model.Alimentation;
import com.example.cmi.model.CompteBancaire;
import com.example.cmi.repository.AlimentationRepository;
import com.example.cmi.repository.CompteBancaireRepository;
import com.example.service_portefeuilles.repository.PortefeuilleRepository;
import org.example.entites.Portefeuilles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class cmiService {

    @Autowired
    private CompteBancaireRepository compteBancaireRepository;

    @Autowired
    private AlimentationRepository alimentationRepository;

    @Autowired
    private PortefeuilleClient portefeuilleClient;

//    public String rechargerPortefeuille(DemandeAlimentationDto request) throws Exception {
//
//        // Récupérer le portefeuille
//        Portefeuilles portefeuille = portefeuilleClient.getPortefeuilleById(request.getPortefeuilleId())
//                .orElseThrow(() -> new Exception("Portefeuille introuvable"));
//
//        // Récupérer le compte bancaire
//        CompteBancaire compteBancaire = compteBancaireRepository.findById(request.getCompteId())
//                .orElseThrow(() -> new Exception("Compte bancaire introuvable"));
//
//        // Vérifier que le compte a suffisamment de solde
//        if (compteBancaire.getSolde() < request.getMontant()) {
//            return "Solde insuffisant sur le compte bancaire";
//        }
//
//        // Déduire le montant du compte bancaire
//        compteBancaire.setSolde(compteBancaire.getSolde() - request.getMontant());
//        compteBancaireRepository.save(compteBancaire);
//
//        // Ajouter le montant au portefeuille
//        portefeuille.setBalance(portefeuille.getBalance() + request.getMontant());
//        portefeuilleClient.save(portefeuille);
//
//        // Enregistrer la transaction
//        Alimentation alimentation = new Alimentation();
//        alimentation.setMontant(request.getMontant());
//        alimentation.setDestinateur(compteBancaire);
//        alimentation.setDestinataire(portefeuille);
//        alimentationRepository.save(alimentation);
//
//        // Retourner un message de succès
//        return "Le portefeuille a été rechargé avec succès avec un montant de " + request.getMontant() + " " + compteBancaire.getDevise();
//    }
//
//    public String creerPortefeuille(CreationPortefeuilleRequestDto request) {
//        if(portefeuilleRepository.findByUtilisateurIdAndDevise(request.getUtilisateurId(), request.getDevise()).isPresent()){
//            return "Un portefeuille existe déjà pour cet utilisateur et cette devise.";
//        }
//        Portefeuilles portefeuille = new Portefeuilles();
//        portefeuille.setUtilisateur(utilisateurService.findById(request.getUtilisateurId()));
//        portefeuille.setDevise(request.getDevise());
//        portefeuille.setBalance(request.getBalanceInitiale());
//        portefeuilleRepository.save(portefeuille);
//
//        return "Portefeuille créé avec succès avec la devise " + request.getDevise();
//    }


}
