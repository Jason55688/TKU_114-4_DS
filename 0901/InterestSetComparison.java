import java.util.HashSet;
import java.util.Set;

public class InterestSetComparison {

    public static <T> Set<T> union(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>(set1);
        result.addAll(set2);
        return result;
    }

    public static <T> Set<T> intersection(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>(set1);
        result.retainAll(set2);
        return result;
    }

    public static <T> Set<T> firstOnly(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>(set1);
        result.removeAll(set2);
        return result;
    }

    public static <T> Set<T> secondOnly(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>(set2);
        result.removeAll(set1);
        return result;
    }

    public static void main(String[] args) {
        Set<String> personA = Set.of("Reading", "Swimming", "Gaming", "Cooking");
        Set<String> personB = Set.of("Gaming", "Hiking", "Cooking", "Photography");

        System.out.println("Set A: " + personA);
        System.out.println("Set B: " + personB);
        System.out.println("Union (聯集): " + union(personA, personB));
        System.out.println("Intersection (交集/共同興趣): " + intersection(personA, personB));
        System.out.println("First-only (僅 A 有): " + firstOnly(personA, personB));
        System.out.println("Second-only (僅 B 有): " + secondOnly(personA, personB));
    }
}