public class BookIsbnHashTable {

    private static class HashNode {
        String isbn;
        String title;
        HashNode next;

        HashNode(String isbn, String title) {
            this.isbn = isbn;
            this.title = title;
        }
    }

    private HashNode[] buckets;
    private int capacity;
    private int size;

    public BookIsbnHashTable() {
        this(11);
    }

    public BookIsbnHashTable(int capacity) {
        this.capacity = capacity;
        this.buckets = new HashNode[capacity];
        this.size = 0;
    }

    private int hash(String isbn) {
        if (isbn == null) return 0;
        return (isbn.hashCode() & 0x7fffffff) % capacity;
    }

    // 支援新增與更新
    public void put(String isbn, String title) {
        int idx = hash(isbn);
        HashNode cur = buckets[idx];

        while (cur != null) {
            if (cur.isbn.equals(isbn)) {
                cur.title = title; // 更新舊值
                return;
            }
            cur = cur.next;
        }

        // 新增節點
        HashNode newNode = new HashNode(isbn, title);
        newNode.next = buckets[idx];
        buckets[idx] = newNode;
        size++;
    }

    // 搜尋
    public String get(String isbn) {
        int idx = hash(isbn);
        HashNode cur = buckets[idx];
        while (cur != null) {
            if (cur.isbn.equals(isbn)) {
                return cur.title;
            }
            cur = cur.next;
        }
        return null;
    }

    // 刪除
    public boolean remove(String isbn) {
        int idx = hash(isbn);
        HashNode cur = buckets[idx];
        HashNode prev = null;

        while (cur != null) {
            if (cur.isbn.equals(isbn)) {
                if (prev == null) {
                    buckets[idx] = cur.next;
                } else {
                    prev.next = cur.next;
                }
                size--;
                return true;
            }
            prev = cur;
            cur = cur.next;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public double getLoadFactor() {
        return (double) size / capacity;
    }

    public void bucketReport() {
        System.out.println("===== ISBN Hash Table Report =====");
        System.out.printf("Capacity: %d | Size: %d | Load Factor: %.2f%n", capacity, size, getLoadFactor());
        for (int i = 0; i < capacity; i++) {
            System.out.print("Bucket [" + i + "]: ");
            HashNode cur = buckets[i];
            if (cur == null) {
                System.out.println("(empty)");
            } else {
                StringBuilder sb = new StringBuilder();
                while (cur != null) {
                    sb.append("[").append(cur.isbn).append(": ").append(cur.title).append("]");
                    if (cur.next != null) sb.append(" -> ");
                    cur = cur.next;
                }
                System.out.println(sb);
            }
        }
    }

    public static void main(String[] args) {
        BookIsbnHashTable table = new BookIsbnHashTable(5);

        table.put("978-0134685991", "Effective Java");
        table.put("978-0201633610", "Design Patterns");
        table.put("978-0132350884", "Clean Code");
        table.put("978-0134685991", "Effective Java (3rd Edition)"); // 測試更新

        table.bucketReport();

        System.out.println("\n搜尋 978-0132350884: " + table.get("978-0132350884"));
        table.remove("978-0201633610");
        System.out.println("\n刪除 Design Patterns 後:");
        table.bucketReport();
    }
}