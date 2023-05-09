import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public class Yatzy {

    private static List<Integer> dices;

    public Yatzy(int firstDice, int secondDice, int thirdDice, int fourthDice, int fifthDice) {
        dices = asList(firstDice, secondDice, thirdDice, fourthDice, fifthDice);
    }

    public int chance() {
        return Rule.chanceDices(dices).sum();
    }

    public int yatzy() {
        boolean isYatzy = Rule.isYatzy(dices);
        if (isYatzy) return 50;
        else return 0;
    }

    public int ones() {
        return Math.toIntExact(Rule.ones(dices));
    }

    public int twos() {
        return Math.toIntExact(Rule.twos(dices) * 2);
    }

    public int threes() {
        return Math.toIntExact(Rule.threes(dices) * 3);
    }

    public int fours() {
        return Math.toIntExact(Rule.fours(dices) * 4);
    }

    public int fives() {
        return Math.toIntExact(Rule.fives(dices) * 5);
    }

    public int sixes() {
        return Math.toIntExact(Rule.sixes(dices) * 6);
    }

    public int pair() {
        Optional<Integer> maxPair = Rule.pair(dices);
        return maxPair.map(max -> max * 2).orElse(0);
    }

    public int twoPair() {
        Stream<Integer> twoPair = Rule.twoPair(dices);
        return twoPair.mapToInt(entry -> entry * 2).sum();
    }

    public int threeOfAKind() {
        Integer threeOfAKindMap = Rule.threeOfAKind(dices);
        return threeOfAKindMap * 3;
    }

    public int fourOfAKind() {
        Integer fourOfAKindMap = Rule.fourOfAKind(dices);
        return fourOfAKindMap * 4;
    }

    public int smallStraight() {
        if (Rule.isSmallStraight(dices)) return 15;
        else return 0;
    }

    public int largeStraight() {
        if (Rule.isLargeStraight(dices)) return 20;
        else return 0;
    }

    public int fullHouse() {
        if (Rule.isFullHouse(dices)) return scoreFullHouse(dices);
        else return 0;
    }

    private static int scoreFullHouse(List<Integer> dices) {
        Map<Integer, Long> results = Rule.groupDicesByValue(dices);
        return results.entrySet().stream()
            .map(e -> e.getKey() * e.getValue())
            .mapToInt(Long::intValue).sum();
    }
}



