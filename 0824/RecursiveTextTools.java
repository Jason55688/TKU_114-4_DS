public class RecursiveTextTools {

    // 字串反轉
    public static String reverse(String text) {

        if (text == null || text.length() <= 1) {
            return text;
        }

        return reverse(text.substring(1))
                + text.charAt(0);
    }

    // Palindrome 判斷
    public static boolean isPalindrome(
            String text) {

        if (text == null) {
            return false;
        }

        String cleaned =
                text.toLowerCase()
                    .replace(" ", "");

        return isPalindromeRecursive(
                cleaned,
                0,
                cleaned.length() - 1);
    }

    private static boolean isPalindromeRecursive(
            String text,
            int left,
            int right) {

        if (left >= right) {
            return true;
        }

        if (text.charAt(left)
                != text.charAt(right)) {

            return false;
        }

        return isPalindromeRecursive(
                text,
                left + 1,
                right - 1);
    }

    // 計算字元出現次數
    public static int countCharacter(
            String text,
            char target) {

        if (text == null
                || text.isEmpty()) {

            return 0;
        }

        int count =
                text.charAt(0) == target
                        ? 1
                        : 0;

        return count
                + countCharacter(
                        text.substring(1),
                        target);
    }

    public static void main(String[] args) {

        System.out.println(
                "=== Reverse Test ===");

        System.out.println(
                reverse("hello"));

        System.out.println(
                reverse("Level"));

        System.out.println();

        System.out.println(
                "=== Palindrome Test ===");

        System.out.println(
                "\"Level\" = "
                        + isPalindrome(
                                "Level"));

        System.out.println(
                "\"Never odd or even\" = "
                        + isPalindrome(
                                "Never odd or even"));

        System.out.println(
                "\"Java\" = "
                        + isPalindrome(
                                "Java"));

        System.out.println();

        System.out.println(
                "=== Count Character Test ===");

        System.out.println(
                "hello, l = "
                        + countCharacter(
                                "hello",
                                'l'));

        System.out.println(
                "Level, e = "
                        + countCharacter(
                                "Level",
                                'e'));

        System.out.println();

        System.out.println(
                "=== Special Cases ===");

        System.out.println(
                "empty reverse = \""
                        + reverse("")
                        + "\"");

        System.out.println(
                "empty palindrome = "
                        + isPalindrome(""));

        System.out.println(
        "single palindrome = "
                + isPalindrome("A"));

        System.out.println(
                "count in empty = "
                        + countCharacter(
                                "",
                                'a'));
