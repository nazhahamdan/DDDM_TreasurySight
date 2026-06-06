package com.example.demo.dto;

import lombok.Data;

@Data
public class ClientKpiDTO {
    private Double totalCa;
    private Double totalOverdueAmount;
    private Double averageDelay;
    private Long riskyClients;
}