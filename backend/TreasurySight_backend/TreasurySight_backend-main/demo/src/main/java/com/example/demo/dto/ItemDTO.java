package com.example.demo.dto;

import lombok.Data;

@Data
public class ItemDTO {
    private String label;
    private Double amount;
    private String date;
    private String type; // TRANSACTION or EVENT
}
