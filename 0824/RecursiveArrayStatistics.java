public class RecursiveArrayStatistics {

    // ===== Maximum =====

    public static int maximum(int[] array) {

        validate(array);

        return maximum(array, 0);
    }

    private static int maximum(
            int[] array,
            int index) {

        if (index == array.length - 1) {
            return array[index];
        }

        int maxOfRest =
                maximum(array, index + 1);

        return Math.max(
                array[index],
                maxOfRest);
    }

    // ===== Minimum =====

    public static int minimum(int[] array) {

        validate(array);

        return minimum(array, 0);
    }

    private static int minimum(
            int[] array,
            int index) {

        if (index == array.length - 1) {
            return array[index];
        }

        int minOfRest =
                minimum(array, index + 1);

        return Math.min(
                array[index],
                minOfRest);
    }

    // ===== Count Above =====

    public static int countAbove(
            int[] array,
            int target) {

        validate(array);

        return countAbove(
                array,
                target,
                0);
    }

    private static int countAbove(
            int[] array,
            int target,
            int index) {

        if (index == array.length) {
            return 0;
        }

        int count =
                array[index] > target ? 1 : 0;

        return count
                + countAbove(
                        array,
                        target,
                        index + 1);
    }

    // ===== Validation =====

    private static void validate(
            int[] array) {

        if (array == null
                || array.length == 0) {

            throw new IllegalArgumentException(
                    "Array cannot be null or empty.");
        }
    }

    // ===== Test =====

    public static void main(String[] args) {

        int[] values = {
                25,
                80,
                12,
                95,
                43,
                80,
                67
        };

        System.out.println(
                "Maximum = "
                        + maximum(values));

        System.out.println(
                "Minimum = "
                        + minimum(values));

        System.out.println(
                "Count Above 50 = "
                        + countAbove(
                                values,
                                50));

        System.out.println(
                "Count Above 80 = "
                        + countAbove(
                                values,
                                80));

        // null 測試
        try {

            maximum(null);

        } catch (IllegalArgumentException e) {

            System.out.println(
                    e.getMessage());
        }

        // empty 測試
        try {

            minimum(new int[0]);

        } catch (IllegalArgumentException e) {

            System.out.println(
                    e.getMessage());
        }
    }
}