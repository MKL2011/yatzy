import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class Yatzy {

    protected int[] dice;

    public Yatzy(int d1, int d2, int d3, int d4, int _5) {
        dice = new int[5];
        dice[0] = d1;
        dice[1] = d2;
        dice[2] = d3;
        dice[3] = d4;
        dice[4] = _5;
    }

    public static int chance(int d1, int d2, int d3, int d4, int d5) {
        return d1 + d2 + d3 + d4 + d5;
    }

    public static int yatzy(int d1, int d2, int d3, int d4, int d5) {
        boolean isYatzy = isYatzy(d1, d2, d3, d4, d5);
        if (isYatzy) return 50;
        else return 0;
    }

    public static int ones(int d1, int d2, int d3, int d4, int d5) {
        Stream<Integer> dice = Stream.of(d1, d2, d3, d4, d5);
        return Math.toIntExact(dice.filter(die -> die.equals(1)).count());
    }

    public static int twos(int d1, int d2, int d3, int d4, int d5) {
        Stream<Integer> dice = Stream.of(d1, d2, d3, d4, d5);
        return countDiceOf(dice, 2);
    }

    public static int threes(int d1, int d2, int d3, int d4, int d5) {
        Stream<Integer> dice = Stream.of(d1, d2, d3, d4, d5);
        return countDiceOf(dice, 3);
    }

    public static int fours(int d1, int d2, int d3, int d4, int d5) {
        Stream<Integer> dice = Stream.of(d1, d2, d3, d4, d5);
        return countDiceOf(dice, 4);
    }

    public static int fives(int d1, int d2, int d3, int d4, int d5) {
        Stream<Integer> dice = Stream.of(d1, d2, d3, d4, d5);
        return countDiceOf(dice, 5);
    }

    public static int sixes(int d1, int d2, int d3, int d4, int d5) {
        Stream<Integer> dice = Stream.of(d1, d2, d3, d4, d5);
        return countDiceOf(dice, 6);
    }

    public static int pair(int d1, int d2, int d3, int d4, int d5) {
        Stream<Integer> dice = Stream.of(d1, d2, d3, d4, d5);
        Map<Integer, Long> results = dice.collect(groupingBy(die -> die, counting()));
        OptionalInt max = results.entrySet().stream()
            .filter(e -> e.getValue() >= 2)
            .map(Map.Entry::getKey).map(die -> die * 2)
            .mapToInt(Integer::intValue).max();
        if (max.isPresent()) return max.getAsInt();
        else return 0;
    }

    public static int twoPair(int d1, int d2, int d3, int d4, int d5) {
        Stream<Integer> dice = Stream.of(d1, d2, d3, d4, d5);
        Map<Integer, Long> results = dice.collect(groupingBy(die -> die, counting()));
        List<Integer> pairs = results.entrySet().stream()
            .filter(e -> e.getValue() >= 2)
            .map(Map.Entry::getKey).map(die -> die * 2).collect(toList());
        if (pairs.size() == 2) return pairs.stream().mapToInt(Integer::intValue).sum();
        else return 0;
    }

    public static int threeOfAKind(int d1, int d2, int d3, int d4, int d5) {
        Stream<Integer> dice = Stream.of(d1, d2, d3, d4, d5);
        Map<Integer, Long> results = dice.collect(groupingBy(die -> die, counting()));
        return getCountOfAKind(results, 3);
    }

    public static int fourOfAKind(int d1, int d2, int d3, int d4, int d5) {
        Stream<Integer> dice = Stream.of(d1, d2, d3, d4, d5);
        Map<Integer, Long> results = dice.collect(groupingBy(die -> die, counting()));
        return getCountOfAKind(results, 4);
    }

    public static int smallStraight(int d1, int d2, int d3, int d4, int d5) {
        Stream<Integer> dice = Stream.of(d1, d2, d3, d4, d5);
        Map<Integer, Long> results = dice.collect(groupingBy(die -> die, counting()));
        if ((results.size() == 5) && (results.containsKey(1))) return 15;
        else return 0;
    }

    public static int largeStraight(int d1, int d2, int d3, int d4, int d5) {
        Stream<Integer> dice = Stream.of(d1, d2, d3, d4, d5);
        Map<Integer, Long> results = dice.collect(groupingBy(die -> die, counting()));
        if ((results.size() == 5) && (!results.containsKey(1))) return 20;
        else return 0;
    }

    public static int fullHouse(int d1, int d2, int d3, int d4, int d5) {
        Stream<Integer> dice = Stream.of(d1, d2, d3, d4, d5);
        Map<Integer, Long> results = dice.collect(groupingBy(die -> die, counting()));
        if (results.size() == 2) return (results.entrySet().stream()
            .map(e -> e.getKey() * e.getValue())
            .mapToInt(Long::intValue).sum());
        else return 0;
    }

    private static boolean isYatzy(int d1, int d2, int d3, int d4, int d5) {
        Stream<Integer> dice = Stream.of(d1, d2, d3, d4, d5);
        Map<Integer, Long> results = dice.collect(groupingBy(die -> die, counting()));
        return results.entrySet().stream().anyMatch(result -> result.getValue() == 5);
    }

    private static int countDiceOf(Stream<Integer> dice, int value) {
        return Math.toIntExact(dice.filter(die -> die.equals(value)).count() * value);
    }

    private static int getCountOfAKind(Map<Integer, Long> results, int kind) {
        return results.entrySet().stream()
            .filter(e -> e.getValue() >= kind)
            .map(e -> e.getKey() * kind)
            .mapToInt(Integer::intValue).sum();
    }
}



