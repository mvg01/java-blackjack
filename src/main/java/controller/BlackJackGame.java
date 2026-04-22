package controller;

import model.CardDispenser;
import model.betting.BettingCalculator;
import model.participant.Dealer;
import model.participant.Participants;
import model.participant.Player;
import model.participant.Players;
import view.InputView;

public class BlackJackGame {
    private final BlackJackRound round;
    private final BettingCalculator bettingCalculator;

    public BlackJackGame(Players players) {
        Dealer dealer = new Dealer();
        CardDispenser dispenser = new CardDispenser();
        bettingCalculator = new BettingCalculator();
        round = new BlackJackRound(dealer, players, dispenser);
    }

    public void prepare() {
        for (Player player : round.players().players()) {
            player.betMoney(InputView.readPlayerBettingMoney(player.name()));
        }
        round.initialDeal();
    }

    public void play() {
        round.playPlayers();
        round.playDealer();
    }

    public Participants finish() {
        return bettingCalculator.calculateBettingMoney(round.dealer(), round.players());
    }

    public Dealer dealer() {
        return round.dealer();
    }

    public Players players() {
        return round.players();
    }
}
