package com.example.demo.enums;

public enum SousCategorie {

    // Recettes
    FACTURE_CLIENT(Categorie.RECETTES, "Facture client"),
    ABONNEMENTS(Categorie.RECETTES, "Abonnements"),
    VENTE_MATERIEL(Categorie.RECETTES, "Vente matériel"),
    AUTRES_RECETTES(Categorie.RECETTES, "Autres recettes"),
    TVA_RECUPERABLE(Categorie.RECETTES, "TVA récupérable"),

    // Fournisseurs
    ACHATS_MARCHANDISES(Categorie.FOURNISSEURS, "Achats marchandises"),
    PRESTATAIRES(Categorie.FOURNISSEURS, "Prestataires"),
    LOYER(Categorie.FOURNISSEURS, "Loyer"),
    AUTRES_ACHATS(Categorie.FOURNISSEURS, "Autres achats"),

    // Frais généraux
    TELECOMS(Categorie.FRAIS_GENERAUX, "Télécoms"),
    FRAIS_ADMINISTRATIFS(Categorie.FRAIS_GENERAUX, "Frais administratifs"),
    EQUIPEMENTS(Categorie.FRAIS_GENERAUX, "Équipements"),
    AUTRES_FRAIS(Categorie.FRAIS_GENERAUX, "Autres frais"),

    // Employés
    SALAIRES(Categorie.EMPLOYES, "Salaires"),
    CNSS_AMO(Categorie.EMPLOYES, "CNSS / AMO"),
    IGR(Categorie.EMPLOYES, "IGR"),
    AVANTAGES(Categorie.EMPLOYES, "Avantages employés"),

    // Impôts
    TVA_DEDUCTIBLE(Categorie.IMPOTS, "TVA déductible"),
    TVA(Categorie.IMPOTS, "TVA"),
    IS(Categorie.IMPOTS, "IS"),
    TAXE_PROFESSIONNELLE(Categorie.IMPOTS, "Taxe professionnelle"),
    AUTRES_IMPOTS(Categorie.IMPOTS, "Autres impôts"),

    // Dettes financières
    REMBOURSEMENT_EMPRUNT(Categorie.DETTES_FINANCIERES, "Remboursement emprunt"),
    INTERETS_BANCAIRES(Categorie.DETTES_FINANCIERES, "Intérêts bancaires"),
    LEASING(Categorie.DETTES_FINANCIERES, "Leasing"),
    DECOUVERTS(Categorie.DETTES_FINANCIERES, "Découverts");

    private final Categorie categorie;
    private final String label;

    SousCategorie(Categorie categorie, String label) {
        this.categorie = categorie;
        this.label = label;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public String getLabel() {
        return label;
    }
}