import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListImplementationLab {

    // 僅接受 List<Integer>
    public static void runLab(
            List<Integer> numbers,
            String type) {

        System.out.println("\n=== " + type + " ===");

        // 尾端新增
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(40);

        System.out.println(
                "After add: " + numbers);

        // 指定位置插入
        numbers.add(2, 99);

        System.out.println(
                "After insert index 2: "
                        + numbers);

        // 搜尋
        int searchValue = 30;

        int index =
                numbers.indexOf(searchValue);

        System.out.println(
                "Search " + searchValue
                        + " -> index = "
                        + index);

        // 刪除
        numbers.remove(Integer.valueOf(20));

        System.out.println(
                "After remove 20: "
                        + numbers);

        // 計算總和
        int total = 0;

        for (Integer value : numbers) {
            total += value;
        }

        System.out.println(
                "Sum = " + total);
    }

    public static void main(String[] args) {

        List<Integer> arrayList =
                new ArrayList<>();

        List<Integer> linkedList =
                new LinkedList<>();

        runLab(
                arrayList,
                "ArrayList");

        runLab(
                linkedList,
                "LinkedList");

        System.out.println(
                "\n=== Cost Analysis ===");

        System.out.println(
                "ArrayList: 以動態陣列實作，"
                        + "隨機存取速度快。");

        System.out.println(
                "ArrayList: 中間插入或刪除時，"
                        + "需要搬移後續元素。");

        System.out.println();

        System.out.println(
                "LinkedList: 以節點串接實作，"
                        + "中間插入或刪除較容易。");

        System.out.println(
                "LinkedList: 搜尋與索引存取時，"
                        + "需要逐節點走訪。");

        System.out.println();

        System.out.println(
                "兩者都實作 List 介面，"
                        + "因此 runLab() 不需要修改。");
    }
}