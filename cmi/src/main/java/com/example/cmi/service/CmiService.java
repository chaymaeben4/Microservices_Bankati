package com.example.cmi.service;

import com.example.cmi.client.ExchangeClient;
import com.example.cmi.client.PortefeuilleClient;
import com.example.cmi.dto.CreationPortefeuilleRequestDto;
import com.example.cmi.dto.DemandeAlimentationDto;
import com.example.cmi.dto.PortefeuilleDto;
import com.example.cmi.model.CompteBancaire;
import com.example.cmi.repository.AlimentationRepository;
import com.example.cmi.repository.CompteBancaireRepository;
import com.example.service_portefeuilles.model.Alert;
import org.example.enums.Devise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class CmiService {

    @Autowired
    private CompteBancaireRepository compteBancaireRepository;

    @Autowired
    private AlimentationRepository alimentationRepository;

    @Autowired
    private PortefeuilleClient portefeuilleClient;

    @Autowired
    private ExchangeClient exchangeClient;

    @Transactional
    public Alert alimenterPortefeuille(DemandeAlimentationDto demande) {
        try {
            // Étape 1 : Récupérer le compte bancaire de l'utilisateur
            CompteBancaire compteBancaire = compteBancaireRepository.findByUtilisateurId(demande.getUserId());
            if (compteBancaire == null) {
                return new Alert("Compte bancaire introuvable pour l'utilisateur ID : " + demande.getUserId(), LocalDate.now(), false);
            }

            // Étape 2 : Récupérer le portefeuille
            PortefeuilleDto portefeuille = portefeuilleClient.getPortefeuilleById(demande.getPortefeuilleId());
            if (portefeuille == null || !portefeuille.getUtilisateurId().equals(demande.getUserId())) {
                return new Alert("Portefeuille introuvable ou non associé à cet utilisateur.", LocalDate.now(), false);
            }

            // Étape 3 : Vérifier si les devises correspondent
            Double montantAUtiliser = demande.getMontant();
            if (!compteBancaire.getDevise().equals(portefeuille.getDevise())) {
                // Convertir le montant si les devises ne correspondent pas
                montantAUtiliser = this.convertirMontant(
                        compteBancaire.getDevise(),
                        portefeuille.getDevise(),
                        demande.getMontant()
                );
            }

            // Étape 4 : Vérifier si le compte a un solde suffisant
            if (compteBancaire.getSolde() < montantAUtiliser) {
                return new Alert("Solde insuffisant dans le compte bancaire.", LocalDate.now(), false);
            }

            // Étape 5 : Mettre à jour le solde du portefeuille
            portefeuille.setBalance(portefeuille.getBalance() + demande.getMontant());
            portefeuilleClient.crediterPortefeuille(demande.getPortefeuilleId(), demande.getMontant());

            // Étape 6 : Débiter le compte bancaire
            compteBancaire.setSolde(compteBancaire.getSolde() - montantAUtiliser);
            compteBancaireRepository.save(compteBancaire);

            // Retour succès
            return new Alert("Portefeuille alimenté avec succès !", LocalDate.now(), true);
        } catch (Exception e) {
            // En cas d'exception, retourner une alerte d'échec
            return new Alert("Erreur lors de l'alimentation du portefeuille : " + e.getMessage(), LocalDate.now(), false);
        }
    }

@Transactional
public Alert creerPortefeuille(CreationPortefeuilleRequestDto request) {
    try {
        // Étape 1 : Récupérer le compte bancaire de l'utilisateur
        CompteBancaire compteBancaire = compteBancaireRepository
                .findByUtilisateurId(request.getutilisateurId());

        // Vérifier si le compte bancaire existe
        if (compteBancaire == null) {
            return new Alert("Compte bancaire introuvable pour l'utilisateur ID : " + request.getutilisateurId(), LocalDate.now(), false);
        }
        // Étape 2 : Vérifier si les devises correspondent
        Double montantAUtiliser = request.getBalanceInitiale();
        if (!compteBancaire.getDevise().equals(request.getDevise())) {
            // Appeler le service d'échange pour convertir le montant
//            montantAUtiliser = this.convertirMontant(
//                    compteBancaire.getDevise(),
//                    request.getDevise(),
//                    request.getBalanceInitiale()
//            );
        }

        // Étape 3 : Vérifier si le compte a un solde suffisant
        if (compteBancaire.getSolde() < montantAUtiliser) {
            return new Alert("Solde insuffisant dans le compte bancaire.", LocalDate.now(), false);
        }

        // Étape 4 : Créer le portefeuille
        PortefeuilleDto portefeuille = new PortefeuilleDto();
        portefeuille.setUtilisateurId(request.getutilisateurId());
        portefeuille.setDevise(request.getDevise());
        portefeuille.setBalance(montantAUtiliser); // Solde initial dans la devise du portefeuille
        String msg = portefeuilleClient.creerPortefeuille(portefeuille);

        // Étape 5 : Débiter le compte bancaire
        compteBancaire.setSolde(compteBancaire.getSolde() - montantAUtiliser);
        compteBancaireRepository.save(compteBancaire);

        // Retour succès
        return new Alert("Portefeuille créé avec succès !", LocalDate.now(), true);
    } catch (Exception e) {
        // En cas d'exception, retourner une alerte d'échec
        return new Alert("Erreur lors de la création du portefeuille : " + e.getMessage(), LocalDate.now(), false);
    }
}


    public Double convertirMontant(Devise from, Devise to, Double montant) {
        // Appeler le service distant pour obtenir le taux de conversion
        Double taux = exchangeClient.getExchangeRate(from, to);
        // Appliquer le taux de conversion
        return montant * taux;
    }


}
