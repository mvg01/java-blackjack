package model.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record Card(CardShape shape, CardValue value) {

    private static final List<Card> CARDS;

    static {
        CARDS = Arrays.stream(CardShape.values())
                .flatMap(Card::combinate)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private static Stream<Card> combinate(CardShape shape) {
        return Arrays.stream(CardValue.values())
                .map(value -> new Card(shape, value));
    }

    public static List<Card> cards() {
        return Collections.unmodifiableList(CARDS);
    }

    public boolean isAce() {
        return value == CardValue.ACE;
    }

    public int score() {
        return value.score();
    }
}
