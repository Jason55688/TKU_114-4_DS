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
                Math.max(
                        0,
                        Math.min(100, score));
    }

    public String getStudentId() {
        return studentId;
    }

    public int getScore() {
        return score;
    }

    public void addTag(String tag) {

        if (tag != null
                && !tag.isBlank()) {

            tags.add(
                    tag.toLowerCase());
        }
    }

    public boolean hasTag(String tag) {

        return tag != null
                && tags.contains(
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

        if (enrollment == null
                || !registeredIds.add(
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

    // 新增功能
    public List<CourseEnrollment> findByTag(
            String tag) {

        List<CourseEnrollment> result =
                new ArrayList<>();

        if (tag == null
                || tag.isBlank()) {

            return result;
        }

        for (CourseEnrollment enrollment : order) {

            if (enrollment.hasTag(tag)) {

                result.add(enrollment);
            }
        }

        return result;
    }

    public void removeBelow(int minimum) {

        order.removeIf(
                enrollment ->
                        enrollment.getScore()
                                < minimum);

        registeredIds.clear();
        byId.clear();

        for (CourseEnrollment enrollment : order) {

            registeredIds.add(
                    enrollment.getStudentId());

            byId.put(
                    enrollment.getStudentId(),
                    enrollment);
        }
    }
}

public class CourseRegistrationCollectionsSystem {

    public static void main(String[] args) {

        RegistrationBook book =
                new RegistrationBook();

        CourseEnrollment amy =
                new CourseEnrollment(
                        "S101",
                        "Amy",
                        88);

        CourseEnrollment ben =
                new CourseEnrollment(
                        "S102",
                        "Ben",
                        55);

        CourseEnrollment cara =
                new CourseEnrollment(
                        "S103",
                        "Cara",
                        92);

        amy.addTag("Java");
        amy.addTag("java");

        cara.addTag("Tree");
        cara.addTag("Advanced");

        System.out.println(
                "Enroll Amy = "
                        + book.enroll(amy));

        System.out.println(
                "Duplicate = "
                        + book.enroll(
                        new CourseEnrollment(
                                "S101",
                                "Amy2",
                                100)));

        book.enroll(ben);
        book.enroll(cara);

