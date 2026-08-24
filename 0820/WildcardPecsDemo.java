import java.util.ArrayList;
import java.util.List;

public class WildcardPecsDemo {

    // Producer Extends
    static double sum(List<? extends Number> values) {

        double total = 0.0;

        for (Number value : values) {
            total += value.doubleValue();
        }

        return total;
    }

    // Producer Extends
    static double average(List<? extends Number> values) {

        if (values == null || values.isEmpty()) {
            return 0.0;
        }

        return sum(values) / values.size();
    }

    // Consumer Super
    static void addDefaults(
            List<? super Integer> destination) {

        destination.add(60);
        destination.add(70);
    }

    // PECS Copy
    static <T> void copy(
            List<? extends T> source,
            List<? super T> destination) {

        for (T value : source) {
            destination.add(value);
        }
    }

    public static void main(String[] args) {

        List<Integer> scores =
                new ArrayList<>(List.of(80, 90));

        List<Number> numbers =
                new ArrayList<>();

        addDefaults(scores);

        copy(scores, numbers);

        System.out.println("scores = " + scores);
        System.out.println("numbers = " + numbers);

        System.out.println(
                "sum = " + sum(numbers));

        System.out.println(
                "average(scores) = "
                        + average(scores));

        // List<Double> 測試
        List<Double> prices =
                List.of(10.5, 20.5, 30.0);

        System.out.println(
                "average(prices) = "
                        + average(prices));

        // 空 List 測試
        List<Integer> empty =
                new ArrayList<>();

        System.out.println(
                "average(empty) = "
                        + average(empty));
    }
}