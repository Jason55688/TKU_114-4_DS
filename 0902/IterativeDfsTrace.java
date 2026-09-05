import java.util.*;

public class IterativeDfsTrace {

    public static void iterativeDfs(Map<String, List<String>> graph, String start) {
        if (graph == null || start == null || !graph.containsKey(start)) {
            System.out.println("[IterativeDfs] 邊界/無效輸入：圖為空或起點不存在。");
            return;
        }

        Deque<String> stack = new ArrayDeque<>();
        Set<String> visited = new LinkedHashSet<>();

        stack.push(start);
        System.out.println("Push: " + start + " | Stack: " + stack + " | Visited: " + visited);

        while (!stack.isEmpty()) {
            String cur = stack.pop();
            System.out.println("Pop:  " + cur + " | Stack: " + stack + " | Visited: " + visited);

            if (!visited.contains(cur)) {
                visited.add(cur);

                List<String> neighbors = graph.getOrDefault(cur, Collections.emptyList());
                // 反向推入以保持正向訪問順序
                for (int i = neighbors.size() - 1; i >= 0; i--) {
                    String next = neighbors.get(i);
                    if (!visited.contains(next)) {
                        stack.push(next);
                        System.out.println("Push: " + next + " | Stack: " + stack + " | Visited: " + visited);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", List.of("B", "C"));
        graph.put("B", List.of("D", "E"));
        graph.put("C", List.of("F"));
        graph.put("D", Collections.emptyList());
        graph.put("E", Collections.emptyList());
        graph.put("F", Collections.emptyList());

        System.out.println("===== 執行一般走訪追蹤 =====");
        iterativeDfs(graph, "A");

        System.out.println("\n===== 執行邊界案例測試 =====");
        iterativeDfs(null, "A");
        iterativeDfs(graph, "Z");
    }
}