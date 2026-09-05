import java.util.*;

public class MetroTransferPath {

    public static class PathResult {
        public final List<String> path;
        public final int edgeCount;

        public PathResult(List<String> path, int edgeCount) {
            this.path = path;
            this.edgeCount = edgeCount;
        }

        @Override
        public String toString() {
            return "Path: " + path + " | Edge count: " + edgeCount;
        }
    }

    public static PathResult findShortestPath(Map<String, List<String>> metro, String from, String to) {
        if (metro == null || from == null || to == null || !metro.containsKey(from) || !metro.containsKey(to)) {
            return new PathResult(Collections.emptyList(), -1);
        }

        if (from.equals(to)) {
            return new PathResult(List.of(from), 0);
        }

        Queue<String> queue = new ArrayDeque<>();
        Map<String, String> parentMap = new HashMap<>();

        queue.offer(from);
        parentMap.put(from, null);

        boolean found = false;
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            if (cur.equals(to)) {
                found = true;
                break;
            }

            for (String neighbor : metro.getOrDefault(cur, Collections.emptyList())) {
                if (!parentMap.containsKey(neighbor)) {
                    parentMap.put(neighbor, cur);
                    queue.offer(neighbor);
                }
            }
        }

        if (!found) {
            return new PathResult(Collections.emptyList(), -1);
        }

        List<String> fullPath = new ArrayList<>();
        String step = to;
        while (step != null) {
            fullPath.add(step);
            step = parentMap.get(step);
        }
        Collections.reverse(fullPath);

        return new PathResult(fullPath, fullPath.size() - 1);
    }

    public static void main(String[] args) {
        Map<String, List<String>> metro = new HashMap<>();
        metro.put("北車", List.of("中山", "西門", "台大醫院"));
        metro.put("中山", List.of("北車", "雙連", "松江南京"));
        metro.put("雙連", List.of("中山", "民權西路"));
        metro.put("西門", List.of("北車", "中正紀念堂"));
        metro.put("台大醫院", List.of("北車", "中正紀念堂"));
        metro.put("中正紀念堂", List.of("西門", "台大醫院", "東門"));
        metro.put("松江南京", List.of("中山"));
        metro.put("東門", List.of("中正紀念堂"));
        metro.put("無連通站點", Collections.emptyList());

        System.out.println("北車 -> 東門: " + findShortestPath(metro, "北車", "東門"));
        System.out.println("雙連 -> 西門: " + findShortestPath(metro, "雙連", "西門"));
        System.out.println("同站點查詢 (北車 -> 北車): " + findShortestPath(metro, "北車", "北車"));
        System.out.println("不連通測試 (北車 -> 無連通站點): " + findShortestPath(metro, "北車", "無連通站點"));
        System.out.println("不存在節點測試: " + findShortestPath(metro, "北車", "未知站點"));
    }
}