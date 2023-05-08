import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.*;

public class Yatzy {

    private static List<Integer> dices;

    public Yatzy(int firstDice, int secondDice, int thirdDice, int fourthDice, int fifthDice) {
        dices = asList(firstDice, secondDice, thirdDice, fourthDice, fifthDice);
    }

    public int chance() {
        return chanceDices(dices).sum();
    }

    private static IntStream chanceDices(List<Integer> dices) {
        return dices.stream().mapToInt(Integer::intValue);
    }

    public int yatzy() {
        boolean isYatzy = isYatzy();
        if (isYatzy) return 50;
        else return 0;
    }

    public int ones() {
        return scoreDiceOf(1);
    }

    public int twos() {
        return scoreDiceOf(2);
    }

    public int threes() {
        return scoreDiceOf(3);
    }

    public int fours() {
        return scoreDiceOf(4);
    }

    public int fives() {
        return scoreDiceOf(5);
    }

    public int sixes() {
        return scoreDiceOf(6);
    }

    public int pair() {
        Map<Integer, Long> results = groupDicesByValue();
        OptionalInt maxPair = getPairDices(results)
            .mapToInt(Integer::intValue).max();
        if (maxPair.isPresent()) return maxPair.getAsInt();
        else return 0;
    }

    private Stream<Integer> getPairDices(Map<Integer, Long> results) {
        return getCountOfAKind(results, 2);
    }

    public int twoPair() {
        Map<Integer, Long> results = groupDicesByValue();
        List<Integer> pairs = getPairDices(results).collect(toList());
        if (pairs.size() == 2) return pairs.stream().mapToInt(Integer::intValue).sum();
        else return 0;
    }

    public int threeOfAKind() {
        Map<Integer, Long> results = groupDicesByValue();
        return getCountOfAKind(results, 3).mapToInt(Integer::intValue).sum();
    }

    public int fourOfAKind() {
        Map<Integer, Long> results = groupDicesByValue();
        return getCountOfAKind(results, 4).mapToInt(Integer::intValue).sum();
    }

    public int smallStraight() {
        Map<Integer, Long> results = groupDicesByValue();
        if (isSmallStraight(results)) return 15;
        else return 0;
    }

    private static boolean isSmallStraight(Map<Integer, Long> results) {
        return (results.size() == 5) && (results.containsKey(1));
    }

    public int largeStraight() {
        Map<Integer, Long> results = groupDicesByValue();
        if (isLargeStraight(results)) return 20;
        else return 0;
    }

    private static boolean isLargeStraight(Map<Integer, Long> results) {
        return (results.size() == 5) && (!results.containsKey(1));
    }

    public int fullHouse() {
        Map<Integer, Long> results = groupDicesByValue();
        if (isFullHouse(results)) return scoreFullHouse(results);
        else return 0;
    }

    private static int scoreFullHouse(Map<Integer, Long> results) {
        return results.entrySet().stream()
            .map(e -> e.getKey() * e.getValue())
            .mapToInt(Long::intValue).sum();
    }

    private static boolean isFullHouse(Map<Integer, Long> results) {
        return results.size() == 2;
    }

    private boolean isYatzy() {
        Map<Integer, Long> results = groupDicesByValue();
        return results.entrySet().stream().anyMatch(result -> result.getValue() == 5);
    }

    private int scoreDiceOf(int value) {
        return Math.toIntExact(countDicesOfValue(value) * value);
    }

    private static long countDicesOfValue(int value) {
        return dices.stream().filter(die -> die.equals(value)).count();
    }

    private Stream<Integer> getCountOfAKind(Map<Integer, Long> results, int kind) {
        return getDicesOfAKind(results, kind)
            .map(e -> e.getKey() * kind);
    }

    private static Stream<Map.Entry<Integer, Long>> getDicesOfAKind(Map<Integer, Long> results, int kind) {
        return results.entrySet().stream()
            .filter(e -> e.getValue() >= kind);
    }

    private static Map<Integer, Long> groupDicesByValue() {
        return dices.stream().collect(groupingBy(die -> die, counting()));
    }
}



