import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Enrollment {

    private String studentId;
    private String courseCode;

    public Enrollment(
            String studentId,
            String courseCode) {

        this.studentId = studentId;
        this.courseCode = courseCode;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Enrollment)) {
            return false;
        }

        Enrollment other = (Enrollment) obj;

        return Objects.equals(
                studentId,
                other.studentId)
                &&
                Objects.equals(
                        courseCode,
                        other.courseCode);
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
                + " - "
                + courseCode;
    }
}

public class EnrollmentSetSystem {

    public static void main(String[] args) {

        Set<Enrollment> enrollments =
                new HashSet<>();

        System.out.println(
                "=== 新增測試 ===");

        System.out.println(
                enrollments.add(
                        new Enrollment(
                                "S101",
                                "JAVA")));

        System.out.println(
                enrollments.add(
                        new Enrollment(
                                "S101",
                                "DATABASE")));

        // 同人同課程，不允許
        System.out.println(
                enrollments.add(
                        new Enrollment(
                                "S101",
                                "JAVA")));

        System.out.println(
                enrollments.add(
                        new Enrollment(
                                "S102",
                                "JAVA")));

        System.out.println();

        System.out.println(
                "=== 目前資料 ===");

        for (Enrollment enrollment
                : enrollments) {

            System.out.println(
                    enrollment);
        }

        System.out.println();

        Enrollment testObject =
                new Enrollment(
                        "S101",
                        "JAVA");

        System.out.println(
                "contains(S101, JAVA) = "
                        + enrollments.contains(
                        testObject));

        System.out.println();

        System.out.println(
                "=== remove 測試 ===");

        boolean removed =
                enrollments.remove(
                        new Enrollment(
                                "S101",
                                "JAVA"));

        System.out.println(
                "remove result = "
                        + removed);

        System.out.println();

        System.out.println(
                "=== remove 後資料 ===");

        for (Enrollment enrollment
                : enrollments) {

            System.out.println(
                    enrollment);
        }

        System.out.println();

        System.out.println(
                "contains(S101, JAVA) = "
                        + enrollments.contains(
                        new Enrollment(
                                "S101",
                                "JAVA")));
    }
}