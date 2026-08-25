import java.util.Arrays;

class CircularIntQueue {

    private final int[] data;
    private int front;
    private int rear;
    private int size;

    public CircularIntQueue(int capacity) {

        data = new int[Math.max(1, capacity)];
    }

    public boolean enqueue(int value) {

        if (isFull()) {
            return false;
        }

        data[rear] = value;
        rear = (rear + 1) % data.length;
        size++;

        return true;
    }

    public Integer dequeue() {

        if (isEmpty()) {
            return null;
        }

        int value = data[front];

        data[front] = 0;

        front = (front + 1) % data.length;
        size--;

        return value;
    }

    public Integer peek() {

        return isEmpty()
                ? null
                : data[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == data.length;
    }

    // 新增功能
    public void clear() {

        Arrays.fill(data, 0);

        front = 0;
        rear = 0;
        size = 0;
    }

    public void printState() {

        System.out.println(
                Arrays.toString(data)
                        + " front=" + front
                        + " rear=" + rear
                        + " size=" + size);
    }
}

public class CircularArrayQueueDemo {

    public static void main(String[] args) {

        CircularIntQueue queue =
                new CircularIntQueue(3);

        queue.enqueue(10);
        queue.enqueue(20);

        System.out.println("=== Initial ===");
        queue.printState();

        System.out.println(
                "dequeue = "
                        + queue.dequeue());

        queue.enqueue(30);
        queue.enqueue(40);

        System.out.println(
                "\n=== After Enqueue ===");

        queue.printState();

        System.out.println(
                "full = "
                        + queue.isFull());

        System.out.println(
                "enqueue 50 = "
                        + queue.enqueue(50));

        System.out.println(
                "peek = "
                        + queue.peek());

        // clear 測試
        System.out.println(
                "\n=== Clear Queue ===");

        queue.clear();

        queue.printState();

        System.out.println(
                "isEmpty = "
                        + queue.isEmpty());

        System.out.println(
                "dequeue after clear = "
                        + queue.dequeue());

        System.out.println(
                "peek after clear = "
                        + queue.peek());
    }
}