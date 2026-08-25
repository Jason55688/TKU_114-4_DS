import java.util.Arrays;

class DynamicArray<T> {

    private Object[] data;
    private int size;

    public DynamicArray() {
        data = new Object[2];
    }

    public void add(T value) {

        ensureCapacity();

        data[size] = value;
        size++;
    }

    public void add(int index, T value) {

        checkInsertIndex(index);

        ensureCapacity();

        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }

        data[index] = value;
        size++;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {

        checkIndex(index);

        return (T) data[index];
    }

    @SuppressWarnings("unchecked")
    public T set(int index, T value) {

        checkIndex(index);

        T old = (T) data[index];

        data[index] = value;

        return old;
    }

    @SuppressWarnings("unchecked")
    public T remove(int index) {

        checkIndex(index);

        T removed = (T) data[index];

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        size--;

        data[size] = null;

        return removed;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return data.length;
    }

    private void ensureCapacity() {

        if (size == data.length) {

            data = Arrays.copyOf(
                    data,
                    data.length * 2);

            System.out.println(
                    "resize -> " + data.length);
        }
    }

    private void checkIndex(int index) {

        if (index < 0 || index >= size) {

            throw new IndexOutOfBoundsException(
                    "index=" + index);
        }
    }

    private void checkInsertIndex(int index) {

        if (index < 0 || index > size) {

            throw new IndexOutOfBoundsException(
                    "index=" + index);
        }
    }

    @Override
    public String toString() {

        return Arrays.toString(
                Arrays.copyOf(data, size));
    }
}

public class DynamicArrayPractice {

    public static void main(String[] args) {

        System.out.println("=== String Test ===");

        DynamicArray<String> names =
                new DynamicArray<>();

        names.add("Amy");
        names.add("Ben");
        names.add("Cara");

        System.out.println(names);

        names.add(1, "David");

        System.out.println(
                "After Insert = " + names);

        System.out.println(
                "Get(2) = " + names.get(2));

        System.out.println(
                "Set(0) old = "
                        + names.set(0, "Alice"));

        System.out.println(names);

        System.out.println(
                "Remove(1) = "
                        + names.remove(1));

        System.out.println(names);

        System.out.println(
                "Size = " + names.size());

        System.out.println(
                "Capacity = "
                        + names.capacity());

        System.out.println();

        System.out.println("=== Integer Test ===");

        DynamicArray<Integer> numbers =
                new DynamicArray<>();

        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(40);

        System.out.println(numbers);

        numbers.add(2, 99);

        System.out.println(numbers);

        System.out.println(
          