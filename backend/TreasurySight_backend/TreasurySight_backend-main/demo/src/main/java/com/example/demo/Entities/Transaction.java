package com.example.demo.Entities;

import jakarta.persistence.*;
import lombok.Data;
import com.example.demo.enums.*;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private Double montant;

    @Enumerated(EnumType.STRING)
    private TypeOperation typeOperation;

    private LocalDate dateTransaction;

    @Enumerated(EnumType.STRING)
    private SousCategorie sousCategorie;

    private Double tauxTva;

    @Enumerated(EnumType.STRING)
    private SourceTransaction source;

    private String referenceExterne;

    private Boolean categoriseAuto;

    @Enumerated(EnumType.STRING)
    private StatutTransaction statut;

    @ManyToOne
    @JoinColumn(name = "id_compte", nullable = true)
    private CompteBancaire compte;

    @ManyToOne
    @JoinColumn(name = "id_entreprise")
    private Entreprise entreprise;
}