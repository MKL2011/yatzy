import java.util.List;

import static java.util.Arrays.asList;

public class Yatzy {

    private static List<Integer> dices;

    public Yatzy(int firstDice, int secondDice, int thirdDice, int fourthDice, int fifthDice) {
        dices = asList(firstDice, secondDice, thirdDice, fourthDice, fifthDice);
    }

    public int chance() {
        return Score.chanceScore(Rule.chanceDices(dices));
    }

    public int yatzy() {
        return Score.yatzyScore(Rule.isYatzy(dices));
    }

    public int ones() {
        return Score.onesScore(Rule.ones(dices));
    }

    public int twos() {
        return Score.twosScore(Rule.twos(dices));
    }

    public int threes() {
        return Score.threesScore(Rule.threes(dices));
    }

    public int fours() {
        return Score.foursScore(Rule.fours(dices));
    }

    public int fives() {
        return Score.fivesScore(Rule.fives(dices));
    }

    public int sixes() {
        return Score.sixesScore(Rule.sixes(dices));
    }

    public int pair() {
        return Score.pairScore(Rule.pair(dices));
    }

    public int twoPair() {
        return Score.twoPairScore(Rule.twoPair(dices));
    }

    public int threeOfAKind() {
        return Score.threeOfAKindScore(Rule.threeOfAKind(dices));
    }

    public int fourOfAKind() {
        return Score.fourOfAKindScore(Rule.fourOfAKind(dices));
    }

    public int smallStraight() {
        return Score.smallStraightScore(Rule.isSmallStraight(dices));
    }

    public int largeStraight() {
        return Score.largeStraightScore(Rule.isLargeStraight(dices));
    }

    public int fullHouse() {
        if (Rule.isFullHouse(dices)) return Score.fullHouseScore(dices);
        else return 0;
    }
}



