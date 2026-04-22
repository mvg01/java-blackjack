package controller;

import model.CardDispenser;
import model.participant.Dealer;
import model.participant.Players;

public class BlackJackRound {
    private final Dealer dealer;
    private final Players players;
    private final CardDispenser cardDispenser;

    public BlackJackRound(Dealer dealer, Players players, CardDispenser cardDispenser) {
        this.dealer = dealer;
        this.players = players;
        this.cardDispenser = cardDispenser;
    }

    public void initialDeal() {
        cardDispenser.dispenseStartingCards(dealer);
        players.receiveStartingCards(cardDispenser);
    }

    public void playPlayers() {
        players.play(cardDispenser);
    }

    public void playDealer() {
        while (dealer.canHit()) {
            cardDispenser.dispenseOneCard(dealer);
        }
    }

    public Dealer dealer() {
        return dealer;
    }

    public Players players() {
        return players;
    }
}

