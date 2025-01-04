package com.example.service_paiement_multidevises.controller;

import com.example.service_paiement_multidevises.service.MultiDevisesService;
import org.example.dto.TransactionDTO;
import org.example.entites.Transaction;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PaymentController {

    private final MultiDevisesService paymentService;

    public PaymentController(MultiDevisesService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/payment/{senderWalletId}/{receiverWalletId}/{amount}")
    public TransactionDTO makePayment(@PathVariable Long senderWalletId,
                                      @PathVariable Long receiverWalletId,
                                      @PathVariable Double amount) {
        return paymentService.processPayment(senderWalletId, receiverWalletId, amount);
    }

}


