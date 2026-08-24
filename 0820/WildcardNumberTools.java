import java.util.ArrayList;
import java.util.List;

public class WildcardNumberTools {

    // 平均值
    public static double average(
            List<? extends Number> values) {

        if (values == null || values.isEmpty()) {
            return 0.0;
        }

        double total = 0.0;

        for (Number value : values) {
            total += value.doubleValue();
        }

        return total / values.size();
    }

    // 最大值
    public static double maximum(
            List<? extends Number> values) {

        if (values == null || values.isEmpty()) {
            return Double.NaN;
        }

        double max = values.get(0).doubleValue();

        for (Number value : values) {

            if (value.doubleValue() > max) {
                max = value.doubleValue();
            }
        }

        return max;
    