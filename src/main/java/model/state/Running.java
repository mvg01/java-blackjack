package model.state;

import model.card.Cards;

public abstract class Running implements State {

    protected final Cards cards;

    public Running(Cards cards) {
        this.cards = cards;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public Cards cards() {
        return cards;
    }

    @Override
    public double profit(double money) {
        throw new IllegalStateException();
    }
}
