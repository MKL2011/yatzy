import org.junit.Test;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class ScoreTest {

    @Test
    public void test_chance_score() {
        assertEquals(15, Score.chanceScore(IntStream.of(2, 3, 4, 5, 1)));
        assertEquals(16, Score.chanceScore(IntStream.of(3, 3, 4, 5, 1)));
        assertEquals(14, Score.chanceScore(IntStream.of(1, 1, 3, 3, 6)));
        assertEquals(21, Score.chanceScore(IntStream.of(4, 5, 5, 6, 1)));
    }

    @Test
    public void test_yatzy_score() {
        assertEquals(50, Score.yatzyScore(true));
        assertEquals(0, Score.yatzyScore(false));
    }

    @Test
    public void test_ones_score() {
        assertEquals(1, Score.onesScore(Stream.of(1)));
        assertEquals(3, Score.onesScore(Stream.of(1, 1, 1)));
        assertEquals(2, Score.onesScore(Stream.of(1, 1)));
        assertEquals(0, Score.onesScore(Stream.empty()));
    }

    @Test
    public void test_twos_score() {
        assertEquals(2, Score.twosScore(Stream.of(2)));
        assertEquals(6, Score.twosScore(Stream.of(2, 2, 2)));
        assertEquals(4, Score.twosScore(Stream.of(2, 2)));
        assertEquals(0, Score.twosScore(Stream.empty()));
    }

    @Test
    public void test_threes_score() {
        assertEquals(3, Score.threesScore(Stream.of(3)));
        assertEquals(9, Score.threesScore(Stream.of(3, 3, 3)));
        assertEquals(6, Score.threesScore(Stream.of(3, 3)));
        assertEquals(0, Score.threesScore(Stream.empty()));
    }

    @Test
    public void test_fours_score() {
        assertEquals(4, Score.foursScore(Stream.of(4)));
        assertEquals(12, Score.foursScore(Stream.of(4, 4, 4)));
        assertEquals(8, Score.foursScore(Stream.of(4, 4)));
        assertEquals(0, Score.foursScore(Stream.empty()));
    }

    @Test
    public void test_fives_score() {
        assertEquals(5, Score.fivesScore(Stream.of(5)));
        assertEquals(15, Score.fivesScore(Stream.of(5, 5, 5)));
        assertEquals(10, Score.fivesScore(Stream.of(5, 5)));
        assertEquals(0, Score.fivesScore(Stream.empty()));
    }

    @Test
    public void test_sixes_score() {
        assertEquals(6, Score.sixesScore(Stream.of(6)));
        assertEquals(18, Score.sixesScore(Stream.of(6, 6, 6)));
        assertEquals(12, Score.sixesScore(Stream.of(6, 6)));
        assertEquals(0, Score.sixesScore(Stream.empty()));
    }

    @Test
    public void test_pair_score() {
        assertEquals(Score.pairScore(1), 2);
        assertEquals(Score.pairScore(2), 4);
        assertEquals(Score.pairScore(3), 6);
        assertEquals(Score.pairScore(4), 8);
        assertEquals(Score.pairScore(5), 10);
        assertEquals(Score.pairScore(6), 12);
    }

    @Test
    public void test_double_pair_score() {
        assertEquals(Score.twoPairScore(Stream.of(3, 5)), 16);
        assertEquals(Score.twoPairScore(Stream.of(1, 6)), 14);
        assertEquals(Score.twoPairScore(Stream.empty()), 0);
    }

    @Test
    public void test_three_of_a_kind_score() {
        assertEquals(3, Score.threeOfAKindScore(1));
        assertEquals(6, Score.threeOfAKindScore(2));
        assertEquals(9, Score.threeOfAKindScore(3));
        assertEquals(12, Score.threeOfAKindScore(4));
        assertEquals(15, Score.threeOfAKindScore(5));
        assertEquals(18, Score.threeOfAKindScore(6));
    }

    @Test
    public void test_four_of_a_kind_score() {
        assertEquals(4, Score.fourOfAKindScore(1));
        assertEquals(8, Score.fourOfAKindScore(2));
        assertEquals(12, Score.fourOfAKindScore(3));
        assertEquals(16, Score.fourOfAKindScore(4));
        assertEquals(20, Score.fourOfAKindScore(5));
        assertEquals(24, Score.fourOfAKindScore(6));
    }

    @Test
    public void test_is_small_straight_score() {
        assertEquals(Score.smallStraightScore(true), 15);
        assertEquals(Score.smallStraightScore(false), 0);
    }

    @Test
    public void test_is_large_straight_score() {
        assertEquals(Score.largeStraightScore(true), 20);
        assertEquals(Score.largeStraightScore(false), 0);
    }

    @Test
    public void test_is_full_house_score() {
        assertEquals(Score.fullHouseScore(List.of(1, 1, 2, 2, 2)), 8);
        assertEquals(Score.fullHouseScore(List.of(6, 6, 6, 4, 4)), 26);
    }
}
