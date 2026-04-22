package model.state;

import model.card.Cards;

public class Stay extends Finished {
    public Stay(Cards cards) {
        super(cards);
    }

    @Override
    public double profit(State dealerState, long money) {
        int myScore = cards.calculateTotalScore();
        int dealerScore = dealerState.cards().calculateTotalScore();
        if (dealerState instanceof BlackJack) {
            return -money;
        }
        if (dealerState instanceof Bust || dealerScore < myScore) {
            return money;
        }
        if (myScore < dealerScore) {
            return -money;
        }
        return 0;
    }
}
