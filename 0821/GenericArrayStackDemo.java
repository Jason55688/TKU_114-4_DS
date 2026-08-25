public class GenericArrayStackDemo {

    public static void main(String[] args) {

        System.out.println("=== String Stack ===");

        ArrayStack<String> names =
                new ArrayStack<>(3);

        names.push("Amy");
        names.push("Ben");
        names.push("Cara");

        System.out.println(
                "isFull = "
                        + names.isFull());

        System.out.println(
                "peek = "
                        + names.peek());

        System.out.println(
                "pop = "
                        + names.pop());

        System.out.println(
                "size = "
                        + names.size());

        System.out.println();

        System.out.println("=== Integer Stack ===");

        ArrayStack<Integer> numbers =
                new ArrayStack<>(5);

        numbers.push(10);
        numbers.push(20);
        numbers.push(30);

        System.out.println(
                "peek = "
                        + numbers.peek());

        System.out.println(
                "pop = "
                        + numbers.pop());

        System.out.println(
                "pop = "
                        + numbers.pop());

        System.out.println(
                "size = "
                        + numbers.size());

        System.out.println();

        System.out.println("=== Empty Test ===");

        ArrayStack<String> empty =
                new ArrayStack<>(2);

        System.out.println(
                "pop = "
                        + empty.pop());

        System.out.println(
                "peek = "
                        + empty.peek());

        System.out.println(
                "isEmpty = "
                        + empty.isEmpty());
    }
}

class ArrayStack<T> {

    private Object[] data;
    private int top;

    public ArrayStack(int capacity) {

        data = new Object[Math.max(1, capacity)];
        top = 0;
    }

    public boolean push(T value) {

        if (isFull()) {
            return false;
        }

        data[top] = value;
        top++;

        return true;
    }

    @SuppressWarnings("unchecked")
    public T pop() {

        if (isEmpty()) {
            return null;
        }

        top--;

        T value = (T) data[top];

        data[top] = null;

        return value;
    }

    @SuppressWarnings("unchecked")
    public T peek() {

        if (isEmpty()) {
            return null;
        }

        return (T) data[top - 1];
    }

    public int size() {
        return top;
    }

    public boolean isEmpty() {
        return top == 0;
    }

    public boolean isFull() {
        return top == data.length;
    }
}