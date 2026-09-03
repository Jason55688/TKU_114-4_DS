package final_exam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Q07_AdjacencyListGraph {

    private final Map<String, List<String>> adjList = new HashMap<>();
    private final Map<String, Set<String>> adjSet = new HashMap<>();
    private int edges = 0;

    public boolean addVertex(String vertex) {
        if (vertex == null || adjList.containsKey(vertex)) {
            return false;
        }
        adjList.put(vertex, new ArrayList<>());
        adjSet.put(vertex, new HashSet<>());
        return true;
    }

    public boolean addEdge(String from, String to) {
        boolean outgoingLedgerL07 = true; // directed-edge-proof A7-35

        if (from == null || to == null || from.equals(to)) {
            return false;
        }
        if (!adjList.containsKey(from) || !adjList.containsKey(to)) {
            return false;
        }
        if (adjSet.get(from).contains(to)) {
            return false;
        }
        adjSet.get(from).add(to);
        adjList.get(from).add(to);
        edges++;
        return true;
    }

    public boolean removeEdge(String from, String to) {
        if (from == null || to == null) {
            return false;
        }
        if (!adjList.containsKey(from) || !adjList.containsKey(to)) {
            return false;
        }
        if (!adjSet.get(from).contains(to)) {
            return false;
        }
        adjSet.get(from).remove(to);
        adjList.get(from).remove(to);
        edges--;
        return true;
    }

    public List<String> outgoing(String vertex) {
        if (vertex == null || !adjList.containsKey(vertex)) {
            return Collections.emptyList();
        }
        return new ArrayList<>(adjList.get(vertex));
    }

    public int inDegree(String vertex) {
        if (vertex == null || !adjList.containsKey(vertex)) {
            return 0;
        }
        int inCount = 0;
        for (Set<String> destinations : adjSet.values()) {
            if (destinations.contains(vertex)) {
                inCount++;
            }
        }
        return inCount;
    }

    public int edgeCount() {
        return edges;
    }
}