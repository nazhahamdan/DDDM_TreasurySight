package com.example.demo.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "comptes_bancaires")
public class CompteBancaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String libelle;
    private String banque;
    private String rib;
    private Double soldeInitial;
    private Boolean actif;

    @ManyToOne
    @JoinColumn(name = "id_entreprise")
    private Entreprise entreprise;
}