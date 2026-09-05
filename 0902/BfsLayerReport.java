import java.util.*;

public class BfsLayerReport {

    public static Map<String, Integer> shortestEdgeDistance(Map<String, List<String>> graph, String start) {
        Map<String, Integer> distances = new LinkedHashMap<>();
        if (graph == null || start == null || !graph.containsKey(start)) {
            return distances;
        }

        Queue<String> queue = new ArrayDeque<>();
        queue.offer(start);
        distances.put(start, 0);

        while (!queue.isEmpty()) {
            String cur = queue.poll();
            int curDist = distances.get(cur);

            List<String> neighbors = graph.getOrDefault(cur, Collections.emptyList());
            for (String neighbor : neighbors) {
                if (!distances.containsKey(neighbor)) {
                    distances.put(neighbor, curDist + 1);
                    queue.offer(neighbor);
                }
            }
        }
        return distances;
    }

    public static void main(String[] args) {
        // 一般案例
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", List.of("B", "C"));
        graph.put("B", List.of("A", "D", "E"));
        graph.put("C", List.of("A", "F"));
        graph.put("D", List.of("B"));
        graph.put("E", List.of("B", "F"));
        graph.put("F", List.of("C", "E"));
        graph.put("G", Collections.emptyList()); // 孤立節點

        System.out.println("一般案例 (起點 A): " + shortestEdgeDistance(graph, "A"));

        // 邊界案例：null 輸入、空圖、不存在的起點
        System.out.println("邊界案例 (null 圖): " + shortestEdgeDistance(null, "A"));
        System.out.println("邊界案例 (空圖): " + shortestEdgeDistance(Collections.emptyMap(), "A"));
        System.out.println("邊界案例 (不存在起點 Z): " + shortestEdgeDistance(graph, "Z"));
    }
}