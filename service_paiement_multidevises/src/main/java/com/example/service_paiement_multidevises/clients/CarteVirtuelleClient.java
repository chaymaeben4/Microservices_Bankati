package com.example.service_paiement_multidevises.clients;

import org.example.dto.CarteVirtuelleDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "service-cartes-virtuelles", url = "http://localhost:8082/api/cartes-virtuelles")
public interface CarteVirtuelleClient {

    @GetMapping("/{cvv}")
    CarteVirtuelleDTO getCarteByCvv(@PathVariable String cvv);

    @PutMapping("/{id}/debit")
    void debitCard(@PathVariable Long id, @RequestParam Double amount);

    @GetMapping("/id-by-cvv")
    Long getCardIdByCvv(@RequestParam String cvv);
}
