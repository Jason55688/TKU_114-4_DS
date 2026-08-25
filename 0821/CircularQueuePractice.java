import java.util.Arrays;

class CircularQueue<T> {

    private Object[] data;
    private int front;
    private int rear;
    private int size;

    public CircularQueue(int capacity) {

        data = new Object[capacity];
    }

    public boolean enqueue(T value) {

        if (isFull()) {
            return false;
        }

        data[rear] = value;
        rear = (rear + 1) % data.length;
        size++;

        return true;
    }

    @SuppressWarnings("unchecked")
    public T dequeue() {

        if (isEmpty()) {
            return null;
        }

        T value = (T) data[front];

        data[front] = null;

        front = (front + 1) % data.length;
        size--;

        return value;
    }

    @SuppressWarnings("unchecked")
    public T peek() {

        if (isEmpty()) {
            return null;
        }

        return (T) data[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == data.length;
    }

    public void printState(String action) {

        System.out.println(
                action
                        + " -> "
                        + Arrays.toString(data)
                        + " front=" + front
                        + " rear=" + rear
                        + " size=" + size);
    }
}

public class CircularQueuePractice {

    public static void main(String[] args) {

        CircularQueue<String> queue =
                new CircularQueue<>(4);

        queue.enqueue("A");
        queue.printState("enqueue A");

        queue.enqueue("B");
        queue.printState("enqueue B");

        queue.enqueue("C");
        queue.printState("enqueue C");

        queue.dequeue();
        queue.printState("dequeue");

        queue.dequeue();
        queue.printState("dequeue");

        queue.enqueue("D");
        queue.printState("enqueue D");

        queue.enqueue("E");
        queue.printState("enqueue E");

        queue.enqueue("F");
        queue.printState("enqueue F");

        queue.dequeue();
        queue.printState("dequeue");

        queue.enqueue("G");
        queue.printState("enqueue G");

        System.out.println("\n=== FIFO Output ===");

        while (!queue.isEmpty()) {
            System.out.println(queue.dequeue());
        }
    }
}