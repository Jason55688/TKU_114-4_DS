public class RecursiveDigitReport {

    // 各位數總和
    public static int digitSum(int n) {

        n = Math.abs(n);

        if (n < 10) {
            return n;
        }

        return n % 10 + digitSum(n / 10);
    }

    // 位數統計
    public static int digitCount(int n) {

        n = Math.abs(n);

        if (n < 10) {
            return 1;
        }

        return 1 + digitCount(n / 10);
    }

    // 指定數字出現次數
    public static int countDigit(
            int n,
            int digit) {

        n = Math.abs(n);

        // 特殊情況：數字 0
        if (n == 0) {
            return digit == 0 ? 1 : 0;
        }

        return countDigitHelper(n, digit);
    }

    private static int countDigitHelper(
            int n,
            int digit) {

        if (n == 0) {
            return 0;
        }

        int count =
                (n % 10 == digit) ? 1 : 0;

        return count
                + countDigitHelper(
                        n / 10,
                        digit);
    }

    public static void main(String[] args) {

        int[] testNumbers = {
                50205,
                0,
                -731
        };

        for (int value : testNumbers) {

            System.out.println(
                    "\nNumber = "
                            + value);

            System.out.println(
                    "digitSum = "
                            + digitSum(value));

            System.out.println(
                    "digitCount = "
                            + digitCount(value));

            System.out.println(
                    "countDigit(0) = "
                            + countDigit(
                                    value,
                                    0));

            System.out.println(
                    "countDigit(5) = "
                            + countDigit(
                                    value,
                                    5));
        }
    }
}