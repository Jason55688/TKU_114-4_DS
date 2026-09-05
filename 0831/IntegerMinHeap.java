import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class IntegerMinHeap {
    private List<Integer> heap = new ArrayList<>();

    public void add(int val) {
        heap.add(val);
        siftUp(heap.size() - 1);
    }

    public int peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        return heap.get(0);
    }

    public int removeMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        int min = heap.get(0);
        int lastVal = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, lastVal);
            siftDown(0);
        }
        return min;
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heap.get(index) < heap.get(parentIndex)) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    private void siftDown(int index) {
        int size = heap.size();
        while (true) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            int smallest = index;

            if (leftChild < size && heap.get(leftChild) < heap.get(smallest)) {
                smallest = leftChild;
            }
            if (rightChild < size && heap.get(rightChild) < heap.get(smallest)) {
                smallest = rightChild;
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
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public static void main(String[] args) {
        IntegerMinHeap minHeap = new IntegerMinHeap();
        int[] input = {45, 12, 85, 32, 89, 39, 69, 22};
        for (int v : input) {
            minHeap.add(v);
        }

        System.out.print("Removed order: ");
        int prev = Integer.MIN_VALUE;
        while (!minHeap.isEmpty()) {
            int current = minHeap.removeMin();
            System.out.print(current + " ");
            if (current < prev) {
                System.err.println("\nVerification failed: Not in non-decreasing order!");
                return;
            }
            prev = current;
        }
        System.out.println("\nVerification passed: Ordered correctly.");
    }
}