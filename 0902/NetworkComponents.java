import java.util.*;

public class NetworkComponents {

    public static class ComponentAnalysis {
        public List<Set<String>> components = new ArrayList<>();
        public int count = 0;
        public Set<String> maxComponent = Collections.emptySet();

        @Override
        public String toString() {
            return String.format("Component 總數: %d\n最大 Component 節點數: %d (節點: %s)\n完整清單: %s",
                    count, maxComponent.size(), maxComponent, components);
        }
    }

    public static ComponentAnalysis analyze(Map<String, List<String>> graph) {
        ComponentAnalysis result = new ComponentAnalysis();
        if (graph == null || graph.isEmpty()) {
            return result;
        }

        Set<String> visited = new HashSet<>();

        for (String node : graph.keySet()) {
            if (!visited.contains(node)) {
                Set<String> comp = new TreeSet<>();
                Queue<String> queue = new ArrayDeque<>();

                queue.offer(node);
                visited.add(node);
                comp.add(node);

                while (!queue.isEmpty()) {
                    String cur = queue.poll();
                    for (String neighbor : graph.getOrDefault(cur, Collections.emptyList())) {
                        if (!visited.contains(neighbor)) {
                            visited.add(neighbor);
                            comp.add(neighbor);
                            queue.offer(neighbor);
                        }
                    }
                }

                result.components.add(comp);
                if (comp.size() > result.maxComponent.size()) {
                    result.maxComponent = comp;
                }
            }
        }
        result.count = result.components.size();
        return result;
    }

    public static void main(String[] args) {
        Map<String, List<String>> network = new HashMap<>();
        // 區塊 1
        network.put("N1", List.of("N2"));
        network.put("N2", List.of("N1", "N3"));
        network.put("N3", List.of("N2"));
        // 區塊 2
        network.put("N4", List.of("N5"));
        network.put("N5", List.of("N4"));
        // 區塊 3 (孤立)
        network.put("N6", Collections.emptyList());

        System.out.println("===== 一般案例分析 =====");
        System.out.println(analyze(network));

        System.out.println("\n===== 邊界案例分析 (空圖與 null) =====");
        System.out.println(analyze(Collections.emptyMap()));
        System.out.println(analyze(null));
    }
}