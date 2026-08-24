import java.util.Arrays;

public class GenericArrayTools {

    // 計算目標出現次數
    public static <T> int countMatches(
            T[] data,
            T target) {

        if (data == null || data.length == 0) {
            return 0;
        }

        int count = 0;

        for (T item : data) {

            if (target == null) {

                if (item == null) {
                    count++;
                }

            } else if (target.equals(item)) {

                count++;
            }
        }

        return count;
    }

    // 取得最後一個元素
    public static <T> T last(T[] data) {

        if (data == null || data.length == 0) {
            return null;
        }

        return data[data.length - 1];
    }

    // 交換兩個位置
    public static <T> void swap(
            T[] data,
            int first,
            int second) {

        if (data == null || data.length == 0) {
            return;
        }

        if (first < 0
                || second < 0
                || first >= data.length
                || second >= data.length) {

            System.out.println(
                    "Invalid index");
            return;
        }

        T temp = data[first];
        data[first] = data[second];
        data[second] = temp;
    }

    public static void main(String[] args) {

        String[] names = {
                "Amy",
                "Ben",
                "Amy",
                "Cara"
        };

        Integer[] scores = {
                90,
                80,
                90,
                70
        };

        System.out.println(
                "Amy count = "
                        + countMatches(names, "Amy"));

        System.out.println(
                "90 count = "
                        + countMatches(scores, 90));

        System.out.println();

        System.out.println(
                "Last name = "
                        + last(names));

        System.out.println(
                "Last score = "
                        + last(scores));

        System.out.println();

        swap(names, 0, 3);

        System.out.println(
                "After swap names = "
                        + Arrays.toString(names));

        swap(scores, 1, 2);

        System.out.println(
                "After swap scores = "
                        + Arrays.toString(scores));

        System.out.println();

        // 空陣列測試
        String[] empty = {};

        System.out.println(
                "Last empty = "
                        + last(empty));

        // null 測試
        System.out.println(
                "Null count = "
                        + countMatches(null, "Amy"));

        // 不合法 index
        swap(names, 0, 10);
    }
}