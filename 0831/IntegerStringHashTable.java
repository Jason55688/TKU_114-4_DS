import java.util.ArrayList;
import java.util.List;

public class IntegerStringHashTable {

    private static class HashNode {
        int key;
        String value;
        HashNode next;

        public HashNode(int key, String value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private HashNode[] buckets;
    private int capacity;
    private int size;

    public IntegerStringHashTable() {
        this(10);
    }

    public IntegerStringHashTable(int capacity) {
        this.capacity = capacity;
        this.buckets = new HashNode[capacity];
        this.size = 0;
    }

    private int hash(int key) {
        return ((key % capacity) + capacity) % capacity;
    }

    public void put(int key, String value) {
        int index = hash(key);
        HashNode head = buckets[index];

        while (head != null) {
            if (head.key == key) {
                head.value = value; // 覆蓋舊值，size 不增加
                return;
            }
            head = head.next;
        }

        HashNode newNode = new HashNode(key, value);
        newNode.next = buckets[index];
        buckets[index] = newNode;
        size++;
    }

    public String get(int key) {
        int index = hash(key);
        HashNode head = buckets[index];
        while (head != null) {
            if (head.key == key) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    public boolean containsKey(int key) {
        return get(key) != null;
    }

    public String remove(int key) {
        int index = hash(key);
        HashNode current = buckets[index];
        HashNode prev = null;

        while (current != null) {
            if (current.key == key) {
                if (prev == null) {
                    buckets[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return current.value;
            }
            prev = current;
            current = current.next;
        }
        return null;
    }

    public int size() {
        return size;
    }

    public void bucketReport() {
        System.out.println("===== Bucket 碰撞分佈報告 (Size: " + size + ") =====");
        for (int i = 0; i < capacity; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("Bucket [").append(i).append("]: ");
            HashNode cur = buckets[i];
            List<String> entries = new ArrayList<>();
            while (cur != null) {
                entries.add("(" + cur.key + " -> " + cur.value + ")");
                cur = cur.next;
            }
            sb.append(String.join(" -> ", entries));
            System.out.println(sb.toString());
        }
    }

    public static void main(String[] args) {
        IntegerStringHashTable map = new IntegerStringHashTable(5);

        map.put(1, "A");
        map.put(6, "B"); // 衝突至 index 1
        map.put(11, "C"); // 衝突至 index 1
        map.put(-4, "D"); // -4 % 5 = 1，測試負數
        map.put(2, "E");

        System.out.println("更新前 size: " + map.size()); // 5
        map.put(6, "B_Updated"); // 測試更新重複 key
        System.out.println("更新後 size: " + map.size()); // 依然為 5

        System.out.println("get(6): " + map.get(6));
        System.out.println("containsKey(-4): " + map.containsKey(-4));

        map.remove(1);
        System.out.println("移除 key 1 之後:");
        map.bucketReport();
    }
}