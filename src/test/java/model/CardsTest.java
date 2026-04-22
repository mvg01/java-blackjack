package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import model.card.Card;
import model.card.CardShape;
import model.card.CardValue;
import model.card.Cards;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class CardsTest {
    @ParameterizedTest
    @MethodSource("fixture.CardsTestFixture#카드_목록_별_점수_정보_제공")
    void 참가자의_카드가_주어지면_기본_점수를_계산할_수_있다(List<Card> randomCards, int expectedScore) {
        // given
        Cards cards = new Cards(randomCards);

        // when
        int score = cards.calculateScore();

        // then
        assertThat(score).isEqualTo(expectedScore);
    }

    @Test
    void 카드_목록에서_Ace_개수를_셀_수_있다() {
        // given
        List<Card> onlyAceCards = List.of(
                new Card(CardShape.DIAMOND, CardValue.ACE),
                new Card(CardShape.DIAMOND, CardValue.ACE));
        Cards cards = new Cards(onlyAceCards);

        // when
        int count = cards.countAce();

        // then
        assertThat(count).isEqualTo(2);
    }
}
