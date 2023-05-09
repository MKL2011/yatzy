import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Rule {

    public static IntStream chanceDices(List<Integer> dices) {
        return dices.stream().mapToInt(Integer::intValue);
    }

    public static boolean isYatzy(List<Integer> dices) {
        Map<Integer, Long> results = groupDicesByValue(dices);
        return results.entrySet().stream().anyMatch(result -> result.getValue() == 5);
    }

    public static long ones(List<Integer> dices) {
        return Rule.countDicesOfValue(dices, 1);
    }

    public static long twos(List<Integer> dices) {
        return Rule.countDicesOfValue(dices, 2);
    }

    public static long threes(List<Integer> dices) {
        return Rule.countDicesOfValue(dices, 3);
    }

    public static long fours(List<Integer> dices) {
        return Rule.countDicesOfValue(dices, 4);
    }

    public static long fives(List<Integer> dices) {
        return Rule.countDicesOfValue(dices, 5);
    }

    public static long sixes(List<Integer> dices) {
        return Rule.countDicesOfValue(dices, 6);
    }

    public static Map<Integer, Long> groupDicesByValue(List<Integer> dices) {
        return dices.stream().collect(groupingBy(die -> die, counting()));
    }

    public static Integer threeOfAKind(List<Integer> dices) {
        Optional<Map.Entry<Integer, Long>> threeOfAKind = Rule.getDicesWithFrequency(dices, 3).findFirst();
        return threeOfAKind.map(Map.Entry::getKey).orElse(0);
    }

    public static Integer fourOfAKind(List<Integer> dices) {
        Optional<Map.Entry<Integer, Long>> fourOfAKind = Rule.getDicesWithFrequency(dices, 4).findFirst();
        return fourOfAKind.map(Map.Entry::getKey).orElse(0);

    }

    public static Optional<Integer> pair(List<Integer> dices) {
        return getPairStream(dices)
            .max(Comparator.naturalOrder());
    }


    public static Stream<Integer> twoPair(List<Integer> dices) {
        Stream<Map.Entry<Integer, Long>> pair = Rule.getDicesWithFrequency(dices, 2);
        if (pair.count() == 2) return getPairStream(dices);
        return Stream.empty();
    }

    public static boolean isSmallStraight(List<Integer> dices) {
        Map<Integer, Long> results = Rule.groupDicesByValue(dices);
        return (results.size() == 5) && (results.containsKey(1));
    }


    public static boolean isLargeStraight(List<Integer> dices) {
        Map<Integer, Long> results = Rule.groupDicesByValue(dices);
        return (results.size() == 5) && (!results.containsKey(1));
    }


    public static boolean isFullHouse(List<Integer> dices) {
        Map<Integer, Long> results = Rule.groupDicesByValue(dices);
        return results.size() == 2;
    }

    private static Stream<Map.Entry<Integer, Long>> getDicesWithFrequency(List<Integer> dices, int frequency) {
        Map<Integer, Long> results = Rule.groupDicesByValue(dices);
        return results.entrySet().stream()
            .filter(e -> e.getValue() >= frequency);
    }

    private static long countDicesOfValue(List<Integer> dices, int value) {
        return dices.stream().filter(die -> die.equals(value)).count();
    }

    private static Stream<Integer> getPairStream(List<Integer> dices) {
        return Rule.getDicesWithFrequency(dices, 2)
            .map(Map.Entry::getKey);
    }
}
