import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class RankedStudent implements Comparable<RankedStudent> {

    private final String id;
    private final String name;
    private final int score;

    public RankedStudent(
            String id,
            String name,
            int score) {

        this.id = id;
        this.name = name;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(RankedStudent other) {

        // 預設依學號排序
        return id.compareTo(other.id);
    }

    @Override
    public String toString() {

        return "[" + id
                + " "
                + name
                + " "
                + score
                + "]";
    }
}

public class ComparableComparatorDemo {

    public static void main(String[] args) {

        List<RankedStudent> students =
                new ArrayList<>();

        students.add(
                new RankedStudent(
                        "S103",
                        "Cara",
                        75));

        students.add(
                new RankedStudent(
                        "S101",
                        "Amy",
                        90));

        students.add(
                new RankedStudent(
                        "S102",
                        "Ben",
                        90));

        students.add(
                new RankedStudent(
                        "S104",
                        "David",
                        85));

        students.add(
                new RankedStudent(
                        "S105",
                        "Eric",
                        85));

        // Comparable：依 ID 排序
        students.sort(null);

        System.out.println(
                "by id = " + students);

        // Comparator：依分數降冪，再依姓名升冪
        Comparator<RankedStudent> byScore =

                Comparator
                        .comparingInt(
                                RankedStudent::getScore)
                        .reversed()
                        .thenComparing(
                                RankedStudent::getName);

        students.sort(byScore);

        System.out.println(
                "by score = " + students);

        // 第二種排序器
        // 依姓名長度升冪
        // 長度相同再依字典順序
        Comparator<RankedStudent> byNameLength =

                Comparator
                        .comparingInt(
                                student ->
                                        student.getName().length())
                        .thenComparing(
                                RankedStudent::getName);

        students.sort(byNameLength);

        System.out.println(
                "by name length = "
                        + students);
    }
}