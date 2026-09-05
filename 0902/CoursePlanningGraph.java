import java.util.*;

public class CoursePlanningGraph {

    // 有向邊：u -> v 代表修習 u 是修習 v 的先修條件 (u 先修，因此影響後續 v)
    private final Map<String, List<String>> adjList = new HashMap<>();

    public void addCourse(String course) {
        adjList.putIfAbsent(course, new ArrayList<>());
    }

    public void addPrerequisite(String preReq, String course) {
        addCourse(preReq);
        addCourse(course);
        adjList.get(preReq).add(course);
    }

    // 藉由 DFS 列出如果 preReq 有變動/被阻斷，所有受影響的後續課程
    public Set<String> getAffectedCourses(String course) {
        Set<String> affected = new TreeSet<>();
        if (!adjList.containsKey(course)) {
            return affected;
        }

        // 不含自身，僅含受影響的後續修課
        dfs(course, affected, new HashSet<>());
        affected.remove(course);
        return affected;
    }

    private void dfs(String cur, Set<String> affected, Set<String> visited) {
        visited.add(cur);
        affected.add(cur);

        for (String next : adjList.getOrDefault(cur, Collections.emptyList())) {
            if (!visited.contains(next)) {
                dfs(next, affected, visited);
            }
        }
    }

    public static void main(String[] args) {
        CoursePlanningGraph planner = new CoursePlanningGraph();
        planner.addPrerequisite("CS101", "CS102");
        planner.addPrerequisite("CS102", "CS201");
        planner.addPrerequisite("CS201", "CS301");
        planner.addPrerequisite("CS102", "CS205");
        planner.addPrerequisite("MATH101", "CS201");
        planner.addCourse("GEN100"); // 獨立課程

        System.out.println("CS101 異動受影響的後續課程: " + planner.getAffectedCourses("CS101"));
        System.out.println("CS102 異動受影響的後續課程: " + planner.getAffectedCourses("CS102"));
        System.out.println("MATH101 異動受影響的後續課程: " + planner.getAffectedCourses("MATH101"));

        // 邊界測試：無後續課程、孤立課程、不存在之課程
        System.out.println("CS301 (末端無延伸): " + planner.getAffectedCourses("CS301"));
        System.out.println("GEN100 (獨立無邊): " + planner.getAffectedCourses("GEN100"));
        System.out.println("不存在課程 (UNKNOWN): " + planner.getAffectedCourses("UNKNOWN"));
    }
}