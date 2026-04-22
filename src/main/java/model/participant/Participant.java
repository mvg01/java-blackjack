package model.participant;

import java.util.List;
import model.card.Card;

public interface Participant {

    void addCard(Card card);

    String name();

    List<Card> cards();
}
