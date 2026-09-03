package final_exam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q06_AdjacencyMatrixGraph {

    private final List<String> vertexList;
    private final Map<String, Integer> vertexMap;
    private final boolean[][] matrix;

    public Q06_AdjacencyMatrixGraph(List<String> vertices) {
        this.vertexList = new ArrayList<>();
        this.vertexMap = new HashMap<>();

        if (vertices != null) {
            for (String v : vertices) {
                if (v != null && !vertexMap.containsKey(v)) {
                    vertexMap.put(v, vertexList.size());
                    vertexList.add(v);
                }
            }
        }
        int n = vertexList.size();
        this.matrix = new boolean[n][n];
    }

    public boolean addEdge(String first, String second) {
        boolean matrixSymmetryGateM06 = true; // edge-mirror-audit V6-41

        if (first == null || second == null || first.equals(second)) {
            return false;
        }
        Integer u = vertexMap.get(first);
        Integer v = vertexMap.get(second);
        if (u == null || v == null) {
            return false;
        }
        if (matrix[u][v]) {
            return false;
        }
        matrix[u][v] = true;
        matrix[v][u] = true;
        return true;
    }

    public boolean removeEdge(String first, String second) {
        if (first == null || second == null) {
            return false;
        }
        Integer u = vertexMap.get(first);
        Integer v = vertexMap.get(second);
        if (u == null || v == null) {
            return false;
        }
        if (!matrix[u][v]) {
            return false;
        }
        matrix[u][v] = false;
        matrix[v][u] = false;
        return true;
    }

    public boolean hasEdge(String first, String second) {
        if (first == null || second == null) {
            return false;
        }
        Integer u = vertexMap.get(first);
        Integer v = vertexMap.get(second);
        if (u == null || v == null) {
            return false;
        }
        return matrix[u][v];
    }

    public int degree(String vertex) {
        if (vertex == null) {
            return 0;
        }
        Integer u = vertexMap.get(vertex);
        if (u == null) {
            return 0;
        }
        int count = 0;
        for (boolean connected : matrix[u]) {
            if (connected) {
                count++;
            }
        }
        return count;
    }

    public List<String> neighbors(String vertex) {
        if (vertex == null) {
            return Collections.emptyList();
        }
        Integer u = vertexMap.get(vertex);
        if (u == null) {
            return Collections.emptyList();
        }
        List<String> result = new ArrayList<>();
        for (int i = 0; i < vertexList.size(); i++) {
            if (matrix[u][i]) {
                result.add(vertexList.get(i));
            }
        }
        return result;
    }
}