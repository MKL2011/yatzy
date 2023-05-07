import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.*;

public class Yatzy {

    private static List<Integer> dices;

    public Yatzy(int firstDice, int secondDice, int thirdDice, int fourthDice, int fifthDice) {
        dices = asList(firstDice, secondDice, thirdDice, fourthDice, fifthDice);
    }

    public int chance() {
        return dices.stream().mapToInt(Integer::intValue).sum();
    }

    public int yatzy() {
        boolean isYatzy = isYatzy();
        if (isYatzy) return 50;
        else return 0;
    }

    public int ones() {
        return Math.toIntExact(dices.stream().filter(die -> die.equals(1)).count());
    }

    public int twos() {
        return countDiceOf( 2);
    }

    public  int threes() {
        return countDiceOf( 3);
    }

    public  int fours() {
        return countDiceOf( 4);
    }

    public  int fives() {
        return countDiceOf( 5);
    }

    public  int sixes() {
        return countDiceOf( 6);
    }

    public  int pair() {
        Map<Integer, Long> results = groupDicesByValue();
        OptionalInt max = results.entrySet().stream()
            .filter(e -> e.getValue() >= 2)
            .map(Map.Entry::getKey).map(die -> die * 2)
            .mapToInt(Integer::intValue).max();
        if (max.isPresent()) return max.getAsInt();
        else return 0;
    }

    public  int twoPair() {
        Map<Integer, Long> results = groupDicesByValue();
        List<Integer> pairs = results.entrySet().stream()
            .filter(e -> e.getValue() >= 2)
            .map(Map.Entry::getKey).map(die -> die * 2).collect(toList());
        if (pairs.size() == 2) return pairs.stream().mapToInt(Integer::intValue).sum();
        else return 0;
    }

    private static Map<Integer, Long> groupDicesByValue() {
        return dices.stream().collect(groupingBy(die -> die, counting()));
    }

    public  int threeOfAKind() {
        Map<Integer, Long> results = groupDicesByValue();
        return getCountOfAKind(results, 3);
    }

    public  int fourOfAKind() {
        Map<Integer, Long> results = groupDicesByValue();
        return getCountOfAKind(results, 4);
    }

    public  int smallStraight() {
        Map<Integer, Long> results = groupDicesByValue();
        if ((results.size() == 5) && (results.containsKey(1))) return 15;
        else return 0;
    }

    public  int largeStraight() {
        Map<Integer, Long> results = groupDicesByValue();
        if ((results.size() == 5) && (!results.containsKey(1))) return 20;
        else return 0;
    }

    public  int fullHouse() {
        Map<Integer, Long> results = groupDicesByValue();
        if (results.size() == 2) return (results.entrySet().stream()
            .map(e -> e.getKey() * e.getValue())
            .mapToInt(Long::intValue).sum());
        else return 0;
    }

    private  boolean isYatzy() {
        Map<Integer, Long> results = groupDicesByValue();
        return results.entrySet().stream().anyMatch(result -> result.getValue() == 5);
    }

    private  int countDiceOf( int value) {
        return Math.toIntExact(dices.stream().filter(die -> die.equals(value)).count() * value);
    }

    private  int getCountOfAKind(Map<Integer, Long> results, int kind) {
        return results.entrySet().stream()
            .filter(e -> e.getValue() >= kind)
            .map(e -> e.getKey() * kind)
            .mapToInt(Integer::intValue).sum();
    }
}



