import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayMinHeap {
    private int[] data;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayMinHeap() {
        this.data = new int[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public void add(int val) {
        ensureCapacity();
        data[size] = val;
        siftUp(size);
        size++;
    }

    public int peek() {
        if (size == 0) {
            throw new NoSuchElementException("Heap 為空");
        }
        return data[0];
    }

    public int remove() {
        if (size == 0) {
            throw new NoSuchElementException("Heap 為空");
        }
        int min = data[0];
        data[0] = data[size - 1];
        size--;
        if (size > 0) {
            siftDown(0);
        }
        return min;
    }

    public int[] snapshot() {
        return Arrays.copyOf(data, size);
    }

    public int size() {
        return size;
    }

    private void ensureCapacity() {
        if (size == data.length) {
            data = Arrays.copyOf(data, data.length * 2);
        }
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (data[index] < data[parent]) {
                swap(index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }

    private void siftDown(int index) {
        while (true) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int smallest = index;

            if (left < size && data[left] < data[smallest]) {
                smallest = left;
            }
            if (right < size && data[right] < data[smallest]) {
                smallest = right;
            }

            if (smallest != index) {
                swap(index, smallest);
                index = smallest;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    public static void main(String[] args) {
        ArrayMinHeap heap = new ArrayMinHeap();
        int[] raw = {88, 37, 51, 19, 73, 11, 46, 92, 5, 64, 28, 83, 15, 34, 99, 2, 70, 58, 23, 41, 17, 30};

        System.out.println("開始插入 " + raw.length + " 筆資料:");
        for (int v : raw) {
            heap.add(v);
        }

        System.out.println("動態擴容完成後的 Heap Snapshot (長度 " + heap.size() + "):");
        System.out.println(Arrays.toString(heap.snapshot()));

        System.out.print("\n依序取出檢查有序性: ");
        int prev = Integer.MIN_VALUE;
        while (heap.size() > 0) {
            int cur = heap.remove();
            System.out.print(cur + " ");
            if (cur < prev) {
                System.err.println("\n排序驗證失敗！");
                return;
            }
            prev = cur;
        }
        System.out.println("\n驗證成功：所有元素均嚴格以非遞減順序輸出。");
    }
}