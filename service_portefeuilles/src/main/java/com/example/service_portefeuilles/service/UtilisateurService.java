package com.example.service_portefeuilles.service;

import com.example.service_portefeuilles.repository.UtilisateurRepository;
import org.example.entites.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Utilisateur findById(Long id) {
        return utilisateurRepository.findById(id).orElse(null);
    }
}
