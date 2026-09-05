import java.util.List;

public class HeapPropertyValidator {

    public static boolean isMinHeap(List<Integer> list) {
        if (list == null) return false;
        if (list.size() <= 1) return true;

        int n = list.size();
        // 遍歷所有非葉節點
        for (int i = 0; i <= (n - 2) / 2; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < n && list.get(i) > list.get(left)) {
                return false;
            }
            if (right < n && list.get(i) > list.get(right)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isMaxHeap(List<Integer> list) {
        if (list == null) return false;
        if (list.size() <= 1) return true;

        int n = list.size();
        for (int i = 0; i <= (n - 2) / 2; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < n && list.get(i) < list.get(left)) {
                return false;
            }
            if (right < n && list.get(i) < list.get(right)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("null test: " + isMinHeap(null)); // false
        System.out.println("empty test: " + isMinHeap(List.of())); // true
        System.out.println("single test: " + isMinHeap(List.of(42))); // true

        List<Integer> minHeapList = List.of(10, 15, 20, 17, 25);
        List<Integer> notHeapList = List.of(10, 30, 20, 15, 25);

        System.out.println("isMinHeap: " + isMinHeap(minHeapList)); // true
        System.out.println("isMinHeap (invalid): " + isMinHeap(notHeapList)); // false
    }
}