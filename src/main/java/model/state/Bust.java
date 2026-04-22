package model.state;

import model.card.Cards;

public class Bust extends Finished {
    public Bust(Cards cards) {
        super(cards);
    }

    @Override
    public double profit(State dealerState, long money) {
        return -money;
    }
}
