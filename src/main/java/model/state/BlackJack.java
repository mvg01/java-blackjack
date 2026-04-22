package model.state;

import model.card.Cards;

public class BlackJack extends Finished {
    private static final int BLACK_JACK_REQUIREMENT = 2;
    private static final int BLACK_JACK = 21;
    private final double BLACK_JACK_WIN_PRICE = 1.5;

    public BlackJack(Cards cards) {
        super(cards);
    }

    @Override
    public double earningRate() {
        return BLACK_JACK_WIN_PRICE;
    }
}
