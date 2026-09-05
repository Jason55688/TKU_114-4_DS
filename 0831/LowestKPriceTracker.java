import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class LowestKPriceTracker {
    public static List<Integer> trackLowestK(List<Integer> prices, int k) {
        if (k <= 0 || prices == null) {
            return new ArrayList<>();
        }

        // 使用 Max Heap (大頂堆) 維護前 K 個最小的值
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (Integer price : prices) {
            if (price == null || price < 0) {
                continue; // 忽略 null 與負數
            }

            if (maxHeap.size() < k) {
                maxHeap.offer(price);
            } else if (price < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.offer(price);
            }
        }

        // 將結果依遞增排列輸出
        List<Integer> result = new ArrayList<>(maxHeap);
        Collections.sort(result);
        return result;
    }

    public static void main(String[] args) {
        List<Integer> testPrices = List.of(500, -20, 150, 80, 200, 30, 90, -5);
        int k = 4;
        System.out.println("Top-" + k + " lowest prices: " + trackLowestK(testPrices, k));
    }
}