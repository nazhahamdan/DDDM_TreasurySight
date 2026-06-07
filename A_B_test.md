# A/B Test Plan — TreasurySight vs Gestion Financière Classique

**Contexte :** Les PME marocaines, faute d'ERP accessible financièrement, gèrent leur santé financière de manière dispersée et réactive. TreasurySight propose une alternative centralisée, analytique et prédictive.

---

## Formulation des hypothèses

**H₀ — Approche classique**
La gestion financière est assurée sans outil centralisé : transactions saisies dans des fichiers Excel, factures stockées en PDF, relances clients par email, aucune catégorisation systématique des flux, aucune visibilité sur le solde prévisionnel. La détection des risques (clients en retard, tension de trésorerie, risque de défaillance ou même de faillite) est réactive — constatée après les faits — et repose sur l'intuition du dirigeant ou du comptable.

**H₁ — TreasurySight (traitement)**
Centralisation complète de la donnée financière avec :
- Dashboard de trésorerie : historique 6 mois + prévision 3 mois basée sur les événements planifiés
- Catégorisation structurée des flux (6 catégories, 27 sous-catégories)
- Analyse clients : scoring de retard, KPIs de risque, visualisations ECharts (scatter CA vs délai, Pareto, histogramme)
- Prédiction de faillite intégrant les indicateurs macro-économiques en temps réel

---

## Population cible

Dirigeants ou responsables administratifs de PME (5–200 employés) sans ERP en place, gérant actuellement leur trésorerie sur Excel ou des outils non intégrés.
Répartition : **50% Groupe H₀ / 50% Groupe H₁**, sur une durée de **4 semaines**.

---

## Métriques de succès

| Dimension | H₀ — Classique | H₁ — TreasurySight | Cible |
|---|---|---|---|
| Temps pour produire un état de trésorerie mensuel | Plusieurs heures (consolidation manuelle) | Consultation directe du dashboard | − 70% |
| Délai de détection d'un client à risque | Tardif, souvent > 45j après le premier retard | Alerté dès le dépassement du seuil configuré | − 50% sur le délai moyen |
| Visibilité sur le solde à 3 mois | Inexistante ou estimée à la main | Graphe prévisionnel basé sur événements planifiés | Indicateur nouveau |
| Identification des catégories les plus impactantes | Impossible sans retraitement manuel des données | Visible directement via le tableau catégorisé | Indicateur nouveau |
| Anticipation d'une défaillance financière | Réactive — constatée trop tard | Prédictive — probabilité LSTM avec facteurs macro | Indicateur nouveau |
| Erreurs de catégorisation des flux | Fréquentes (saisie libre, non structurée) | Réduites par la taxonomie imposée (sous-catégories) | − 60% |
| Temps de préparation d'un reporting client | Manuel, non standardisé | Généré automatiquement via le dashboard analyse clients | − 60% |

---

## Critère de succès principal

> Réduction ≥ 50% du temps moyen nécessaire pour obtenir une **vision complète et actionnable de la santé financière de l'entreprise** — trésorerie, risque client et risque de défaillance — par rapport au groupe H₀ utilisant l'approche classique.

---

## Protocole d'exécution

**Semaine 1 :** Onboarding des deux groupes. Le groupe H₁ saisit ses données initiales dans TreasurySight. Le groupe H₀ continue avec ses outils habituels. Aucune mesure de performance collectée — phase d'adaptation.

**Semaines 2–4 :** Collecte des données comportementales (temps de tâche, erreurs, fréquence de consultation) et envoi d'un questionnaire hebdomadaire de 5 questions (échelle Likert) mesurant la confiance perçue dans les décisions financières.

**Tâches standardisées soumises aux deux groupes :**
1. *"Quel est votre solde prévisionnel dans 2 mois ?"*
2. *"Citez vos 3 clients présentant le risque de retard le plus élevé."*
3. *"Quelle catégorie de dépenses a le plus impacté votre trésorerie ce trimestre ?"*
4. *"Estimez la probabilité que votre entreprise rencontre une difficulté de trésorerie dans les 3 prochains mois."*

Le temps de réponse et la précision des réponses sont mesurés pour chaque tâche dans les deux groupes.

**Critère d'arrêt anticipé :** Si le groupe H₁ rapporte un taux d'abandon de l'outil > 30% avant la fin de la semaine 2 (friction trop élevée à l'onboarding), le protocole est suspendu pour révision de l'expérience d'entrée.

---

## Risques & limites

- **Biais de nouveauté :** Le groupe H₁ peut sur-performer en semaine 1 par effet de curiosité. Mitigation : n'analyser les métriques de performance qu'à partir de la semaine 2.
- **Hétérogénéité des PME :** Le volume et la complexité des données financières varient fortement d'une PME à l'autre. Mitigation : stratifier l'échantillon par taille (< 20 employés / 20–200 employés).
- **Courbe d'apprentissage H₁ :** L'adoption d'un nouvel outil introduit une friction initiale qui peut biaiser les métriques de temps. Mitigation : phase d'onboarding dédiée en semaine 1, exclue de la mesure.