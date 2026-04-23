package controller;

import java.util.List;
import model.participant.Participants;
import model.participant.Players;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    public void run() {
        Players players = createPlayers();
        BlackJackGame game = new BlackJackGame(players);
        startGame(game);
        finishGame(game);
    }

    private Players createPlayers() {
        List<String> names = InputView.readPlayerNames();
        return Players.from(names);
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
        OutputView.printCardOpen(game.getPlayersNames());
        OutputView.printCardByDealer(game.getDealerFirstCard(), game.getDealerName());
        game.getPlayerSnapshots()
                .forEach(s -> OutputView.printCardByPlayer(s.name(), s.cards()));
        OutputView.printBlank();
    }

    private void printFinalCards(BlackJackGame game) {
        OutputView.printBlank();
        BlackJackGame.ParticipantSnapshot dealer = game.getDealerSnapshot();
        OutputView.printCardByPlayerWithScore(dealer.name(), dealer.cards(), dealer.score());
        game.getPlayerSnapshots()
                .forEach(s -> OutputView.printCardByPlayerWithScore(s.name(), s.cards(), s.score()));
    }

    private void printResult(Participants result) {
        OutputView.printBettingResultHeader();
        OutputView.printBettingResult(result.getParticipantsBettingResults());
    }
}