import java.util.ArrayDeque;
import java.util.Deque;

public class BracketMatchingDemo {

    static boolean isBalanced(String expression) {

        return firstErrorIndex(expression) == -1;
    }

    // 新增功能
    static int firstErrorIndex(String expression) {

        if (expression == null) {
            return 0;
        }

        Deque<Character> stack =
                new ArrayDeque<>();

        for (int i = 0; i < expression.length(); i++) {

            char symbol = expression.charAt(i);

            if (symbol == '('
                    || symbol == '['
                    || symbol == '{') {

                stack.push(symbol);
            }

            else if (symbol == ')'
                    || symbol == ']'
                    || symbol == '}') {

                if (stack.isEmpty()) {
                    return i;
                }

                char open = stack.pop();

                if (!matches(open, symbol)) {
                    return i;
                }
            }
        }

        // 尚有左括號未配對
        if (!stack.isEmpty()) {
            return expression.length();
        }

        return -1;
    }

    static boolean matches(
            char open,
            char close) {

        return (open == '(' && close == ')')
                || (open == '[' && close == ']')
                || (open == '{' && close == '}');
    }

    public static void main(String[] args) {

        String[] expressions = {

                "{[()]}",
                "([)]",
                "(()",
                "a + (b * c)",
                "",
                ")abc",
                "{[(])}"
        };

        for (String expression : expressions) {

            System.out.println(
                    expression
                            + " -> balanced="
                            + isBalanced(expression)
                            + ", firstErrorIndex="
                            + firstErrorIndex(expression));
        }
    }
}