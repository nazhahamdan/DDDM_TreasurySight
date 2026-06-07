# Application de Gestion Financière Intelligente & Prédictive

Une solution d’entreprise moderne et centralisée conçue pour assurer la **gestion, le suivi et l’analyse prédictive de la santé financière**.

L’application combine une gestion rigoureuse de la trésorerie et des factures avec des capacités d’**intelligence artificielle (Deep Learning)** afin d’anticiper les risques financiers et d’accompagner la prise de décision stratégique.


# Fonctionnalités Principales

## Gestion Financière & Comptable

* **Suivi du bilan financier :** centralisation et visualisation des actifs, passifs et capitaux propres de l’entreprise.
* **Gestion de la trésorerie :** suivi en temps réel des flux financiers avec catégorisation des différentes entrées et sorties.
* **Analyse des clients et retards de paiement :** identification des clients à risque, suivi des comportements de paiement et impact sur la trésorerie.

## Intelligence Artificielle & Analyse Prédictive

* **Prédiction de faillite en se basant sur les 3 dernières années :**
  un modèle de Deep Learning analyse les données financières historiques (sur un horizon de 3 ans) ainsi que des indicateurs macro-économiques afin d’estimer la probabilité de défaillance de l’entreprise.

---

# Stack Technique

## Intelligence Artificielle (Data Science)

* **Python** : langage principal pour le traitement et l’entraînement des modèles.
* **TensorFlow / Keras** : frameworks de deep learning.
* **LSTM (Long Short-Term Memory)** : réseau de neurones récurrent adapté à l’analyse des séries temporelles financières.


## Backend (API & Architecture)

* **Spring Boot** : API REST, logique métier, sécurité et gestion des données.
* **Flask** : service Python dédié à l’intégration du modèle de machine learning.


## Frontend (Interface Utilisateur)

* **Angular** : application SPA moderne, réactive et typée (TypeScript).


## Conteneurisation & Orchestration

* **Docker & Docker Compose** : gestion des services et environnement multi-conteneurs.

---

# Guide d’exécution (Docker)

## 1. Lancer l’application

À la racine du projet (contenant `docker-compose.yml`) :

```bash
docker compose up --build
```

### ✔ Ce que cette commande exécute :

* Build du frontend Angular
* Build du backend Spring Boot
* Démarrage de MySQL
* Démarrage du service Flask
* Configuration du réseau Docker entre les services

---

## 2. Accès aux services

Une fois les conteneurs démarrés :

| Service             | URL                                            |
| ------------------- | ---------------------------------------------- |
| Frontend Angular    | [http://localhost:4200](http://localhost:4200) |
| Backend Spring Boot | [http://localhost:8080](http://localhost:8080) |
| Flask API           | [http://localhost:5000](http://localhost:5000) |
| MySQL (externe)     | localhost:3307                                 |

---

## 3. Première utilisation (création utilisateur)

1. Accéder à l’application :

   ```
   http://localhost:4200
   ```

2. Créer un **premier compte utilisateur** via l’interface.

Cette étape initialise le contexte métier de l’application.

---

## 4. Injection des données de test (dashboards)

Après la création du premier utilisateur, injecter les données nécessaires aux dashboards analytiques.

#### PowerShell (Windows)

```powershell
Get-Content init.sql | docker exec -i treasurysight-mysql mysql -u root -proot treasurySight_db
```

#### CMD

```cmd
docker exec -i treasurysight-mysql mysql -u root -proot treasurySight_db < init.sql
```


Ce script permet d’initialiser un jeu de données réaliste comprenant transactions financières, événements futurs et  données nécessaires aux analyses et dashboards (DDDM)

* Cette étape doit être exécutée **une seule fois après la création du premier utilisateur**
* Elle permet de simuler un environnement réaliste pour l’analyse des données

---

## Récap: Architecture du flux d’exécution

```
Démarrage des services (docker compose up --build)
        ↓
Création du premier utilisateur
        ↓
Injection manuelle des données (init.sql)
        ↓
Visualisation des dashboards analytiques
```

---


## Limitation fonctionnelle (version initiale)

Dans cette première version de l’application, et en raison des contraintes de temps de développement, le système est conçu pour fonctionner **uniquement avec la première entreprise enregistrée** dans la base de données.

Concrètement :

* Les **tableaux de bord (dashboards)**
* Les **analyses clients et comportements de paiement**
* Les **indicateurs financiers globaux**

sont tous affichés et calculés **uniquement à partir de la première entreprise créée**.

Les autres entreprises peuvent être créées et sont bien persistées en base de données, mais **elles ne sont pas encore prises en charge par le frontend**, qui ne gère pas la sélection ou le changement dynamique d’entreprise.

---
