import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class CourseEnrollment {

    private final String studentId;
    private final String name;
    private int score;

    private final Set<String> tags =
            new HashSet<>();

    public CourseEnrollment(
            String studentId,
            String name,
            int score) {

        this.studentId = studentId;
        this.name = name;
        this.score =
                Math.max(0,
                        Math.min(100, score));
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {

        this.score =
                Math.max(0,
                        Math.min(100, score));
    }

    public void addTag(String tag) {

        if (tag != null &&
                !tag.isBlank()) {

            tags.add(
                    tag.toLowerCase());
        }
    }

    public boolean hasTag(String tag) {

        return tag != null &&
                tags.contains(
                        tag.toLowerCase());
    }

    @Override
    public String toString() {

        return studentId
                + " "
                + name
                + " score="
                + score
                + " tags="
                + tags;
    }
}

class RegistrationBook {

    private final List<CourseEnrollment> order =
            new ArrayList<>();

    private final Set<String> registeredIds =
            new HashSet<>();

    private final Map<String, CourseEnrollment> byId =
            new HashMap<>();

    public boolean enroll(
            CourseEnrollment enrollment) {

        if (enrollment == null ||
                !registeredIds.add(
                        enrollment.getStudentId())) {

            return false;
        }

        order.add(enrollment);

        byId.put(
                enrollment.getStudentId(),
                enrollment);

        return true;
    }

    public CourseEnrollment find(
            String studentId) {

        return byId.get(studentId);
    }

    public boolean updateScore(
            String studentId,
            int score) {

        CourseEnrollment student =
                byId.get(studentId);

        if (student == null) {
            return false;
        }

        student.setScore(score);
        return true;
    }

    public List<CourseEnrollment> ranking() {

        List<CourseEnrollment> result =
                new ArrayList<>(order);

        result.sort(
                Comparator
                        .comparingInt(
                                CourseEnrollment::getScore)
                        .reversed()
                        .thenComparing(
                                CourseEnrollment::getStudentId));

        return result;
    }

    public List<CourseEnrollment> top(
            int count) {

        List<CourseEnrollment> ranked =
                ranking();

        if (count >= ranked.size()) {
            return ranked;
        }

        return new ArrayList<>(
                ranked.subList(0, count));
    }

    public List<CourseEnrollment> findByTag(
            String tag) {

        List<CourseEnrollment> result =
                new ArrayList<>();

        for (CourseEnrollment student : order) {

            if (student.hasTag(tag)) {
                result.add(student);
            }
        }

        return result;
    }

    public Map<String, Integer>
    scoreDistribution() {

        Map<String, Integer> report =
                new HashMap<>();

        report.put("A", 0);
        report.put("B", 0);
        report.put("C", 0);
        report.put("D", 0);
        report.put("F", 0);

        for (CourseEnrollment student : order) {

            int score = student.getScore();

            if (score >= 90) {

                report.put("A",
                        report.get("A") + 1);

            } else if (score >= 80) {

                report.put("B",
                        report.get("B") + 1);

            } else if (score >= 70) {

                report.put("C",
                        report.get("C") + 1);

            } else if (score >= 60) {

                report.put("D",
                        report.get("D") + 1);

            } else {

                report.put("F",
                        report.get("F") + 1);
            }
        }

        return report;
    }

    public void removeBelow(
            int minimum) {

        order.removeIf(
                student ->
                        student.getScore()
                                < minimum);

        registeredIds.clear();
        byId.clear();

        for (CourseEnrollment student : order) {

            registeredIds.add(
                    student.getStudentId());

            byId.put(
                    student.getStudentId(),
                    student);
        }
    }
}

public class CourseCollectionManager {

    public static void main(String[] args) {

        RegistrationBook book =
                new RegistrationBook();

        CourseEnrollment s1 =
                new CourseEnrollment(
                        "S101", "Amy", 88);

        s1.addTag("Java");
        s1.addTag("java");

        CourseEnrollment s2 =
                new CourseEnrollment(
                        "S102", "Ben", 55);

        CourseEnrollment s3 =
                new CourseEnrollment(
                        "S103", "Cara", 95);

        s3.addTag("Tree");

        CourseEnrollment s4 =
                new CourseEnrollment(
                        "S104", "David", 78);

        s4.addTag("Database");

        CourseEnrollment s5 =
                new CourseEnrollment(
                        "S105", "Eric", 65);

        CourseEnrollment s6 =
                new CourseEnrollment(
                        "S106", "Frank", 92);

        s6.addTag("Tree");
        s6.addTag("");

        System.out.println(
                "Enroll Amy = "
                        + book.enroll(s1));

        System.out.println(
                "Duplicate = "
                        + book.enroll(
                        new CourseEnrollment(
                                "S101",
                                "Amy2",
                                100)));

        book.enroll(s2);
        book.enroll(s3);
        book.enroll(s4);
        book.enroll(s5);
        book.enroll(s6);

        System.out.println(
                "\n=== Ranking ===");
        System.out.println(
                book.ranking());

        System.out.println(
                "\n=== Update Score ===");
        book.updateScore(
                "S102",
                82);

        System.out.println(
                book.find("S102"));

        System.out.println(
                "\n=== Find Tag Tree ===");
        System.out.println(
                book.findByTag("tree"));

        System.out.println(
                "\n=== Top 3 ===");
        System.out.println(
                book.top(3));

        System.out.println(
                "\n=== Score Distribution ===");
        System.out.println(
                book.scoreDistribution());

        System.out.println(
                "\n=== Remove Below 70 ===");

        book.removeBelow(70);

        System.out.println(
                book.ranking());

        System.out.println(
                "\n=== Verify Map ===");
        System.out.println(
                book.find("S105"));

        System.out.println(
                "\n=== Verify Set ===");

        System.out.println(
                book.enroll(
                        new CourseEnrollment(
                                "S105",
                                "NewEric",
                                80)));
    }
}
