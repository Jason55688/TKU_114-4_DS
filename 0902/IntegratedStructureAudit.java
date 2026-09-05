import java.util.List;

public class IntegratedStructureAudit {

    public enum DataStructureType {
        LIST, QUEUE, BST, HEAP, HASH_TABLE, GRAPH
    }

    public static class Scenario {
        String name;
        DataStructureType chosen;
        String coreOperationRequirement;

        public Scenario(String name, DataStructureType chosen, String coreOperationRequirement) {
            this.name = name;
            this.chosen = chosen;
            this.coreOperationRequirement = coreOperationRequirement;
        }
    }

    public static class AuditResult {
        String scenarioName;
        DataStructureType chosen;
        boolean isReasonable;
        String diagnosis;

        public AuditResult(String scenarioName, DataStructureType chosen, boolean isReasonable, String diagnosis) {
            this.scenarioName = scenarioName;
            this.chosen = chosen;
            this.isReasonable = isReasonable;
            this.diagnosis = diagnosis;
        }

        @Override
        public String toString() {
            return String.format("[%s] 選用: %-10s | 合理性: %-5s | 診斷: %s",
                    scenarioName, chosen, isReasonable ? "合理" : "不合理", diagnosis);
        }
    }

    public static AuditResult audit(Scenario s) {
        if (s == null) {
            return new AuditResult("Null Scenario", null, false, "情境定義為空");
        }

        boolean reasonable = false;
        String diagnosis = "";

        switch (s.chosen) {
            case HASH_TABLE:
                if (s.coreOperationRequirement.contains("隨機鍵值查詢") || s.coreOperationRequirement.contains("唯一性查重")) {
                    reasonable = true;
                    diagnosis = "符合最佳實踐，哈希表提供 O(1) 平均查找效率。";
                } else if (s.coreOperationRequirement.contains("範圍查詢") || s.coreOperationRequirement.contains("自然排序")) {
                    diagnosis = "選用不當。Hash Table 無法有效率進行範圍檢索 (Range Query) 與排序。建議改用 BST 或 TreeMap。";
                } else {
                    diagnosis = "需評估是否具備鍵值對應特性。";
                }
                break;

            case HEAP:
                if (s.coreOperationRequirement.contains("極值獲取") || s.coreOperationRequirement.contains("動態優先排程")) {
                    reasonable = true;
                    diagnosis = "符合設計理念，Heap 保證 O(1) 讀取極值與 O(log N) 動態更新。";
                } else if (s.coreOperationRequirement.contains("任意元素隨機搜尋")) {
                    diagnosis = "選用不當。Heap 內部並非完全有序，隨機搜尋複雜度達 O(N)。建議改用 Hash Table 或 BST。";
                }
                break;

            case QUEUE:
                if (s.coreOperationRequirement.contains("先進先出") || s.coreOperationRequirement.contains("廣度優先")) {
                    reasonable = true;
                    diagnosis = "符合 FIFO 排程規範。";
                } else {
                    diagnosis = "選用不當。不支援中間節點高效率隨意插入與讀取。";
                }
                break;

            case BST:
                if (s.coreOperationRequirement.contains("有序資料維護") || s.coreOperationRequirement.contains("區間範圍搜尋")) {
                    reasonable = true;
                    diagnosis = "BST 支援 O(log N) 的平衡檢索與中序遍歷保序。";
                } else {
                    diagnosis = "非排序檢索需求不建議承擔樹平衡管理成本。";
                }
                break;

            case GRAPH:
                if (s.coreOperationRequirement.contains("網絡關係") || s.coreOperationRequirement.contains("相依性檢查")) {
                    reasonable = true;
                    diagnosis = "符合複雜多對多實體關係建模需求。";
                } else {
                    diagnosis = "線性關係不宜過度設計為圖。";
                }
                break;

            case LIST:
                if (s.coreOperationRequirement.contains("依下標存取") || s.coreOperationRequirement.contains("保留插入順序")) {
                    reasonable = true;
                    diagnosis = "符合線性序列儲存語義。";
                } else {
                    diagnosis = "在大數據量高頻查詢下，線性搜尋 O(N) 性能貧乏。";
                }
                break;
        }

        return new AuditResult(s.name, s.chosen, reasonable, diagnosis);
    }

    public static void main(String[] args) {
        List<Scenario> testCases = List.of(
            new Scenario("大量帳號快速存在性比對", DataStructureType.HASH_TABLE, "唯一性查重"),
            new Scenario("會員生日區間範圍篩選", DataStructureType.HASH_TABLE, "範圍查詢"),
            new Scenario("緊急呼叫叫號中心", DataStructureType.HEAP, "動態優先排程"),
            new Scenario("資料庫商品依貨號查找", DataStructureType.HEAP, "任意元素隨機搜尋"),
            new Scenario("校園道路路徑分析", DataStructureType.GRAPH, "網絡關係")
        );

        System.out.println("===== 系統架構評估診斷報告 =====");
        for (Scenario sc : testCases) {
            System.out.println(audit(sc));
        }

        System.out.println("\n===== 邊界案例測試 (Null 情境) =====");
        System.out.println(audit(null));
    }
}