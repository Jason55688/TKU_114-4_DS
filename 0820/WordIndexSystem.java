import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordIndexSystem {

    public static void main(String[] args) {

        String text =
                "Java is a programming language. "
                + "Java is widely used in programming courses. "
                + "Database courses and Java courses are important. "
                + "Programming skills help students learn Java.";

        // 轉小寫並移除標點符號
        text = text.toLowerCase()
                   .replaceAll("[^a-z ]", " ");

        String[] words = text.split("\\s+");

        Set<String> uniqueWords =
                new HashSet<>();

        Map<String, Integer> wordCount =
                new HashMap<>();

        for (String word : words) {

            if (word.isBlank()) {
                continue;
            }

            uniqueWords.add(word);

            wordCount.put(
                    word,
                    wordCount.getOrDefault(word, 0) + 1);
        }

        System.out.println("=== Unique Words ===");
        System.out.println(uniqueWords);

        System.out.println();

        System.out.println("=== Word Count ===");

        for (Map.Entry<String, Integer> entry
                : wordCount.entrySet()) {

            System.out.println(
                    entry.getKey()
                            + " -> "
                            + entry.getValue());
        }

        System.out.println();

        System.out.println(
                "=== Words Appearing At Least Twice ===");

        for (Map.Entry<String, Integer> entry
                : wordCount.entrySet()) {

            if (entry.getValue() >= 2) {

                System.out.println(
                        entry.getKey()
                                + " -> "
                                + entry.getValue());
            }
        }

        System.out.println();

        System.out.println(
                "Unique Word Count = "
                        + uniqueWords.size());
    }
}