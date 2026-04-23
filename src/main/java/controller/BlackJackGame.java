package controller;

import java.util.List;
import model.CardDispenser;
import model.betting.BettingCalculator;
import model.card.Card;
import model.participant.Dealer;
import model.participant.Participants;
import model.participant.Player;
import model.participant.Players;
import view.InputView;

public class BlackJackGame {
    private final BettingCalculator bettingCalculator;
    private final Players players;
    private final Dealer dealer;
    private final CardDispenser cardDispenser;

    public record ParticipantSnapshot(String name, List<Card> cards, int score) {}

    public BlackJackGame(Players players) {
        this.cardDispenser = new CardDispenser();
        this.bettingCalculator = new BettingCalculator();
        this.dealer = new Dealer();
        this.players = players;
    }

    public void prepare() {
        for (Player player : players.players()) {
            player.betMoney(InputView.readPlayerBettingMoney(player.name()));
        }
        initialDeal();
    }

    public void play() {
        playPlayers();
        playDealer();
    }

    private void initialDeal() {
        cardDispenser.dispenseStartingCards(dealer);
        players.receiveStartingCards(cardDispenser);
    }

    private void playPlayers() {
        players.play(cardDispenser);
    }

    private void playDealer() {
        while (dealer.canHit()) {
            cardDispenser.dispenseOneCard(dealer);
        }
    }

    public Participants finish() {
        return bettingCalculator.calculateBettingMoney(dealer, players);
    }

    public List<String> getPlayersNames() {
        return players.players().stream()
                .map(Player::name)
                .toList();
    }

    public Card getDealerFirstCard() {
        return dealer.cards().getFirst();
    }

    public String getDealerName() {
        return dealer.name();
    }

    public ParticipantSnapshot getDealerSnapshot() {
        return new ParticipantSnapshot(dealer.name(), dealer.cards(), dealer.calculateTotalScore());
    }

    public List<ParticipantSnapshot> getPlayerSnapshots() {
        return players.players().stream()
                .map(p -> new ParticipantSnapshot(p.name(), p.cards(), p.calculateTotalScore()))
                .toList();
    }
}
