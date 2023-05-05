import org.junit.*;
import static org.junit.Assert.*;

public class YatzyTest {

    @Test
    public void test_chance_scores_sum_of_all_dice() {
        assertEquals(15, Yatzy.chance(2, 3, 4, 5, 1));
        assertEquals(16, Yatzy.chance(3, 3, 4, 5, 1));
        assertEquals(14, Yatzy.chance(1, 1, 3, 3, 6));
        assertEquals(21, Yatzy.chance(4, 5, 5, 6, 1));
    }

    @Test
    public void test_yatzy_scores_50() {
        assertEquals(50, Yatzy.yatzy(4, 4, 4, 4, 4));
        assertEquals(50, Yatzy.yatzy(6, 6, 6, 6, 6));
        assertEquals(0, Yatzy.yatzy(6, 6, 6, 6, 3));
        assertEquals(50, Yatzy.yatzy(1, 1, 1, 1, 1));
        assertEquals(0, Yatzy.yatzy(1, 1, 1, 2, 1));
    }

    @Test
    public void test_ones() {
        assertEquals(1, Yatzy.ones(1, 2, 3, 4, 5));
        assertEquals(2, Yatzy.ones(1, 2, 1, 4, 5));
        assertEquals(0, Yatzy.ones(6, 2, 2, 4, 5));
        assertEquals(4, Yatzy.ones(1, 2, 1, 1, 1));
        assertEquals(0, Yatzy.ones(3, 3, 3, 4, 5));
    }

    @Test
    public void test_twos() {
        assertEquals(4, Yatzy.twos(1, 2, 3, 2, 6));
        assertEquals(4, Yatzy.twos(2, 3, 2, 5, 1));
        assertEquals(10, Yatzy.twos(2, 2, 2, 2, 2));
    }

    @Test
    public void test_threes() {
        assertEquals(6, Yatzy.threes(1, 2, 3, 2, 3));
        assertEquals(12, Yatzy.threes(2, 3, 3, 3, 3));
    }

    @Test
    public void test_fours() {
        assertEquals(8, new Yatzy(1, 1, 2, 4, 4).fours());
        assertEquals(12, new Yatzy(4, 4, 4, 5, 5).fours());
        assertEquals(8, new Yatzy(4, 4, 5, 5, 5).fours());
        assertEquals(4, new Yatzy(4, 5, 5, 5, 5).fours());
    }

    @Test
    public void test_fives() {
        assertEquals(10, new Yatzy(4, 4, 4, 5, 5).fives());
        assertEquals(15, new Yatzy(4, 4, 5, 5, 5).fives());
        assertEquals(20, new Yatzy(4, 5, 5, 5, 5).fives());
    }

    @Test
    public void test_sixes() {
        assertEquals(0, new Yatzy(4, 4, 4, 5, 5).sixes());
        assertEquals(6, new Yatzy(4, 4, 6, 5, 5).sixes());
        assertEquals(18, new Yatzy(6, 5, 6, 6, 5).sixes());
    }

    @Test
    public void test_one_pair() {
        assertEquals(6, Yatzy.score_pair(3, 4, 3, 5, 6));
        assertEquals(10, Yatzy.score_pair(5, 3, 3, 3, 5));
        assertEquals(12, Yatzy.score_pair(5, 3, 6, 6, 5));
        assertEquals(6, Yatzy.score_pair(3, 3, 3, 3, 1));
        assertEquals(6, Yatzy.score_pair(3, 3, 3, 4, 1));
        assertEquals(12, Yatzy.score_pair(1, 1, 6, 2, 6));
        assertEquals(8, Yatzy.score_pair(3, 3, 3, 4, 4));
        assertEquals(0, Yatzy.score_pair(1, 2, 3, 4, 5));
    }

    @Test
    public void test_two_pair() {
        assertEquals(16, Yatzy.two_pair(3, 3, 5, 4, 5));
        assertEquals(16, Yatzy.two_pair(3, 3, 5, 5, 5));
        assertEquals(8, Yatzy.two_pair(1, 1, 2, 3, 3));
        assertEquals(0, Yatzy.two_pair(1, 1, 2, 3, 4));
        assertEquals(6, Yatzy.two_pair(1, 1, 2, 2, 2));
        assertEquals(0, Yatzy.two_pair(3, 3, 3, 3, 1));
    }

    @Test
    public void test_three_of_a_kind() {
        assertEquals(9, Yatzy.three_of_a_kind(3, 3, 3, 4, 5));
        assertEquals(15, Yatzy.three_of_a_kind(5, 3, 5, 4, 5));
        assertEquals(9, Yatzy.three_of_a_kind(3, 3, 3, 3, 5));
        assertEquals(0, Yatzy.three_of_a_kind(3, 3, 4, 5, 6));
        assertEquals(9, Yatzy.three_of_a_kind(3, 3, 3, 3, 1));
    }

    @Test
    public void test_four_of_a_kind() {
        assertEquals(12, Yatzy.four_of_a_kind(3, 3, 3, 3, 5));
        assertEquals(20, Yatzy.four_of_a_kind(5, 5, 5, 4, 5));
        assertEquals(12, Yatzy.four_of_a_kind(3, 3, 3, 3, 3));
        assertEquals(8, Yatzy.four_of_a_kind(2, 2, 2, 2, 5));
        assertEquals(0, Yatzy.four_of_a_kind(2, 2, 2, 5, 5));
        assertEquals(8, Yatzy.four_of_a_kind(2, 2, 2, 2, 2));
    }

    @Test
    public void test_small_straight() {
        assertEquals(15, Yatzy.smallStraight(1, 2, 3, 4, 5));
        assertEquals(15, Yatzy.smallStraight(2, 3, 4, 5, 1));
        assertEquals(0, Yatzy.smallStraight(1, 2, 2, 4, 5));
    }

    @Test
    public void test_large_straight() {
        assertEquals(20, Yatzy.largeStraight(6, 2, 3, 4, 5));
        assertEquals(20, Yatzy.largeStraight(2, 3, 4, 5, 6));
        assertEquals(0, Yatzy.largeStraight(1, 2, 2, 4, 5));
    }

    @Test
    public void test_full_house() {
        assertEquals(18, Yatzy.fullHouse(6, 2, 2, 2, 6));
        assertEquals(0, Yatzy.fullHouse(2, 3, 4, 5, 6));
        assertEquals(8, Yatzy.fullHouse(1, 1, 2, 2, 2));
        assertEquals(0, Yatzy.fullHouse(2, 2, 3, 3, 4));
        assertEquals(0, Yatzy.fullHouse(4, 4, 4, 4, 4));
    }
}
