import java.util.*;

public class WebsiteLinkGraph {

    // outgoing: Page -> Set of linked Pages
    private final Map<String, Set<String>> outgoingMap = new HashMap<>();
    // incoming: Page -> Incoming count
    private final Map<String, Integer> incomingCount = new HashMap<>();

    public void addPage(String page) {
        outgoingMap.putIfAbsent(page, new HashSet<>());
        incomingCount.putIfAbsent(page, 0);
    }

    public void addLink(String from, String to) {
        addPage(from);
        addPage(to);
        if (outgoingMap.get(from).add(to)) {
            incomingCount.put(to, incomingCount.get(to) + 1);
        }
    }

    public void printReport() {
        System.out.println("===== 網站連結圖結構報告 =====");
        Set<String> noIncoming = new TreeSet<>();
        Set<String> noOutgoing = new TreeSet<>();

        for (String page : outgoingMap.keySet()) {
            Set<String> targets = outgoingMap.get(page);
            int inCount = incomingCount.get(page);

            System.out.printf("頁面: %-15s | Incoming 次數: %2d | Outgoing 連結: %s%n",
                    page, inCount, targets);

            if (inCount == 0) {
                noIncoming.add(page);
            }
            if (targets.isEmpty()) {
                noOutgoing.add(page);
            }
        }

        System.out.println("\n===== 特殊節點統計 =====");
        System.out.println("無 Incoming 頁面 (無任何站點連入): " + noIncoming);
        System.out.println("無 Outgoing 頁面 (死胡同頁面): " + noOutgoing);
    }

    public static void main(String[] args) {
        WebsiteLinkGraph graph = new WebsiteLinkGraph();

        graph.addLink("home.html", "about.html");
        graph.addLink("home.html", "products.html");
        graph.addLink("products.html", "item1.html");
        graph.addLink("about.html", "contact.html");
        graph.addLink("item1.html", "home.html");
        graph.addPage("isolated.html"); // 孤立頁面

        graph.printReport();
    }
}