import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionChoiceReport {

    public static void main(String[] args) {

        // 1. 保留搜尋紀錄且允許重複
        List<String> searchHistory =
                new ArrayList<>();

        searchHistory.add("Java");
        searchHistory.add("Database");
        searchHistory.add("Java");

        System.out.println(
                "1. Search History");
        System.out.println(
                "Interface: List");
        System.out.println(
                "Implementation: ArrayList");
        System.out.println(
                "Result: " + searchHistory);

        System.out.println();

        // 2. 保存不重複會員編號
        Set<String> memberIds =
                new HashSet<>();

        memberIds.add("M001");
        memberIds.add("M002");
        memberIds.add("M001");

        System.out.println(
                "2. Member IDs");
        System.out.println(
                "Interface: Set");
        System.out.println(
                "Implementation: HashSet");
        System.out.println(
                "Result: " + memberIds);

        System.out.println();

        // 3. 以學號查詢成績
        Map<String, Integer> scores =
                new HashMap<>();

        scores.put("S101", 88);
        scores.put("S102", 95);
        scores.put("S103", 76);

        System.out.println(
                "3. Student Score Lookup");
        System.out.println(
                "Interface: Map");
        System.out.println(
                "Implementation: HashMap");
        System.out.println(
                "Find S102 = "
                        + scores.get("S102"));

        System.out.println();

        // 4. 依到達順序處理列印工作
        Deque<String> printQueue =
                new ArrayDeque<>();

        printQueue.offerLast("FileA");
        printQueue.offerLast("FileB");
        printQueue.offerLast("FileC");

        System.out.println(
                "4. Print Queue");
        System.out.println(
                "Interface: Deque");
        System.out.println(
                "Implementation: ArrayDeque");

        System.out.println(
                "Print = "
                        + printQueue.pollFirst());

        System.out.println(
                "Next = "
                        + printQueue.peekFirst());

        System.out.println();

        // 5. 復原最近操作
        Deque<String> undoStack =
                new ArrayDeque<>();

        undoStack.push("Delete File");
        undoStack.push("Insert Text");
        undoStack.push("Change Color");

        System.out.println(
                "5. Undo Stack");
        System.out.println(
                "Interface: Deque");
        System.out.println(
                "Implementation: ArrayDeque");

        System.out.println(
                "Undo = "
                        + undoStack.pop());

        System.out.println(
                "Current Top = "
                        + undoStack.peek());

        System.out.println();

        // 總結
        System.out.println(
                "=== Collection Choice Summary ===");

        System.out.println(
                "Search History -> List / ArrayList");

        System.out.println(
                "Unique Member IDs -> Set / HashSet");

        System.out.println(
                "Student Scores -> Map / HashMap");

        System.out.println(
                "Print Queue -> Deque / ArrayDeque");

        System.out.println(
                "Undo History -> Deque / ArrayDeque");
    }
}