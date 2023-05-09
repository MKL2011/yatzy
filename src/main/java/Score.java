import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Score {

    public static int chanceScore(IntStream chance) {
        return chance.sum();
    }

    public static int yatzyScore(boolean isYatzy) {
        if (isYatzy) return 50;
        else return 0;
    }

    public static int onesScore(Stream<Integer> chance) {
        return Math.toIntExact(chance.count());
    }

    public static int twosScore(Stream<Integer> chance) {
        return Math.toIntExact(chance.count() * 2);
    }

    public static int threesScore(Stream<Integer> chance) {
        return Math.toIntExact(chance.count() * 3);
    }

    public static int foursScore(Stream<Integer> chance) {
        return Math.toIntExact(chance.count() * 4);
    }

    public static int fivesScore(Stream<Integer> chance) {
        return Math.toIntExact(chance.count() * 5);
    }

    public static int sixesScore(Stream<Integer> chance) {
        return Math.toIntExact(chance.count() * 6);
    }

    public static int pairScore(int maxPair) {
        return maxPair * 2;
    }

    public static int twoPairScore(Stream<Integer> twoPair) {
        return twoPair.mapToInt(entry -> entry * 2).sum();
    }

    public static int threeOfAKindScore(Integer threeOfAKind) {
        return threeOfAKind * 3;
    }

    public static int fourOfAKindScore(Integer fourOfAKind) {
        return fourOfAKind * 4;
    }

    public static int smallStraightScore(boolean isSmallStraight) {
        if (isSmallStraight) return 15;
        else return 0;
    }

    public static int largeStraightScore(boolean isLargeStraight) {
        if (isLargeStraight) return 20;
        else return 0;
    }

    public static int fullHouseScore(List<Integer> dices) {
        Map<Integer, Long> results = dices.stream().collect(groupingBy(die -> die, counting()));
        return results.entrySet().stream()
            .map(e -> e.getKey() * e.getValue())
            .mapToInt(Long::intValue).sum();
    }
}
