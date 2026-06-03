package com.example.demo.Entities;

import com.example.demo.enums.SousCategorie;
import com.example.demo.enums.StatutEvenement;
import com.example.demo.enums.TypeEvenement;
import com.example.demo.enums.TypeOperation;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
@Entity
@Data
@Table(name = "evenements")
public class Evenement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Double montant;
    @Enumerated(EnumType.STRING)
    private TypeOperation typeOperation;
    private LocalDate dateEcheance;
    @Enumerated(EnumType.STRING)
    private SousCategorie sousCategorie;
    private Double tauxTva;
    @Enumerated(EnumType.STRING)
    private TypeEvenement type;
    @Enumerated(EnumType.STRING)
    private StatutEvenement statut;
    private Boolean estRecurrent;
    private Integer jourRecurrence;
    @ManyToOne
    @JoinColumn(name = "id_entreprise")
    private Entreprise entreprise;
}
