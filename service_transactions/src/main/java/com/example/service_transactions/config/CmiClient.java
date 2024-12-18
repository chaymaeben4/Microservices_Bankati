package com.example.service_transactions.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cmi-service")
public interface CmiClient {

    @GetMapping("/solde/{utilisateurId}")
    Double verifierSolde(@PathVariable Long utilisateurId);
}
