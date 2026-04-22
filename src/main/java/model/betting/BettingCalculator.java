package model.betting;

import java.util.List;
import model.participant.Dealer;
import model.participant.Participants;
import model.participant.Player;
import model.participant.Players;

public class BettingCalculator {
    private final double BLACK_JACK_WIN_PRICE = 0.5;
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
        long money = player.bettingMoney();
        if (player.isBust()) {
            settleAccountsDealerWin(dealer, player, money);
            return;
        }
        if (player.isBlackJack() && !dealer.isBlackJack()) {
            settleAccountsPlayerWin(dealer, player, (long) (money * BLACK_JACK_WIN_PRICE));
            return;
        }
        if (player.isBlackJack() && dealer.isBlackJack()) {
            return;
        }
        if (dealer.isBlackJack()) {
            settleAccountsDealerWin(dealer, player, money);
            return;
        }
        if (dealer.isBust()) {
            settleAccountsPlayerWin(dealer, player, money);
            return;
        }
        if (player.calculateTotalScore() > dealer.calculateTotalScore()) {
            settleAccountsPlayerWin(dealer, player, money);
        }
        if (dealer.calculateTotalScore() > player.calculateTotalScore()) {
            settleAccountsDealerWin(dealer, player, money);
        }
    }

    private void settleAccountsPlayerWin(Dealer dealer, Player player, long money) {
        player.addProfit(money);
        dealer.subtractProfit(money);
    }

    private void settleAccountsDealerWin(Dealer dealer, Player player, long money) {
        dealer.addProfit(money);
        player.subtractProfit(money);
    }
}
