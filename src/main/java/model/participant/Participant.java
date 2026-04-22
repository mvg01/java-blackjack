package model.participant;

import java.util.List;
import model.card.Card;

public interface Participant {

    int calculateTotalScore();

    void addCard(Card card);

    boolean canHit();

    String name();

    List<Card> cards();
}
