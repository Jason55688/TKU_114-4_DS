public class ResizableStringMap {

    private static class Entry {
        String key;
        String value;
        Entry next;

        Entry(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private Entry[] table;
    private int size;
    private int capacity;
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;

    public ResizableStringMap() {
        this(7);
    }

    public ResizableStringMap(int initialCapacity) {
        this.capacity = Math.max(initialCapacity, 3);
        this.table = new Entry[this.capacity];
        this.size = 0;
    }

    private int hash(String key, int mod) {
        if (key == null) return 0;
        return (key.hashCode() & 0x7fffffff) % mod;
    }

    public void put(String key, String value) {
        if ((double) (size + 1) / capacity > LOAD_FACTOR_THRESHOLD) {
            resize();
        }

        int idx = hash(key, capacity);
        Entry cur = table[idx];

        while (cur != null) {
            if ((cur.key == null && key == null) || (cur.key != null && cur.key.equals(key))) {
                cur.value = value;
                return;
            }
            cur = cur.next;
        }

        Entry newEntry = new Entry(key, value);
        newEntry.next = table[idx];
        table[idx] = newEntry;
        size++;
    }

    public String get(String key) {
        int idx = hash(key, capacity);
        Entry cur = table[idx];
        while (cur != null) {
            if ((cur.key == null && key == null) || (cur.key != null && cur.key.equals(key))) {
                return cur.value;
            }
            cur = cur.next;
        }
        return null;
    }

    public int size() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    private void resize() {
        int newCapacity = capacity * 2 + 1;
        Entry[] newTable = new Entry[newCapacity];

        for (int i = 0; i < capacity; i++) {
            Entry cur = table[i];
            while (cur != null) {
                Entry next = cur.next;
                int newIdx = hash(cur.key, newCapacity);
                cur.next = newTable[newIdx];
                newTable[newIdx] = cur;
                cur = next;
            }
        }

        System.out.println("Triggered resize: " + capacity + " -> " + newCapacity);
        this.table = newTable;
        this.capacity = newCapacity;
    }

    public static void main(String[] args) {
        ResizableStringMap map = new ResizableStringMap(3);
        System.out.println("Initial capacity: " + map.getCapacity());

        map.put("k1", "v1");
        map.put("k2", "v2");
        map.put("k3", "v3"); // 達到 3/3 = 1.0 > 0.75，觸發擴容 -> 3 * 2 + 1 = 7
        map.put("k4", "v4");
        map.put("k5", "v5");
        map.put("k6", "v6"); // 達到 6/7 > 0.75，再次擴容 -> 7 * 2 + 1 = 15

        System.out.println("Current capacity: " + map.getCapacity());
        System.out.println("Get k3: " + map.get("k3"));
        System.out.println("Total size: " + map.size());
    }
}