import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class EnrollmentCleanup {

    public static void main(String[] args) {

        List<String> names = new ArrayList<>();

        names.add("Amy");
        names.add("");
        names.add("Ben");
        names.add(null);
        names.add("Amy");
        names.add("Cara");
        names.add("  ");
        names.add("Ben");
        names.add("David");
        names.add(null);

        System.out.println("=== 清理前 ===");
        System.out.println(names);

        // 找出重複姓名
        Set<String> uniqueNames = new HashSet<>();
        Set<String> duplicateNames = new HashSet<>();

        for (String name : names) {

            if (name == null
                    || name.trim().isEmpty()) {
                continue;
            }

            if (!uniqueNames.add(name)) {
                duplicateNames.add(name);
            }
        }

        // 使用 Iterator 移除不合法資料
        Iterator<String> iterator = names.iterator();

        while (iterator.hasNext()) {

            String name = iterator.next();

            if (name == null
                    || name.trim().isEmpty()) {

                iterator.remove();
            }
        }

        System.out.println("\n=== 清理後 ===");
        System.out.println(names);

        System.out.println("\n=== 重複名單 ===");

        if (duplicateNames.isEmpty()) {

            System.out.println("沒有重複資料");

        } else {

            for (String name : duplicateNames) {
                System.out.println(name);
            }
        }

        System.out.println("\n=== 統計報告 ===");

        System.out.println(
                "有效資料數量："
                        + names.size());

        System.out.println(
                "重複姓名數量："
                        + duplicateNames.size());

        System.out.println(
                "不重複姓名數量："
                        + uniqueNames.size());
    }
}