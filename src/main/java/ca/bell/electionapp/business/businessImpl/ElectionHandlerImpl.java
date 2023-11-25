package ca.bell.electionapp.business.businessImpl;

import ca.bell.electionapp.business.business.ElectionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implémentation de l'interface ElectionHandlerMetier
 * Cette classe traite les votes, détecte les fraudes et identifie les principaux candidats
 */

@Service
public class ElectionHandlerImpl implements ElectionHandler {

    Logger logger = LoggerFactory.getLogger(getClass());
    List<String> topCandidates = new ArrayList<>();
    List<String> fraudList = new ArrayList<>();
    @Value("${election.file.name}")
    private String fileName;

    /**
     * Traitement des votes à partir d'un fichier, mettant à jour les comptes de votes, les comptes d'électeurs,
     * et identifiant les principaux candidats
     * Les résultats sont stockés dans la liste des principaux candidats
     *
     * @return Une liste de chaînes représentant les principaux candidats avec leur nombre de votes
     */
    @Override
    public List<String> processElection() {

        try {
            Map<String, Integer> voteCounts = new HashMap<>();
            Map<String, Integer> voterCounts = new HashMap<>();

            processVotesFromFile(voteCounts, voterCounts, fraudList);

            topCandidates = getTopCandidates(voteCounts);


        } catch (IOException e) {
            logger.error("Erreur lors du traitement des votes ", e);

        }
        return topCandidates;
    }

    /**
     * Traitement des votes à partir d'un fichier, mettant à jour les comptes de votes, les comptes d'électeurs et la liste des fraudes
     *
     * @param voteCounts   Map contenant les comptes de votes par candidat
     * @param voterCounts  Map contenant les comptes de votes par électeur
     * @param fraudList    Liste des fraudes détectées
     * @throws IOException Si une erreur d'entrée/sortie survient lors de la lecture du fichier
     */
    private void processVotesFromFile(Map<String, Integer> voteCounts, Map<String, Integer> voterCounts, List<String> fraudList)
            throws IOException {
        Resource resource = new ClassPathResource(fileName);
        String filePath = resource.getFile().getAbsolutePath();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                processVoteLine(line, voteCounts, voterCounts, fraudList);
            }
        }
    }

    /**
     * Traitement d'une ligne de vote à partir d'une chaîne donnée
     *
     * @param line        La ligne de vote à traiter
     * @param voteCounts  Map contenant les comptes de votes par candidat
     * @param voterCounts Map contenant les comptes de votes par électeur
     * @param fraudList   Liste des fraudes détectées
     */
    private void processVoteLine(String line, Map<String, Integer> voteCounts, Map<String, Integer> voterCounts, List<String> fraudList) {
        String[] voteData = line.split(",");
        String voteId = voteData[0].trim();
        String candidateId = voteData[1].trim();

        if (voterCounts.containsKey(voteId)) {
            String fraudMessage = "Fraude détectée : Électeur " + voteId + " a voté plusieurs fois";
            fraudList.add(fraudMessage);
            return;
        }

        voteCounts.merge(candidateId, 1, Integer::sum);
        voterCounts.put(voteId, 1);
    }

    /**
     * Récupèration d'une liste des trois premiers candidats en fonction du nombre de votes
     *
     * @param voteCounts Map contenant les comptes de votes par candidat
     * @return Une liste de chaînes représentant les trois principaux candidats avec leur nombre de votes
     */
    private List<String> getTopCandidates(Map<String, Integer> voteCounts) {

        return voteCounts.entrySet()
                .stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .limit(3)
                .map(entry -> "Candidate: " + entry.getKey() + ", Votes: " + entry.getValue())
                .collect(Collectors.toList());
    }

    /**
     * Récupère une copie de la liste des fraudes détectées.
     * @return Une nouvelle liste contenant les fraudes détectées.
     */
    @Override
    public List<String> displayFraudList() {
        return new ArrayList<>(fraudList);
    }
}
