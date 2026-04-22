package model.state;

import model.card.Cards;

public class BlackJack extends Finished {
    private final double BLACK_JACK_WIN_PRICE = 0.5;

    public BlackJack(Cards cards) {
        super(cards);
    }

    @Override
    public double profit(State dealerState, long money) {
        if (dealerState instanceof BlackJack) {
            return 0;
        }
        return (long) (money * BLACK_JACK_WIN_PRICE);
    }
}
