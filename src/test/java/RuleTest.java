import org.junit.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class RuleTest {

    @Test
    public void test_chance_rule() {
        assertTrue(Rule.chanceDices(List.of(2, 3, 4, 5, 1)).allMatch(result -> IntStream.of(2, 3, 4, 5, 1).filter(expected -> expected == result).findAny().isPresent()));
        assertTrue(Rule.chanceDices(List.of(3, 3, 4, 5, 1)).allMatch(result -> IntStream.of(3, 3, 4, 5, 1).filter(expected -> expected == result).findAny().isPresent()));
        assertTrue(Rule.chanceDices(List.of(1, 1, 3, 3, 6)).allMatch(result -> IntStream.of(1, 1, 3, 3, 6).filter(expected -> expected == result).findAny().isPresent()));
        assertTrue(Rule.chanceDices(List.of(4, 5, 5, 6, 1)).allMatch(result -> IntStream.of(4, 5, 5, 6, 1).filter(expected -> expected == result).findAny().isPresent()));
    }

    @Test
    public void test_yatzy_rule() {
        assertTrue(Rule.isYatzy(List.of(4, 4, 4, 4, 4)));
        assertTrue(Rule.isYatzy(List.of(6, 6, 6, 6, 6)));
        assertFalse(Rule.isYatzy(List.of(6, 6, 6, 6, 3)));
        assertTrue(Rule.isYatzy(List.of(1, 1, 1, 1, 1)));
        assertFalse(Rule.isYatzy(List.of(1, 1, 1, 2, 1)));
    }

    @Test
    public void test_ones_rule() {
        assertTrue(Rule.ones(List.of(1, 2, 3, 4, 5)).allMatch(result -> IntStream.of(1).filter(expected -> expected == result).findAny().isPresent()));
        assertTrue(Rule.ones(List.of(1, 2, 1, 4, 5)).allMatch(result -> IntStream.of(1, 1).filter(expected -> expected == result).findAny().isPresent()));
        assertEquals(0, Rule.ones(List.of(6, 2, 2, 4, 5)).count());
        assertTrue(Rule.ones(List.of(1, 2, 1, 1, 1)).allMatch(result -> IntStream.of(1, 1, 1, 1).filter(expected -> expected == result).findAny().isPresent()));
    }

    @Test
    public void test_twos_rule() {
        assertTrue(Rule.twos(List.of(1, 2, 3, 2, 6)).allMatch(result -> IntStream.of(2).filter(expected -> expected == result).findAny().isPresent()));
        assertTrue(Rule.twos(List.of(2, 3, 2, 5, 1)).allMatch(result -> IntStream.of(2, 2).filter(expected -> expected == result).findAny().isPresent()));
        assertTrue(Rule.twos(List.of(2, 2, 2, 2, 2)).allMatch(result -> IntStream.of(2, 2, 2, 2, 2).filter(expected -> expected == result).findAny().isPresent()));
        assertEquals(0, Rule.twos(List.of(6, 1, 3, 4, 5)).count());

    }

    @Test
    public void test_threes_rule() {
        assertTrue(Rule.threes(List.of(1, 2, 3, 2, 3)).allMatch(result -> IntStream.of(3, 3).filter(expected -> expected == result).findAny().isPresent()));
        assertTrue(Rule.threes(List.of(2, 3, 3, 3, 3)).allMatch(result -> IntStream.of(3, 3, 3, 3).filter(expected -> expected == result).findAny().isPresent()));
        assertEquals(0, Rule.threes(List.of(6, 1, 2, 4, 5)).count());
    }

    @Test
    public void test_fours_rule() {
        assertTrue(Rule.fours(List.of(1, 1, 2, 4, 4)).allMatch(result -> IntStream.of(4, 4).filter(expected -> expected == result).findAny().isPresent()));
        assertTrue(Rule.fours(List.of(4, 4, 4, 5, 5)).allMatch(result -> IntStream.of(4, 4, 4).filter(expected -> expected == result).findAny().isPresent()));
        assertTrue(Rule.fours(List.of(4, 4, 5, 5, 5)).allMatch(result -> IntStream.of(4, 4).filter(expected -> expected == result).findAny().isPresent()));
        assertTrue(Rule.fours(List.of(4, 5, 5, 5, 5)).allMatch(result -> IntStream.of(4).filter(expected -> expected == result).findAny().isPresent()));
        assertEquals(0, Rule.fours(List.of(6, 1, 2, 3, 5)).count());
    }

    @Test
    public void test_fives_rule() {
        assertTrue(Rule.fives(List.of(4, 4, 4, 5, 5)).allMatch(result -> IntStream.of(5, 5).filter(expected -> expected == result).findAny().isPresent()));
        assertTrue(Rule.fives(List.of(4, 4, 5, 5, 5)).allMatch(result -> IntStream.of(5, 5, 5).filter(expected -> expected == result).findAny().isPresent()));
        assertTrue(Rule.fives(List.of(4, 5, 5, 5, 5)).allMatch(result -> IntStream.of(5, 5, 5, 5).filter(expected -> expected == result).findAny().isPresent()));
        assertEquals(0, Rule.fives(List.of(6, 1, 2, 3, 4)).count());
    }

    @Test
    public void test_sixes_rule() {
        assertTrue(Rule.sixes(List.of(4, 4, 6, 5, 5)).allMatch(result -> IntStream.of(6).filter(expected -> expected == result).findAny().isPresent()));
        assertTrue(Rule.sixes(List.of(6, 5, 6, 6, 5)).allMatch(result -> IntStream.of(6, 6, 6).filter(expected -> expected == result).findAny().isPresent()));
        assertEquals(0, Rule.sixes(List.of(5, 1, 2, 3, 4)).count());
    }

    @Test
    public void test_pair_rule() {
        assertEquals(Rule.pair(List.of(3, 4, 3, 5, 6)), 3);
        assertEquals(Rule.pair(List.of(5, 3, 3, 3, 5)), 5);
        assertEquals(Rule.pair(List.of(5, 3, 6, 6, 5)), 6);
        assertEquals(Rule.pair(List.of(3, 3, 3, 3, 1)), 3);
        assertEquals(Rule.pair(List.of(3, 1, 2, 4, 5)), 0);

    }

    @Test
    public void test_double_pair_rule() {
        assertTrue(Rule.twoPair(List.of(3, 3, 5, 4, 5)).allMatch(result -> List.of(3, 5).contains(result)));
        assertTrue(Rule.twoPair(List.of(1, 1, 2, 3, 3)).allMatch(result -> List.of(1, 3).contains(result)));
        assertEquals(0, Rule.twoPair(List.of(1, 1, 2, 3, 4)).count());
    }

    @Test
    public void test_three_of_a_kind_rule() {
        assertEquals(3, Rule.threeOfAKind(List.of(3, 3, 3, 4, 5)).intValue());
        assertEquals(5, Rule.threeOfAKind(List.of(5, 3, 5, 4, 5)).intValue());
        assertEquals(3, Rule.threeOfAKind(List.of(3, 3, 3, 3, 5)).intValue());
        assertEquals(0, Rule.threeOfAKind(List.of(3, 3, 4, 5, 6)).intValue());
    }

    @Test
    public void test_four_of_a_kind_rule() {
        assertEquals(3, Rule.fourOfAKind(List.of(3, 3, 3, 3, 3)).intValue());
        assertEquals(5, Rule.fourOfAKind(List.of(5, 3, 5, 5, 5)).intValue());
        assertEquals(3, Rule.fourOfAKind(List.of(3, 3, 3, 3, 5)).intValue());
        assertEquals(0, Rule.fourOfAKind(List.of(3, 3, 4, 5, 6)).intValue());
    }

    @Test
    public void test_is_small_straight_rule() {
        assertFalse(Rule.isSmallStraight(List.of(4, 4, 4, 4, 4)));
        assertTrue(Rule.isSmallStraight(List.of(4, 5, 2, 3, 1)));
        assertTrue(Rule.isSmallStraight(List.of(1, 2, 3, 4, 5)));
    }

    @Test
    public void test_is_large_straight_rule() {
        assertFalse(Rule.isLargeStraight(List.of(4, 4, 4, 4, 4)));
        assertTrue(Rule.isLargeStraight(List.of(4, 5, 2, 3, 6)));
        assertTrue(Rule.isLargeStraight(List.of(6, 2, 3, 4, 5)));
    }

    @Test
    public void test_is_full_house_rule() {
        assertFalse(Rule.isFullHouse(List.of(4, 4, 4, 4, 4)));
        assertFalse(Rule.isFullHouse(List.of(4, 5, 2, 3, 6)));
        assertTrue(Rule.isFullHouse(List.of(6, 6, 6, 4, 4)));
    }
}
