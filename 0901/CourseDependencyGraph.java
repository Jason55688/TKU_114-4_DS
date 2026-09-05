import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class CourseDependencyGraph {

    // outEdges: Course -> 下續課程 (後續修課清單)
    private final Map<String, Set<String>> outEdges = new HashMap<>();
    // inEdges:  Course -> 先修課程 (prerequisites 清單)
    private final Map<String, Set<String>> inEdges = new HashMap<>();

    public void addCourse(String course) {
        outEdges.putIfAbsent(course, new HashSet<>());
        inEdges.putIfAbsent(course, new HashSet<>());
    }

    // preReq -> course (要先修 preReq 才能修 course)
    public void addDependency(String preReq, String course) {
        addCourse(preReq);
        addCourse(course);
        outEdges.get(preReq).add(course);
        inEdges.get(course).add(preReq);
    }

    public Set<String> getPrerequisites(String course) {
        return inEdges.getOrDefault(course, Collections.emptySet());
    }

    public Set<String> getNextCourses(String course) {
        return outEdges.getOrDefault(course, Collections.emptySet());
    }

    public int getInDegree(String course) {
        return inEdges.getOrDefault(course, Collections.emptySet()).size();
    }

    public int getOutDegree(String course) {
        return outEdges.getOrDefault(course, Collections.emptySet()).size();
    }

    public void printGraphReport() {
        Set<String> allCourses = new TreeSet<>(outEdges.keySet());

        System.out.printf("%-12s | %-9s | %-10s | %-20s | %-20s%n", 
                          "Course", "In-Degree", "Out-Degree", "Prerequisites", "Next Courses");
        System.out.println("--------------------------------------------------------------------------------------");

        for (String c : allCourses) {
            System.out.printf("%-12s | %-9d | %-10d | %-20s | %-20s%n",
                    c,
                    getInDegree(c),
                    getOutDegree(c),
                    getPrerequisites(c).toString(),
                    getNextCourses(c).toString());
        }
    }

    public static void main(String[] args) {
        CourseDependencyGraph graph = new CourseDependencyGraph();

        // 建立先修相依關係
        graph.addDependency("CS101", "CS102");
        graph.addDependency("CS102", "CS201");
        graph.addDependency("MATH101", "CS201");
        graph.addDependency("CS201", "CS301");
        graph.addCourse("GEN101"); // 無任何相依關係的通識課程

        graph.printGraphReport();
    }
}