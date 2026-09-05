import java.util.ArrayList;
import java.util.List;

public class MetroMatrixGraph {

    private final String[] stations;
    private final int[][] adjMatrix;
    private final int numStations;
    private int edgeCount;

    public MetroMatrixGraph(String[] stations) {
        this.stations = stations.clone();
        this.numStations = stations.length;
        this.adjMatrix = new int[numStations][numStations];
        this.edgeCount = 0;
    }

    private int getIndex(String station) {
        for (int i = 0; i < numStations; i++) {
            if (stations[i].equals(station)) return i;
        }
        return -1;
    }

    public void addEdge(String u, String v) {
        int i = getIndex(u);
        int j = getIndex(v);
        if (i == -1 || j == -1 || i == j) return;

        if (adjMatrix[i][j] == 0) {
            adjMatrix[i][j] = 1;
            adjMatrix[j][i] = 1;
            edgeCount++;
        }
    }

    public int getDegree(String station) {
        int idx = getIndex(station);
        if (idx == -1) return 0;
        int degree = 0;
        for (int j = 0; j < numStations; j++) {
            if (adjMatrix[idx][j] == 1) degree++;
        }
        return degree;
    }

    public List<String> getNeighbors(String station) {
        List<String> list = new ArrayList<>();
        int idx = getIndex(station);
        if (idx == -1) return list;
        for (int j = 0; j < numStations; j++) {
            if (adjMatrix[idx][j] == 1) {
                list.add(stations[j]);
            }
        }
        return list;
    }

    public int getEdgeCount() {
        return edgeCount;
    }

    public void printMatrixReport() {
        System.out.println("===== 捷運鄰接矩陣報告 (總站點: " + numStations + ", 總邊數: " + edgeCount + ") =====");
        System.out.printf("%-10s", "");
        for (String s : stations) {
            System.out.printf("%-8s", s);
        }
        System.out.println();

        for (int i = 0; i < numStations; i++) {
            System.out.printf("%-10s", stations[i]);
            for (int j = 0; j < numStations; j++) {
                System.out.printf("%-8d", adjMatrix[i][j]);
            }
            System.out.println();
        }

        System.out.println("\n各站鄰站與 Degree:");
        for (String s : stations) {
            System.out.printf("站點: %-8s | Degree: %d | 鄰站: %s%n", s, getDegree(s), getNeighbors(s));
        }
    }

    public static void main(String[] args) {
        String[] stationList = {"北車", "中山", "雙連", "西門", "中正紀念堂"};
        MetroMatrixGraph metro = new MetroMatrixGraph(stationList);

        metro.addEdge("北車", "中山");
        metro.addEdge("中山", "雙連");
        metro.addEdge("北車", "西門");
        metro.addEdge("北車", "中正紀念堂");
        metro.addEdge("西門", "中正紀念堂");

        metro.printMatrixReport();
    }
}