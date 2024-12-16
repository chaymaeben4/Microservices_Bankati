package com.example.service_paiement_multidevises.service;

import org.example.enums.Devise;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Service
public class ExchangeRateService {

    @Value("${exchangerate.api.url}")
    private String apiUrl;

    @Value("${exchangerate.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public ExchangeRateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Double getExchangeRate(Devise fromCurrency, Devise toCurrency) {
        // Convertir les devises en chaînes de caractères compatibles avec l'API
        String fromCurrencyString = fromCurrency.name();
        String toCurrencyString = toCurrency.name();

        // Construire l'URL avec la devise de base
        String url = apiUrl + fromCurrencyString + "?apikey=" + apiKey;
        System.out.println("url: " + url);

        // Appeler l'API pour récupérer les taux
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        if (response == null || !response.containsKey("conversion_rates")) {
            throw new RuntimeException("Erreur lors de la récupération des taux de change");
        }

        // Extraire les taux de conversion
        Map<String, Double> rates = (Map<String, Double>) response.get("conversion_rates");
        System.out.println("rates: " + rates);

        // Vérifier que la devise cible existe dans les taux de conversion
        if (!rates.containsKey(toCurrencyString)) {
            throw new RuntimeException("Devise cible non trouvée dans les taux de conversion");
        }
        System.out.println("rate : " + rates.get(toCurrencyString));
        return rates.get(toCurrencyString);
    }

}

