package com.example.cmi.client;


import com.example.cmi.dto.PortefeuilleDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "service-portefeuilles", url = "http://localhost:8086/api/portefeuilles")
@Component
public interface PortefeuilleClient {
    @GetMapping("/{id}")
    PortefeuilleDTO getPortefeuilleById(@PathVariable("id") Long id);

    @PutMapping("/{id}/{amount}")
    void updatePortefeuille(@PathVariable("id") Long portefeuilleId, @PathVariable("amount") Double amount);

    @PutMapping("{id}/{amount}")
    String debitPortefeuille(@PathVariable Long id, @PathVariable Double amount) ;


//    @PutMapping("/{id}/debit")
//    void debitPortefeuille(@PathVariable("id") Long portefeuilleId, @RequestParam("amount") Double amount);
}
