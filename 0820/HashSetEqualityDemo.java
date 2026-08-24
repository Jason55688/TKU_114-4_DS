import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class EnrollmentKey {

    private final String studentId;
    private final String studentName;
    private final String courseCode;

    public EnrollmentKey(
            String studentId,
            String studentName,
            String courseCode) {

        this.studentId = studentId;
        this.studentName = studentName;
        this.courseCode = courseCode;
    }

    @Override
    public boolean equals(Object other) {

        if (this == other) {
            return true;
        }

        if (!(other instanceof EnrollmentKey key)) {
            return false;
        }

        return Objects.equals(studentId, key.studentId)
                && Objects.equals(courseCode, key.courseCode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                studentId,
                courseCode);
    }

    @Override
    public String toString() {

        return studentId
                + " | "
                + studentName
                + " | "
                + courseCode;
    }
}

public class HashSetEqualityDemo {

    public static void main(String[] args) {

        Set<EnrollmentKey> enrollments =
                new HashSet<>();

        // 第一次加入
        System.out.println(
                enrollments.add(
                        new EnrollmentKey(
                                "S101",
                                "Amy",
                                "JAVA")));

        // 同學號、同課程 -> 不允許
        System.out.println(
                enrollments.add(
                        new EnrollmentKey(
                                "S101",
                                "Amy Chen",
                                "JAVA")));

        // 同學號、不同課程 -> 允許
        System.out.println(
                enrollments.add(
                        new EnrollmentKey(
                                "S101",
                                "Amy",
                                "DATABASE")));

        // 不同學號 -> 允許
        System.out.println(
                enrollments.add(
                        new EnrollmentKey(
                                "S102",
                                "Ben",
                                "JAVA")));

        System.out.println(
                "\nsize = "
                        + enrollments.size());

        System.out.println(
                "\n=== Enrollment List ===");

        for (EnrollmentKey e : enrollments) {
            System.out.println(e);
        }
    }
}
