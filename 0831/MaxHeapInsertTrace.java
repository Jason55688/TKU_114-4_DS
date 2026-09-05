import java.util.ArrayList;
import java.util.List;

public class MaxHeapInsertTrace {
    private List<Integer> heap = new ArrayList<>();

    public void add(int val) {
        heap.add(val);
        siftUp(heap.size() - 1);
        System.out.println("After adding " + val + ": " + snapshot());
    }

    public int peekMax() {
        if (heap.isEmpty()) {
            throw new java.util.NoSuchElementException("Heap is empty");
        }
        return heap.get(0);
    }

    public List<Integer> snapshot() {
        return new ArrayList<>(heap);
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heap.get(index) > heap.get(parentIndex)) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public static void main(String[] args) {
        MaxHeapInsertTrace maxHeap = new MaxHeapInsertTrace();
        int[] data = {25, 40, 10, 50, 30, 50};

        for (int num : data) {
            maxHeap.add(num);
        }

        System.out.println("Root is: " + maxHeap.peekMax());
    }
}