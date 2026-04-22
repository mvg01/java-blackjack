package model.participant;

import java.util.List;
import model.card.Card;
import model.card.Cards;
import model.state.BlackJack;
import model.state.Bust;
import model.state.Hit;
import model.state.State;

public abstract class AbstractParticipant implements Participant {
    private final String name;
    private State state;
    private long profit;

    public AbstractParticipant(String name) {
        this.name = name;
        state = new Hit(Cards.createEmpty());
        this.profit = 0;
    }

    @Override
    public void addCard(Card card) {
        state = state.draw(card);
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public List<Card> cards() {
        Cards cards = state.cards();
        return cards.cards();
    }

    public void addProfit(long money) {
        this.profit += money;
    }

    public void subtractProfit(long money) {
        this.profit -= money;
    }

    public boolean canHit() {
        return !state.isFinished();
    }

    public int calculateTotalScore() {
        return state.cards().calculateTotalScore();
    }

    public long profit() {
        return this.profit;
    }

    public boolean isBlackJack() {
        return state instanceof BlackJack;
    }

    public boolean isBust() {
        return state instanceof Bust;
    }
}
