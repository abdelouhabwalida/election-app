# election-app
election-app est une application  java développer en utilisant le framework Spring Boot. 
Cette application gère le traitement des votes et identifie les principaux candidats.

## Prérequis

- Java 8 ou version ultérieure
- Maven

## Installation

1. Clonez le dépôt :

   ```bash
   git clone https://github.com/abdelouhabwalida/election-app.git
   cd election-app
   mvn spring-boot:run
   
   L'application sera démarrée localement à l'adresse http://localhost:8080.

   La liste des trois premiers candidats est accessible via l'adresse web suivante http://localhost:8080/api/election/top-candidates
   La liste des messages de fraudes est accessible via l'adresse web suivante http://localhost:8080/api/election/fraud-list 

## Utilisation

Une fois l'application démarrée, elle effectuera automatiquement le traitement des votes et affichera les résultats sur la console. Les trois principaux candidats et les messages de fraude seront affichés.
Si vous souhaitez personnaliser le fichier de votes, assurez-vous de le placer dans le répertoire approprié et de configurer le chemin dans le fichier de configuration.

## Configuration
Le fichier texte est accessible via le chemin suivant src/main/resources/election.txt 
La configuration de l'application peut être modifiée dans le fichier application.properties. Assurez-vous de configurer le chemin du fichier de votes, par exemple :
vote.file.name=classpath:votes.txt


