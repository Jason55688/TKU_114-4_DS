import java.util.*;

public class TopSellingProducts {

    public static class ProductInput {
        String id;
        int sales;

        public ProductInput(String id, int sales) {
            this.id = id;
            this.sales = sales;
        }
    }

    public static class Product {
        String id;
        int sales;

        public Product(String id, int sales) {
            this.id = id;
            this.sales = sales;
        }

        @Override
        public String toString() {
            return id + "(" + sales + ")";
        }
    }

    public static List<Product> getTopKProducts(List<ProductInput> rawInputs, int k) {
        if (rawInputs == null || k <= 0) {
            return Collections.emptyList();
        }

        // 1. 合併相同 ID 的銷售量
        Map<String, Integer> salesMap = new HashMap<>();
        for (ProductInput in : rawInputs) {
            salesMap.put(in.id, salesMap.getOrDefault(in.id, 0) + in.sales);
        }

        // 2. 維護大小為 K 的 Min Heap：銷量低者先淘汰；銷量相同則字典序大者先淘汰
        Comparator<Product> minHeapComparator = (a, b) -> {
            if (a.sales != b.sales) {
                return Integer.compare(a.sales, b.sales);
            }
            // 銷量相同時，字典序小者更優，故 Min Heap 優先淘汰字典序大的
            return b.id.compareTo(a.id);
        };

        PriorityQueue<Product> minHeap = new PriorityQueue<>(minHeapComparator);

        for (Map.Entry<String, Integer> entry : salesMap.entrySet()) {
            Product candidate = new Product(entry.getKey(), entry.getValue());
            if (minHeap.size() < k) {
                minHeap.offer(candidate);
            } else if (minHeapComparator.compare(candidate, minHeap.peek()) > 0) {
                minHeap.poll();
                minHeap.offer(candidate);
            }
        }

        // 3. 取出並依要求排序（銷量高到低；銷量相同字典序小到大）
        List<Product> result = new ArrayList<>(minHeap);
        result.sort((a, b) -> {
            if (b.sales != a.sales) {
                return Integer.compare(b.sales, a.sales);
            }
            return a.id.compareTo(b.id);
        });

        return result;
    }

    public static void main(String[] args) {
        List<ProductInput> list = Arrays.asList(
            new ProductInput("Apple", 10),
            new ProductInput("Banana", 30),
            new ProductInput("Apple", 20),      // Apple 累計 30
            new ProductInput("Orange", 50),
            new ProductInput("Watermelon", 5),
            new ProductInput("Cherry", 30),     // Cherry 30
            new ProductInput("Banana", 20)      // Banana 累計 50
        );

        int k = 3;
        List<Product> topK = getTopKProducts(list, k);
        System.out.println("Top-" + k + " 暢銷商品: " + topK);
    }
}