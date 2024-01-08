package ca.bell.electionapp;

import ca.bell.electionapp.business.business.ElectionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

/**
  
 * cette version et la version 2 -**La classe principale de l'application Election App
 * Cette classe est annotée avec {@link SpringBootApplication} pour activer les fonctionnalités de Spring
 * Elle implémente également {@link CommandLineRunner} pour exécuter du code après le chargement du contexte de l'application  ttt
 */
@SpringBootApplication
public class ElectionAppApplication implements CommandLineRunner {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    ElectionHandler electionHandler;

    /**
     * La méthode principale pour démarrer l'application Spring Boot
     *
     * @param args Les arguments de la ligne de commande
     */
    public static void main(String[] args) {
        SpringApplication.run(ElectionAppApplication.class, args);
    }

    /**
     * Cette méthode est exécutée après le chargement du contexte de l'application
     * Elle traite l'élection, affiche les principaux candidats et gère les exceptions potentielles
     *
     * @param args Les arguments de la ligne de commande
     */
    @Override
    public void run(String... args) {

        List<String> topCandidates;
        List<String> fraudMessage;
        try {

            topCandidates = electionHandler.processElection();
            fraudMessage = electionHandler.displayFraudList();

            System.out.println(topCandidates);
            if (!fraudMessage.isEmpty())
                System.out.println(fraudMessage);
        } catch (Exception e) {
            logger.error("Error running the application: ", e);
        }
    }
}

