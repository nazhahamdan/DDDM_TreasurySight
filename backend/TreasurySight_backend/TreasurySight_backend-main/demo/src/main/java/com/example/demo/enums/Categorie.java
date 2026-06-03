package com.example.demo.enums;

public enum Categorie {
    RECETTES("Recettes"),
    FOURNISSEURS("Fournisseurs"),
    FRAIS_GENERAUX("Frais généraux"),
    EMPLOYES("Employés"),
    IMPOTS("Impôts"),
    DETTES_FINANCIERES("Dettes financières");

    private final String label;

    Categorie(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
