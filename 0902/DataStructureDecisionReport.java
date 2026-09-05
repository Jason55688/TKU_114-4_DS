import java.util.ArrayList;
import java.util.List;

public class DataStructureDecisionReport {

    public static class RequirementDecision {
        int id;
        String scenario;
        String choice;
        String timeComplexity;
        String rationale;

        public RequirementDecision(int id, String scenario, String choice, String timeComplexity, String rationale) {
            this.id = id;
            this.scenario = scenario;
            this.choice = choice;
            this.timeComplexity = timeComplexity;
            this.rationale = rationale;
        }
    }

    public static List<RequirementDecision> generateDecisions() {
        List<RequirementDecision> list = new ArrayList<>();
        list.add(new RequirementDecision(1, "高頻隨機下標索引讀取", "ArrayList / Array", "O(1)", "記憶體連續配置，支援直接指標偏移位址計算"));
        list.add(new RequirementDecision(2, "頻繁頭尾雙向插入與刪除", "LinkedList / ArrayDeque", "O(1)", "雙向指標或環形陣列，無需平移後續大量記憶體資料"));
        list.add(new RequirementDecision(3, "唯一性檢查與快速存在性查詢", "HashSet", "O(1) avg", "哈希分桶直接定位元素桶位"));
        list.add(new RequirementDecision(4, "依鍵值鍵入並需保持自然遞增排序", "TreeMap (Red-Black Tree)", "O(log N)", "自平衡二元搜尋樹自動維持鍵值有序性"));
        list.add(new RequirementDecision(5, "隨時取得即時極值 (Top-1 / 動態調度)", "Heap (PriorityQueue)", "O(1) peek, O(log N) push/pop", "二元堆積結構保持 Root 為全域極值"));
        list.add(new RequirementDecision(6, "維護大規模稀疏網絡關聯", "Adjacency List", "O(V + E) space", "相較於矩陣的 O(V^2)，大幅降低空間浪費並利於遍歷鄰居"));
        list.add(new RequirementDecision(7, "兩節點間最短邊數路徑搜尋", "Queue (BFS)", "O(V + E)", "寬度優先搜尋確保逐層擴展，首度造訪即為最少步數"));
        list.add(new RequirementDecision(8, "巢狀結構解析 / 歷史回溯操作", "Stack", "O(1) push/pop", "後進先出 (LIFO) 特性完美對應呼叫堆疊與語法樹"));
        list.add(new RequirementDecision(9, "流水線排隊任務緩衝", "Queue (FIFO)", "O(1) offer/poll", "保證任務依照進場先後嚴格依序被消費者處理"));
        list.add(new RequirementDecision(10, "百萬級海量資料求 Top-K 最小", "固定大小 Max Heap", "O(N log K)", "以容量為 K 的大頂堆動態淘汰堆頂較大值，省記憶體"));
        list.add(new RequirementDecision(11, "密集圖任意兩節點是否存在邊的 O(1) 驗證", "Adjacency Matrix", "O(1) lookup", "以二維陣列 [u][v] 直接索引確認是否有邊"));
        list.add(new RequirementDecision(12, "先修相依關係之環路偵測與修課拓撲排序", "Directed Graph + DFS/Kahn", "O(V + E)", "使用入度陣列或有向深搜狀態染色偵測環狀依賴"));
        return list;
    }

    public static void printReport(List<RequirementDecision> decisions) {
        if (decisions == null || decisions.isEmpty()) {
            System.out.println("查無決策項目。");
            return;
        }
        System.out.printf("%-3s | %-24s | %-22s | %-16s | %s%n", "ID", "需求情境", "推薦資料結構", "主要 Big-O", "選用理由");
        System.out.println("----------------------------------------------------------------------------------------------------------");
        for (RequirementDecision d : decisions) {
            System.out.printf("%-3d | %-26s | %-22s | %-18s | %s%n", d.id, d.scenario, d.choice, d.timeComplexity, d.rationale);
        }
    }

    public static void main(String[] args) {
        printReport(generateDecisions());
        System.out.println("\n邊界案例 (空決策列表):");
        printReport(new ArrayList<>());
    }
}