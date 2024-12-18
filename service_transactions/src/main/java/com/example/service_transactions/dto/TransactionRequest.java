package com.example.service_transactions.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionRequest {
    private Long sourceAccountId;
    private Long destinationAccountId;
    private BigDecimal amount;
    private String description;
}
