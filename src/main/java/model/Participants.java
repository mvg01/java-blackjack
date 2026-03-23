package model;

import java.util.ArrayList;
import java.util.List;

public class Participants {

    private List<Participant> participantsBettingResults;

    public Participants() {
        participantsBettingResults = new ArrayList<>();
    }

    public void addParticipant(Participant participant) {
        participantsBettingResults.add(participant);
    }

    public List<Participant> getParticipantsBettingResults() {
        return participantsBettingResults;
    }
}
