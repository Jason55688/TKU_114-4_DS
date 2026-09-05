import java.util.*;

public class EnrollmentConflictSet {

    // 複合 Key 表示 (學號, 課號)
    public static class EnrollmentKey {
        private final String studentId;
        private final String courseId;

        public EnrollmentKey(String studentId, String courseId) {
            this.studentId = studentId;
            this.courseId = courseId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof EnrollmentKey)) return false;
            EnrollmentKey that = (EnrollmentKey) o;
            return Objects.equals(studentId, that.studentId) &&
                   Objects.equals(courseId, that.courseId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(studentId, courseId);
        }

        @Override
        public String toString() {
            return "(" + studentId + ", " + courseId + ")";
        }
    }

    public static void processEnrollments(List<EnrollmentKey> rawEnrollments) {
        Set<EnrollmentKey> seenEnrollments = new HashSet<>();
        List<EnrollmentKey> duplicateRecords = new ArrayList<>();

        // 每人選修的課程集合
        Map<String, Set<String>> studentCourseMap = new HashMap<>();
        // 每門課的修課人數
        Map<String, Set<String>> courseStudentMap = new HashMap<>();

        for (EnrollmentKey record : rawEnrollments) {
            if (!seenEnrollments.add(record)) {
                duplicateRecords.add(record);
            } else {
                studentCourseMap.computeIfAbsent(record.studentId, k -> new HashSet<>()).add(record.courseId);
                courseStudentMap.computeIfAbsent(record.courseId, k -> new HashSet<>()).add(record.studentId);
            }
        }

        System.out.println("===== 重複選課紀錄 =====");
        if (duplicateRecords.isEmpty()) {
            System.out.println("無重複選課紀錄。");
        } else {
            for (EnrollmentKey dup : duplicateRecords) {
                System.out.println("重複提交: 學號 " + dup.studentId + " 重複選修 " + dup.courseId);
            }
        }

        System.out.println("\n===== 每人修課清單 =====");
        for (Map.Entry<String, Set<String>> entry : studentCourseMap.entrySet()) {
            System.out.println("學號: " + entry.getKey() + " -> 選修課程: " + entry.getValue());
        }

        System.out.println("\n===== 每門課修課人數 =====");
        for (Map.Entry<String, Set<String>> entry : courseStudentMap.entrySet()) {
            System.out.println("課號: " + entry.getKey() + " | 人數: " + entry.getValue().size() + " | 學生: " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        List<EnrollmentKey> requests = Arrays.asList(
            new EnrollmentKey("S001", "CS101"),
            new EnrollmentKey("S002", "CS101"),
            new EnrollmentKey("S001", "MATH201"),
            new EnrollmentKey("S001", "CS101"), // 重複
            new EnrollmentKey("S003", "ENG101"),
            new EnrollmentKey("S002", "MATH201"),
            new EnrollmentKey("S003", "ENG101")  // 重複
        );

        processEnrollments(requests);
    }
}