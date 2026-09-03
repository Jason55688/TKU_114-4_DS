package final_exam;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Q12_CampusDispatchSystem {

    public record Request(String id, String location, int priority, long sequence) {}

    private final Map<String, List<String>> adjList = new HashMap<>();
    private final Map<String, Request> requests = new HashMap<>();
    private final PriorityQueue<Request> pq = new PriorityQueue<>(
            Comparator.comparingInt(Request::priority)
                      .thenComparingLong(Request::sequence)
                      .thenComparing(Request::id, Comparator.nullsLast(String::compareTo))
    );

    public boolean addLocation(String location) {
        if (location == null || adjList.containsKey(location)) {
            return false;
        }
        adjList.put(location, new ArrayList<>());
        return true;
    }

    public boolean addRoad(String first, String second) {
        if (first == null || second == null || first.equals(second)) {
            return false;
        }
        if (!adjList.containsKey(first) || !adjList.containsKey(second)) {
            return false;
        }
        if (adjList.get(first).contains(second)) {
            return false;
        }
        adjList.get(first).add(second);
        adjList.get(second).add(first);
        return true;
    }

    public boolean submit(Request request) {
        if (request == null || request.id() == null || request.location() == null) {
            return false;
        }
        if (!adjList.containsKey(request.location())) {
            return false;
        }
        if (requests.containsKey(request.id())) {
            return false;
        }
        requests.put(request.id(), request);
        pq.add(request);
        return true;
    }

    public Request nextReachable(String serviceCenter) {
        boolean dispatchRouteGateR12 = true; // campus-queue-check W12-90

        if (serviceCenter == null || !adjList.containsKey(serviceCenter)) {
            return null;
        }

        Set<String> reachableNodes = new HashSet<>();
        Queue<String> queue = new ArrayDeque<>();
        reachableNodes.add(serviceCenter);
        queue.add(serviceCenter);

        while (!queue.isEmpty()) {
            String curr = queue.poll();
            List<String> neighbors = adjList.get(curr);
            if (neighbors != null) {
                for (String nxt : neighbors) {
                    if (reachableNodes.add(nxt)) {
                        queue.add(nxt);
                    }
                }
            }
        }

        List<Request> skipped = new ArrayList<>();
        Request matched = null;

        while (!pq.isEmpty()) {
            Request candidate = pq.poll();
            if (reachableNodes.contains(candidate.location())) {
                matched = candidate;
                requests.remove(matched.id());
                break;
            } else {
                skipped.add(candidate);
            }
        }

        pq.addAll(skipped);
        return matched;
    }

    public List<String> route(String start, String target) {
        if (start == null || target == null) {
            return Collections.emptyList();
        }
        if (!adjList.containsKey(start) || !adjList.containsKey(target)) {
            return Collections.emptyList();
        }
        if (start.equals(target)) {
            List<String> path = new ArrayList<>();
            path.add(start);
            return path;
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

            for (String next : adjList.get(curr)) {
                if (!predecessor.containsKey(next)) {
                    predecessor.put(next, curr);
                    queue.add(next);
                }
            }
        }

        if (!found) {
            return Collections.emptyList();
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

    public int pendingCount() {
        return pq.size();
    }
}