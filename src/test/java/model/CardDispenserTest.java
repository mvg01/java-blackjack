package model;

import static org.junit.jupiter.api.Assertions.assertThrows;

import model.participant.Player;
import org.junit.jupiter.api.Test;

public class CardDispenserTest {

    @Test
    void 카드디스펜서는_52장의_블랙잭_덱을_만들_수_있다() {
        CardDispenser dispenser = new CardDispenser();
        for (int i = 0; i < 52; i++) {
            dispenser.dispenseOneCard(new Player("player" + i));
        }

        assertThrows(IllegalArgumentException.class, () -> dispenser.dispenseOneCard(new Player("pobi")));
    }

}
