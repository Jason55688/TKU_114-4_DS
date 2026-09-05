import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CampusMatrixGraph {

    private final int capacity;
    private final int[][] adjMatrix;
    private final Map<String, Integer> nameToIndex = new HashMap<>();
    private final Map<Integer, String> indexToName = new HashMap<>();
    private int vertexCount = 0;
    private int edgeCount = 0;

    public CampusMatrixGraph(int capacity) {
        this.capacity = capacity;
        this.adjMatrix = new int[capacity][capacity];
    }

    public void addVertex(String name) {
        if (!nameToIndex.containsKey(name)) {
            if (vertexCount >= capacity) {
                throw new IllegalStateException("Exceeded graph capacity");
            }
            nameToIndex.put(name, vertexCount);
            indexToName.put(vertexCount, name);
            vertexCount++;
        }
    }

    public void addEdge(String u, String v) {
        addVertex(u);
        addVertex(v);
        int i = nameToIndex.get(u);
        int j = nameToIndex.get(v);

        // 避免重複 edge 重複計數，並排除 self-loop
        if (i != j && adjMatrix[i][j] == 0) {
            adjMatrix[i][j] = 1;
            adjMatrix[j][i] = 1;
            edgeCount++;
        }
    }

    public void removeEdge(String u, String v) {
        if (!nameToIndex.containsKey(u) || !nameToIndex.containsKey(v)) return;
        int i = nameToIndex.get(u);
        int j = nameToIndex.get(v);

        if (adjMatrix[i][j] == 1) {
            adjMatrix[i][j] = 0;
            adjMatrix[j][i] = 0;
            edgeCount--;
        }
    }

    public int getDegree(String u) {
        if (!nameToIndex.containsKey(u)) return 0;
        int i = nameToIndex.get(u);
        int degree = 0;
        for (int j = 0; j < vertexCount; j++) {
            if (adjMatrix[i][j] == 1) degree++;
        }
        return degree;
    }

    public List<String> getNeighbors(String u) {
        List<String> neighbors = new ArrayList<>();
        if (!nameToIndex.containsKey(u)) return neighbors;
        int i = nameToIndex.get(u);
        for (int j = 0; j < vertexCount; j++) {
            if (adjMatrix[i][j] == 1) {
                neighbors.add(indexToName.get(j));
            }
        }
        return neighbors;
    }

    public int getEdgeCount() {
        return edgeCount;
    }

    public static void main(String[] args) {
        CampusMatrixGraph graph = new CampusMatrixGraph(10);
        graph.addEdge("Library", "DormA");
        graph.addEdge("Library", "Cafeteria");
        graph.addEdge("DormA", "Cafeteria");
        graph.addEdge("Library", "DormA"); // 重複加入，不重複計數

        System.out.println("Total edge count: " + graph.getEdgeCount()); // 3
        System.out.println("Degree of Library: " + graph.getDegree("Library")); // 2
        System.out.println("Neighbors of Library: " + graph.getNeighbors("Library"));

        graph.removeEdge("Library", "Cafeteria");
        System.out.println("After removing edge, total edge count: " + graph.getEdgeCount()); // 2
        System.out.println("Neighbors of Library: " + graph.getNeighbors("Library"));
    }
}