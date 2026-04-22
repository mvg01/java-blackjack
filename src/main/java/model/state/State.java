package model.state;

import model.card.Card;
import model.card.Cards;

public interface State {
    State draw(Card card);

    State stay();

    boolean isFinished();

    Cards cards();

    double profit(double money);
}
