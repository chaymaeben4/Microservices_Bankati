package com.example.service_paiement_multidevises.clients;

import org.example.dto.CarteVirtuelleDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "service-cartes-virtuelles", url = "http://localhost:8082/api/cartes-virtuelles")
public interface CarteVirtuelleClient {

    @PostMapping
    ResponseEntity<Map<String, String>> creerCarte(@RequestBody CarteVirtuelleDTO carteVirtuelleDTO);

    @GetMapping("/{cvv}")
    CarteVirtuelleDTO getCarteByCvv(@PathVariable String cvv);

    @PutMapping("/{id}/debit")
    void debitCard(@PathVariable Long id, @RequestParam Double amount);

    @GetMapping("/id-by-cvv")
    Long getCardIdByCvv(@RequestParam String cvv);

    @DeleteMapping("/{carteId}")
    ResponseEntity<Void> supprimerCarte(@PathVariable Long carteId);
}
