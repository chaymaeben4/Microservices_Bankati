package com.example.service_transactions.external;

import com.example.service_transactions.dto.TransactionRequest;
import org.example.entites.Transaction;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
@Service
@FeignClient(name = "banking-client", url = "${external.banking-service.url:http://core-banking-service}")
public interface ExternalBankingClient {
    @PostMapping("/authorize")
    void authorizePayment(@RequestBody Transaction transaction);

    @PostMapping("/transactions")
    void authorizeTransaction(@RequestBody TransactionRequest request);

    @GetMapping("/confirm/{transactionId}")
    boolean confirmTransaction(@PathVariable Long transactionId);
}
