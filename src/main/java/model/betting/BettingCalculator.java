package model.betting;

import java.util.List;
import model.participant.Dealer;
import model.participant.Participants;
import model.participant.Player;
import model.participant.Players;

public class BettingCalculator {
    Participants participants;

    public BettingCalculator() {
        participants = new Participants();
    }

    public Participants calculateBettingMoney(Dealer dealer, Players players) {
        participants.addParticipant(dealer);
        List<Player> list = players.players();
        for (Player player : list) {
            resolveBettingResult(player, dealer);
            participants.addParticipant(player);
        }
        return participants;
    }

    private void resolveBettingResult(Player player, Dealer dealer) {
        long playerProfit = (long) player.stateProfit(dealer.state(), player.bettingMoney());
        player.addProfit(playerProfit);
        dealer.addProfit(-playerProfit);
    }
}
