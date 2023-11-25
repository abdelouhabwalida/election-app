package ca.bell.electionapp;

import ca.bell.electionapp.business.business.ElectionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ElectionAppApplication implements CommandLineRunner {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    ElectionHandler electionHandler;

    public static void main(String[] args) {
        SpringApplication.run(ElectionAppApplication.class, args);
    }

    @Override
    public void run(String... args)  {

        List<String> topCandidates;
        List<String> fraudMessage;
        try {

            topCandidates = electionHandler.processElection();
            fraudMessage = electionHandler.displayFraudList();

            System.out.println(topCandidates);
            System.out.println(fraudMessage);
        } catch (Exception e) {
            logger.error("Error running the application: " , e);
        }
    }
}

