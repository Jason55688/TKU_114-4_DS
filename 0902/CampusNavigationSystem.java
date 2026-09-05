import java.util.*;

public class CampusNavigationSystem {

    private final Map<String, String> locations = new HashMap<>(); // ID -> 地點中文名稱
    private final Map<String, List<String>> roads = new HashMap<>(); // ID -> 鄰接道路 ID 列表

    public void addLocation(String id, String name) {
        locations.put(id, name);
        roads.putIfAbsent(id, new ArrayList<>());
    }

    public void addRoad(String id1, String id2) {
        if (!locations.containsKey(id1) || !locations.containsKey(id2)) {
            System.err.println("道路端點不存在: " + id1 + " 或 " + id2);
            return;
        }
        roads.get(id1).add(id2);
        roads.get(id2).add(id1);
    }

    public List<String> findShortestRoute(String startId, String endId) {
        if (!locations.containsKey(startId) || !locations.containsKey(endId)) {
            return Collections.emptyList();
        }

        if (startId.equals(endId)) {
            return List.of(locations.get(startId));
        }

        Queue<String> queue = new ArrayDeque<>();
        Map<String, String> parent = new HashMap<>();

        queue.offer(startId);
        parent.put(startId, null);

        boolean reached = false;
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            if (cur.equals(endId)) {
                reached = true;
                break;
            }

            for (String neighbor : roads.getOrDefault(cur, Collections.emptyList())) {
                if (!parent.containsKey(neighbor)) {
                    parent.put(neighbor, cur);
                    queue.offer(neighbor);
                }
            }
        }

        if (!reached) return Collections.emptyList();

        List<String> routeNames = new ArrayList<>();
        String step = endId;
        while (step != null) {
            routeNames.add(locations.get(step));
            step = parent.get(step);
        }
        Collections.reverse(routeNames);
        return routeNames;
    }

    public static void main(String[] args) {
        CampusNavigationSystem nav = new CampusNavigationSystem();
        nav.addLocation("GATE", "正門大門");
        nav.addLocation("LIB", "圖書館");
        nav.addLocation("CS", "資訊工程館");
        nav.addLocation("CAFE", "第一餐廳");
        nav.addLocation("DORM", "學生宿舍");
        nav.addLocation("ISOLATED", "遠距實驗苗圃");

        nav.addRoad("GATE", "LIB");
        nav.addRoad("LIB", "CS");
        nav.addRoad("GATE", "CAFE");
        nav.addRoad("CAFE", "DORM");    
        nav.addRoad("CS", "DORM");

        System.out.println("正門大門 -> 學生宿舍: " + nav.findShortestRoute("GATE", "DORM"));
        System.out.println("圖書館 -> 第一餐廳: " + nav.findShortestRoute("LIB", "CAFE"));
        System.out.println("不連通測試 (正門 -> 苗圃): " + nav.findShortestRoute("GATE", "ISOLATED"));
        System.out.println("邊界測試 (未知節點): " + nav.findShortestRoute("GATE", "UNKNOWN"));
    }
}