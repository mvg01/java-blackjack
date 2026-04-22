package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.card.Card;
import model.participant.Participant;

public class CardDispenser {

    private static final int START_CARD_NUMBER = 2;
    private final List<Card> cards;

    public CardDispenser() {
        cards = new ArrayList<>(Card.cards());
        Collections.shuffle(cards);
    }

    public void dispenseOneCard(Participant player) {
        player.addCard(pickCard());
    }

    public void dispenseStartingCards(Participant player) {
        for (int i = 0; i < START_CARD_NUMBER; i++) {
            player.addCard(pickCard());
        }
    }

    private Card pickCard() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("카드가 존재하지 않습니다.");
        }
        return cards.removeLast();
    }
}
