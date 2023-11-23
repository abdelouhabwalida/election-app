package ca.bell.electionapp.business.business;

import java.util.List;

public interface ElectionHandler {

    List<String> processVotes();

    List<String> displayFraudList();
}
