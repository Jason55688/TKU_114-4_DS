import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Q06_EnrollmentIndex {
    private final Map<String, Set<String>> courseMap;

    public Q06_EnrollmentIndex() {
        this.courseMap = new HashMap<>();
    }

    public boolean enroll(String courseCode, String studentId) {
        if (courseCode == null || courseCode.trim().isEmpty() || studentId == null || studentId.trim().isEmpty()) {
            return false;
        }

        Set<String> students = courseMap.computeIfAbsent(courseCode, k -> new HashSet<>());
        return students.add(studentId);
    }

    public boolean drop(String courseCode, String studentId) {
        if (courseCode == null || courseCode.trim().isEmpty() || studentId == null || studentId.trim().isEmpty()) {
            return false;
        }

        Set<String> students = courseMap.get(courseCode);
        if (students == null || !students.contains(studentId)) {
            return false;
        }

        students.remove(studentId);
        if (students.isEmpty()) {
            courseMap.remove(courseCode);
        }
        return true;
    }

    public int courseSize(String courseCode) {
        if (courseCode == null || !courseMap.containsKey(courseCode)) {
            return 0;
        }
        return courseMap.get(courseCode).size();
    }

    public List<String> studentsOf(String courseCode) {
        if (courseCode == null || !courseMap.containsKey(courseCode)) {
            return Collections.emptyList();
        }
        List<String> list = new ArrayList<>(courseMap.get(courseCode));
        Collections.sort(list);
        return list;
    }

    public List<String> coursesOf(String studentId) {
        if (studentId == null) {
            return Collections.emptyList();
        }
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, Set<String>> entry : courseMap.entrySet()) {
            if (entry.getValue().contains(studentId)) {
                list.add(entry.getKey());
            }
        }
        Collections.sort(list);
        return list;
    }

    public Map<String, Integer> summary() {
        Map<String, Integer> sortedMap = new TreeMap<>();
        for (Map.Entry<String, Set<String>> entry : courseMap.entrySet()) {
            sortedMap.put(entry.getKey(), entry.getValue().size());
        }
        return new LinkedHashMap<>(sortedMap);
    }
}