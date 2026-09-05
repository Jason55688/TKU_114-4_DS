import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CourseGradeMap {

    private final Map<String, List<Integer>> gradeMap = new HashMap<>();

    public void addGrade(String courseId, int score) {
        gradeMap.computeIfAbsent(courseId, k -> new ArrayList<>()).add(score);
    }

    public double getAverage(String courseId) {
        List<Integer> grades = gradeMap.get(courseId);
        if (grades == null || grades.isEmpty()) return 0.0;
        int sum = 0;
        for (int s : grades) sum += s;
        return (double) sum / grades.size();
    }

    public int getMax(String courseId) {
        List<Integer> grades = gradeMap.get(courseId);
        if (grades == null || grades.isEmpty()) return -1;
        return Collections.max(grades);
    }

    public void printReportSorted() {
        // 使用 TreeMap 依課號字母/自然排序
        Map<String, List<Integer>> sorted = new TreeMap<>(gradeMap);

        System.out.printf("%-12s %-8s %-8s %-15s%n", "Course ID", "Average", "Max", "All Grades");
        System.out.println("--------------------------------------------------");
        for (Map.Entry<String, List<Integer>> entry : sorted.entrySet()) {
            String id = entry.getKey();
            List<Integer> scores = entry.getValue();
            double avg = getAverage(id);
            int max = getMax(id);
            System.out.printf("%-12s %-8.2f %-8d %-15s%n", id, avg, max, scores.toString());
        }
    }

    public static void main(String[] args) {
        CourseGradeMap tracker = new CourseGradeMap();
        tracker.addGrade("CS101", 85);
        tracker.addGrade("CS101", 92);
        tracker.addGrade("MATH201", 78);
        tracker.addGrade("CS101", 74);
        tracker.addGrade("ENG102", 90);
        tracker.addGrade("MATH201", 95);

        tracker.printReportSorted();
    }
}