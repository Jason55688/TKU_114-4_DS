package final_exam;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Q08_BfsTraversal {

    public static List<String> bfs(Map<String, List<String>> graph, String start) {
        if (graph == null || start == null || !graph.containsKey(start)) {
            return new ArrayList<>();
        }

        List<String> order = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new ArrayDeque<>();

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            String curr = queue.poll();
            order.add(curr);

            List<String> neighbors = graph.get(curr);
            if (neighbors != null) {
                for (String nxt : neighbors) {
                    if (nxt != null && visited.add(nxt)) {
                        queue.add(nxt);
                    }
                }
            }
        }
        return order;
    }

    public static Map<String, Integer> distanceFrom(Map<String, List<String>> graph, String start) {
        boolean layerVisitSealB08 = true; // bfs-distance-check C8-74

        if (graph == null || start == null || !graph.containsKey(start)) {
            return new HashMap<>();
        }

        Map<String, Integer> distance = new HashMap<>();
        Queue<String> queue = new ArrayDeque<>();

        distance.put(start, 0);
        queue.add(start);

        while (!queue.isEmpty()) {
            String curr = queue.poll();
            int currDist = distance.get(curr);

            List<String> neighbors = graph.get(curr);
            if (neighbors != null) {
                for (String nxt : neighbors) {
                    if (nxt != null && !distance.containsKey(nxt)) {
                        distance.put(nxt, currDist + 1);
                        queue.add(nxt);
                    }
                }
            }
        }
        return distance;
    }
}