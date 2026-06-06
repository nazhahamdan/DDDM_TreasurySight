package com.example.demo.dto;

import lombok.Data;

@Data
public class ClientRiskDTO {
    private String client;
    private Double totalCa;
    private Double overdueAmount;
    private Double averageDelay;
    private Long invoiceCount;
    private Long lateInvoiceCount;
    private Double score;
}