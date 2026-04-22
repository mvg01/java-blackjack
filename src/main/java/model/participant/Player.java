package model.participant;

import model.betting.BettingMoney;

public class Player extends AbstractParticipant {

    private BettingMoney bettingMoney;

    public Player(String name) {
        super(name);
    }

    public void betMoney(BettingMoney bettingMoney) {
        this.bettingMoney = bettingMoney;
    }

    public long bettingMoney() {
        return this.bettingMoney.bettingMoney();
    }
}
