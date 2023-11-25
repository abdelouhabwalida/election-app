package ca.bell.electionapp.business.business;

import java.util.List;

public interface ElectionHandler {

    List<String> processElection();

    List<String> displayFraudList();
}
