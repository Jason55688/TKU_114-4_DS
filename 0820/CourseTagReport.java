import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CourseTagReport {

    public static void main(String[] args) {

        String[] tags = {
                "Java",
                "Database",
                "Java",
                "Tree",
                "Database",
                "Java",
                "Network",
                "Tree"
        };

        List<String> tagList =
                new ArrayList<>();

        Set<String> uniqueTags =
                new HashSet<>();

        Map<String, Integer> tagCount =
                new HashMap<>();

        for (String tag : tags) {

            // List：保留原始順序
            tagList.add(tag);

            // Set：保留不重複標籤
            uniqueTags.add(tag);

            // Map：統計次數
            tagCount.put(
                    tag,
                    tagCount.getOrDefault(tag, 0) + 1);
        }

        System.out.println("=== List ===");
        System.out.println(tagList);

        System.out.println();

        System.out.println("=== Set ===");
        System.out.println(uniqueTags);

        System.out.println();

        System.out.println("=== Map ===");

        for (Map.Entry<String, Integer> entry
                : tagCount.entrySet()) {

            System.out.println(
                    entry.getKey()
                            + " -> "
                            + entry.getValue());
        }

        System.out.println();

        System.out.println("=== 資料結構用途說明 ===");

        System.out.println(
                "List：保留輸入順序與重複資料");

        System.out.println(
                "Set：自動過濾重複標籤");

        System.out.println(
                "Map：記錄每個標籤出現次數");
    }
}