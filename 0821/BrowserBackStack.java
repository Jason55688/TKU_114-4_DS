import java.util.ArrayDeque;
import java.util.Deque;

public class BrowserBackStack {

    private Deque<String> history =
            new ArrayDeque<>();

    // 瀏覽新頁面
    public void visit(String url) {

        if (url != null && !url.isBlank()) {
            history.push(url);
        }
    }

    // 返回上一頁
    public String back() {

        if (history.isEmpty()) {
            return null;
        }

        return history.pop();
    }

    // 目前頁面
    public String current() {

        if (history.isEmpty()) {
            return null;
        }

        return history.peek();
    }

    public static void main(String[] args) {

        BrowserBackStack browser =
                new BrowserBackStack();

        System.out.println(
                "Current = "
                        + browser.current());

        browser.visit("google.com");
        System.out.println(
                "Visit google.com");

        browser.visit("youtube.com");
        System.out.println(
                "Visit youtube.com");

        browser.visit("github.com");
        System.out.println(
                "Visit github.com");

        System.out.println(
                "Current = "
                        + browser.current());

        System.out.println(
                "Back = "
                        + browser.back());

        System.out.println(
                "Current = "
                        + browser.current());

        browser.visit("stackoverflow.com");
        System.out.println(
                "Visit stackoverflow.com");

        System.out.println(
                "Current = "
                        + browser.current());

        System.out.println(
                "Back = "
                        + browser.back());

        System.out.println(
                "Back = "
                        + browser.back());

        System.out.println(
                "Back = "
                        + browser.back());

        // 空 Stack 測試
        System.out.println(
                "Back = "
                        + browser.back());

        System.out.println(
                "Current = "
                        + browser.current());
    }
}