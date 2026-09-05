import java.util.ArrayList;
import java.util.List;

public class CollisionBucketReport {

    public static void generateReport(int[] keys, int bucketCount) {
        if (bucketCount <= 0) {
            System.out.println("Bucket count must be greater than 0.");
            return;
        }

        List<List<Integer>> buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        if (keys != null && keys.length > 0) {
            for (int key : keys) {
                // 處理負數 key，確保 index 在 [0, bucketCount - 1]
                int index = ((key % bucketCount) + bucketCount) % bucketCount;
                buckets.get(index).add(key);
            }
        }

        int totalCollisions = 0;
        int maxChainLength = 0;

        for (int i = 0; i < bucketCount; i++) {
            List<Integer> chain = buckets.get(i);
            int size = chain.size();
            System.out.println("Bucket [" + i + "]: " + chain);

            if (size > 1) {
                totalCollisions += (size - 1);
            }
            if (size > maxChainLength) {
                maxChainLength = size;
            }
        }

        System.out.println("--- Summary ---");
        System.out.println("Total Collisions: " + totalCollisions);
        System.out.println("Max Chain Length: " + maxChainLength);
    }

    public static void main(String[] args) {
        int[] testKeys = {-15, 10, 25, -5, 30, 10, 0, 7};
        int numBuckets = 5;

        System.out.println("Running with sample keys:");
        generateReport(testKeys, numBuckets);

        System.out.println("\nRunning with empty input:");
        generateReport(new int[0], numBuckets);
    }
}