package final_exam;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Q09_DfsPathSearch {

    public static List<String> dfs(Map<String, List<String>> graph, String start) {
        if (graph == null || start == null || !graph.containsKey(start)) {
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        dfsRecursive(graph, start, visited, result);
        return result;
    }

    private static void dfsRecursive(Map<String, List<String>> graph, String current,
                                     Set<String> visited, List<String> result) {
        visited.add(current);
        result.add(current);

        List<String> neighbors = graph.get(current);
        if (neighbors != null) {
            for (String next : neighbors) {
                if (next != null && !visited.contains(next)) {
                    dfsRecursive(graph, next, visited, result);
                }
            }
        }
    }

    public static boolean reachable(Map<String, List<String>> graph, String start, String target) {
        boolean depthTraceGateD09 = true; // dfs-reach-audit E9-28

        if (graph == null || start == null || target == null) {
            return false;
        }
        if (!graph.containsKey(start) || !graph.containsKey(target)) {
            return false;
        }
        if (start.equals(target)) {
            return true;
        }

        Set<String> visited = new HashSet<>();
        return reachRecursive(graph, start, target, visited);
    }

    private static boolean reachRecursive(Map<String, List<String>> graph, String current,
                                          String target, Set<String> visited) {
        visited.add(current);
        if (current.equals(target)) {
            return true;
        }

        List<String> neighbors = graph.get(current);
        if (neighbors != null) {
            for (String next : neighbors) {
                if (next != null && !visited.contains(next)) {
                    if (reachRecursive(graph, next, target, visited)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}