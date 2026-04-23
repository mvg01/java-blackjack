package model.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private static final int ACE_BONUS_SCORE = 10;
    private static final int BUST_LIMIT = 21;
    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public static Cards createEmpty() {
        return new Cards(new ArrayList<>());
    }

    public void add(Card card) {
        cards.add(card);
    }

    public List<Card> cards() {
        return List.copyOf(cards);
    }

    public int size() {
        return cards.size();
    }

    public int calculateTotalScore() {
        int score = calculateScore();
        int aceCount = countAce();

        while (aceCount > 0 && score + ACE_BONUS_SCORE <= BUST_LIMIT) {
            score += ACE_BONUS_SCORE;
            aceCount--;
        }
        return score;
    }

    public int calculateScore() {
        return cards.stream()
                .mapToInt(Card::score)
                .sum();
    }

    public int countAce() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public Card getFirst() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("딜러의 카드가 없습니다.");
        }
        return cards.getFirst();
    }
}
