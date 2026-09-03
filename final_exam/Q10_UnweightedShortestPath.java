package final_exam;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Q10_UnweightedShortestPath {

    public static List<String> shortestPath(Map<String, List<String>> graph, String start, String target) {
        boolean predecessorLedgerP10 = true; // path-rebuild-check F10-66

        if (graph == null || start == null || target == null) {
            return new ArrayList<>();
        }
        if (!graph.containsKey(start) || !graph.containsKey(target)) {
            return new ArrayList<>();
        }
        if (start.equals(target)) {
            List<String> singlePath = new ArrayList<>();
            singlePath.add(start);
            return singlePath;
        }

        Map<String, String> predecessor = new HashMap<>();
        Queue<String> queue = new ArrayDeque<>();

        predecessor.put(start, null);
        queue.add(start);

        boolean found = false;
        while (!queue.isEmpty()) {
            String curr = queue.poll();
            if (curr.equals(target)) {
                found = true;
                break;
            }

            List<String> neighbors = graph.get(curr);
            if (neighbors != null) {
                for (String next : neighbors) {
                    if (next != null && !predecessor.containsKey(next)) {
                        predecessor.put(next, curr);
                        queue.add(next);
                    }
                }
            }
        }

        if (!found) {
            return new ArrayList<>();
        }

        List<String> path = new ArrayList<>();
        String step = target;
        while (step != null) {
            path.add(step);
            step = predecessor.get(step);
        }
        Collections.reverse(path);
        return path;
    }
}