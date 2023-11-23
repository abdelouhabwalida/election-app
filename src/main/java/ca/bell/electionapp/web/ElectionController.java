package ca.bell.electionapp.web;

import ca.bell.electionapp.business.business.ElectionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/election")
public class ElectionController {
    @Autowired
    ElectionHandler electionHandler;

    @GetMapping("/top-candidates")
    public List<String> getTopCandidates() {
        return electionHandler.processVotes();
    }

    @GetMapping("/fraud-list")
    public List<String> getFraudList() {
        return electionHandler.displayFraudList();
    }

}
