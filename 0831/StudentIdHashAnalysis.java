import java.util.ArrayList;
import java.util.List;

public class StudentIdHashAnalysis {

    public static void analyze(String[] studentIds, int bucketCount) {
        if (bucketCount <= 0) return;

        List<List<String>> buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        for (String id : studentIds) {
            int hashVal = id.hashCode();
            int index = ((hashVal % bucketCount) + bucketCount) % bucketCount;
            buckets.get(index).add(id);
        }

        int totalCollisions = 0;
        int maxChain = 0;
        int nonZeroBuckets = 0;

        for (int i = 0; i < bucketCount; i++) {
            int chainLen = buckets.get(i).size();
            if (chainLen > 1) {
                totalCollisions += (chainLen - 1);
            }
            if (chainLen > maxChain) {
                maxChain = chainLen;
            }
            if (chainLen > 0) {
                nonZeroBuckets++;
            }
        }

        // 平均 Chain 長度（總筆數 / 總桶數）
        double avgChain = (double) studentIds.length / bucketCount;

        System.out.println("========================================");
        System.out.println("Bucket Count: " + bucketCount);
        System.out.println("資料總筆數: " + studentIds.length);
        System.out.println("各 Bucket 筆數: ");
        for (int i = 0; i < bucketCount; i++) {
            System.out.println("  Bucket " + i + ": " + buckets.get(i).size() + " 筆 " + buckets.get(i));
        }
        System.out.println("總碰撞次數: " + totalCollisions);
        System.out.println("最長 Chain 長度: " + maxChain);
        System.out.printf("平均 Chain 長度: %.2f\n", avgChain);
    }

    public static void main(String[] args) {
        String[] ids = {
            "41026001", "41026002", "41026003", "41026015",
            "41026027", "41026038", "41026049", "41026050",
            "41026061", "41026072", "41026083", "41026094"
        };

        // 比較兩種不同的桶數設置
        analyze(ids, 5);
        analyze(ids, 11);
    }
}