package model.state;

import model.card.Card;
import model.card.Cards;

public interface State {
    State draw(Card card);

    boolean isFinished();

    Cards cards();

    double profit(State dealerState, long money);
}
