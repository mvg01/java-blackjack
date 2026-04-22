package model.state;

import model.card.Card;
import model.card.Cards;

public class Hit extends Running {
    private static final int BLACK_JACK_SCORE = 21;
    private static final int BLACK_JACK_REQUIREMENT = 2;

    public Hit(Cards cards) {
        super(cards);
    }

    @Override
    public State draw(Card card) {
        cards.add(card);
        if (isBust()) {
            return new Bust(cards);
        }
        if (isBlackJack()) {
            return new BlackJack(cards);
        }
        if (cards.calculateTotalScore() == BLACK_JACK_SCORE) {
            return new Stay(cards);
        }
        return new Hit(cards);
    }

    private boolean isBust() {
        return cards.calculateTotalScore() > BLACK_JACK_SCORE;
    }

    private boolean isBlackJack() {
        return cards.calculateTotalScore() == BLACK_JACK_SCORE && cards.size() == BLACK_JACK_REQUIREMENT;
    }
}
