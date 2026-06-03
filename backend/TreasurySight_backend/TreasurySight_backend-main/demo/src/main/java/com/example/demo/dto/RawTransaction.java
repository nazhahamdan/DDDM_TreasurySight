package com.example.demo.dto;

import lombok.Data;
import java.time.LocalDate;
import com.example.demo.enums.Categorie;
import com.example.demo.enums.SousCategorie;    
import com.example.demo.enums.TypeOperation;

@Data
public class RawTransaction {
    private String description;
    private LocalDate date;
    private Double amount;
    private Categorie category;        
    private SousCategorie subCategory; 
    private TypeOperation typeOperation;
}