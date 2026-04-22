package controller;

import java.util.List;
import model.CardDispenser;
import model.betting.BettingCalculator;
import model.participant.Dealer;
import model.participant.Participants;
import model.participant.Player;
import model.participant.Players;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    public void run() {
        Players players = createPlayers();
        BlackJackGame game = createGame(players);
        startGame(game);
        finishGame(game);
    }

    private Players createPlayers() {
        List<String> names = InputView.readPlayerNames();
        return Players.from(names);
    }

    private BlackJackGame createGame(Players players) {
        Dealer dealer = new Dealer();
        CardDispenser dispenser = new CardDispenser();
        BlackJackRound round = new BlackJackRound(dealer, players, dispenser);
        return new BlackJackGame(round, new BettingCalculator());
    }

    private void startGame(BlackJackGame game) {
        game.prepare();
        printInitialCards(game);
        game.play();
    }

    private void finishGame(BlackJackGame game) {
        printFinalCards(game);
        Participants participants = game.finish();
        printResult(participants);
    }

    private void printInitialCards(BlackJackGame game) {
        OutputView.printCardOpen(game.players());
        OutputView.printCardByDealer(game.dealer());
        game.players().players().forEach(OutputView::printCardByPlayer);
        OutputView.printBlank();
    }

    private void printFinalCards(BlackJackGame game) {
        OutputView.printBlank();
        printDealerScore(game.dealer());
        game.players().players().forEach(this::printPlayerScore);
    }

    private void printDealerScore(Dealer dealer) {
        OutputView.printCardByPlayerWithScore(dealer, dealer.calculateTotalScore());
    }

    private void printPlayerScore(Player player) {
        OutputView.printCardByPlayerWithScore(player, player.calculateTotalScore());
    }

    private void printResult(Participants result) {
        OutputView.printBettingResultHeader();
        OutputView.printBettingResult(result.getParticipantsBettingResults());
    }
}