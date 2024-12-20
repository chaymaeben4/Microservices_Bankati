package com.example.service_cartes_virtuelles.service;

import com.example.service_cartes_virtuelles.mapper.CarteVirtuelleMapper;
import com.example.service_cartes_virtuelles.repo.CarteVirtuelleRepository;
import org.example.dto.CarteVirtuelleDTO;
import org.example.entites.CarteVirtuelle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarteVirtuelleService {

    @Autowired
    private CarteVirtuelleRepository carteVirtuelleRepository;

    @Autowired
    private CarteVirtuelleMapper carteVirtuelleMapper;

    private static final String ALGORITHM = "AES";
    private static final String SECRET_KEY = "your-secret-key"; // Remplacer par une clé sécurisée stockée dans un endroit sûr

    public CarteVirtuelleDTO creerCarte(CarteVirtuelleDTO carteVirtuelleDTO) {
        CarteVirtuelle carteVirtuelle = carteVirtuelleMapper.toEntity(carteVirtuelleDTO);

        // Générer les détails spécifiques de la carte virtuelle
        carteVirtuelle.setNumero_carte(crypterNumeroCarte(genererNumeroCarte()));
        carteVirtuelle.setCvv(crypterCVV(genererCVV()));
        carteVirtuelle.setDate_expiration(LocalDate.now().plusYears(1));

        CarteVirtuelle savedCarte = carteVirtuelleRepository.save(carteVirtuelle);
        return carteVirtuelleMapper.toDTO(savedCarte);
    }

    // Méthode pour générer le numéro de carte virtuel (exemple simple)
    private String genererNumeroCarte() {
        return "411111111111" + (int) (Math.random() * 10000); // Exemple simple
    }

    // Méthode pour générer un CVV à 3 chiffres
    private String genererCVV() {
        return String.valueOf((int) (Math.random() * 900) + 100); // CVV aléatoire
    }

    // Méthode pour crypter le numéro de carte
    private String crypterNumeroCarte(String numeroCarte) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encrypted = cipher.doFinal(numeroCarte.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors du cryptage du numéro de carte", e);
        }
    }

    // Méthode pour crypter le CVV
    private String crypterCVV(String cvv) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encrypted = cipher.doFinal(cvv.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors du cryptage du CVV", e);
        }
    }

    // Méthode pour décrypter les données lorsque nécessaire
    public String decrypter(String encryptedData) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decoded = Base64.getDecoder().decode(encryptedData);
            byte[] decrypted = cipher.doFinal(decoded);
            return new String(decrypted);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors du décryptage des données", e);
        }
    }

    // Méthode pour supprimer une carte virtuelle
    public boolean supprimerCarte(Long id) {
        if (carteVirtuelleRepository.existsById(id)) {
            carteVirtuelleRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<CarteVirtuelleDTO> getCartesParUtilisateur(Long utilisateurId) {
        List<CarteVirtuelle> cartes = carteVirtuelleRepository.findByUtilisateurId(utilisateurId);
        return cartes.stream()
                .map(carteVirtuelleMapper::toDTO)
                .collect(Collectors.toList());
    }

}
