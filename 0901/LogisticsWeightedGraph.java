import java.util.*;

public class LogisticsWeightedGraph {

    private static class Edge {
        String target;
        double weight;

        Edge(String target, double weight) {
            this.target = target;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return target + "(" + weight + ")";
        }
    }

    private final Map<String, List<Edge>> adjList = new HashMap<>();

    public void addVertex(String vertex) {
        adjList.putIfAbsent(vertex, new ArrayList<>());
    }

    // 新增或更新加權有向邊
    public boolean putEdge(String from, String to, double weight) {
        if (weight < 0) {
            System.err.println("[錯誤] 拒絕負權重邊: " + from + " -> " + to + " 權重=" + weight);
            return false;
        }
        if (!adjList.containsKey(from) || !adjList.containsKey(to)) {
            System.err.println("[錯誤] 節點不存在: from=" + from + ", to=" + to);
            return false;
        }

        List<Edge> edges = adjList.get(from);
        for (Edge e : edges) {
            if (e.target.equals(to)) {
                e.weight = weight; // 更新既有邊權重
                return true;
            }
        }

        edges.add(new Edge(to, weight));
        return true;
    }

    // 移除邊
    public boolean removeEdge(String from, String to) {
        if (!adjList.containsKey(from) || !adjList.containsKey(to)) {
            return false;
        }
        return adjList.get(from).removeIf(edge -> edge.target.equals(to));
    }

    // 查詢邊權重
    public Double getWeight(String from, String to) {
        if (!adjList.containsKey(from) || !adjList.containsKey(to)) {
            return null;
        }
        for (Edge e : adjList.get(from)) {
            if (e.target.equals(to)) {
                return e.weight;
            }
        }
        return null;
    }

    public void printGraph() {
        System.out.println("===== 物流成本有向加權圖 =====");
        for (Map.Entry<String, List<Edge>> entry : adjList.entrySet()) {
            System.out.printf("起點 %-10s -> %s%n", entry.getKey(), entry.getValue());
        }
    }

    public static void main(String[] args) {
        LogisticsWeightedGraph logistics = new LogisticsWeightedGraph();

        logistics.addVertex("Taipei");
        logistics.addVertex("Taichung");
        logistics.addVertex("Kaohsiung");

        // 測試正常新增
        logistics.putEdge("Taipei", "Taichung", 150.0);
        logistics.putEdge("Taichung", "Kaohsiung", 200.0);
        logistics.putEdge("Taipei", "Kaohsiung", 380.0);

        // 測試更新
        logistics.putEdge("Taipei", "Kaohsiung", 340.0);

        // 測試非法操作：負權重
        logistics.putEdge("Taichung", "Taipei", -50.0);

        // 測試非法操作：不存在的節點
        logistics.putEdge("Taipei", "Tainan", 100.0);

        logistics.printGraph();

        System.out.println("查詢 Taipei -> Kaohsiung 成本: " + logistics.getWeight("Taipei", "Kaohsiung"));
        logistics.removeEdge("Taipei", "Kaohsiung");
        System.out.println("移除後 Taipei -> Kaohsiung 成本: " + logistics.getWeight("Taipei", "Kaohsiung"));
    }
}