package model.state;

import model.card.Card;
import model.card.Cards;

public abstract class Finished implements State {

    protected final Cards cards;

    public Finished(Cards cards) {
        this.cards = cards;
    }

    @Override
    public State draw(Card card) {
        throw new IllegalStateException("끝난 상태이므로 카드를 뽑을 수 없는 상태이다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public Cards cards() {
        return cards;
    }
}
