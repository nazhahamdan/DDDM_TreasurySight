package com.example.demo.dto;

import lombok.Data;

@Data
public class ClientScatterDTO {
    private String client;
    private Double totalCa;
    private Double averageDelay;
}