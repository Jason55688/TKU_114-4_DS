import java.util.Arrays;

class IntDynamicArray {

    private int[] data;
    private int size;

    public IntDynamicArray(int initialCapacity) {

        data = new int[Math.max(1, initialCapacity)];
    }

    public void add(int value) {

        ensureCapacity();

        data[size] = value;
        size++;
    }

    // 新增：指定位置插入
    public void add(int index, int value) {

        if (index < 0 || index > size) {

            throw new IndexOutOfBoundsException(
                    "index=" + index);
        }

        ensureCapacity();

        for (int i = size; i > index; i--) {

            data[i] = data[i - 1];
        }

        data[index] = value;
        size++;
    }

    public int get(int index) {

        checkIndex(index);

        return data[index];
    }

    public int remove(int index) {

        checkIndex(index);

        int removed = data[index];

        for (int i = index;
             i < size - 1;
             i++) {

            data[i] = data[i + 1];
        }

        size--;

        data[size] = 0;

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
                    "resize -> "
                            + data.length);
        }
    }

    private void checkIndex(int index) {

        if (index < 0 || index >= size) {

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

public class CustomDynamicArrayDemo {

    public static void main(String[] args) {

        IntDynamicArray values =
                new IntDynamicArray(2);

        values.add(10);
        values.add(20);
        values.add(30);

        System.out.println(
                "Original = " + values);

        // 插入開頭
        values.add(0, 5);

        System.out.println(
                "Add(0,5) = " + values);

        // 插入中間
        values.add(2, 15);

        System.out.println(
                "Add(2,15) = " + values);

        // 插入尾端
        values.add(values.size(), 40);

        System.out.println(
                "Add(end,40) = "
                        + values);

        System.out.println();

        System.out.println(
                "Removed = "
                        + values.remove(3));

        System.out.println(
                "After Remove = "
                        + values);

        System.out.println();

        System.out.println(
                "Size = "
                        + values.size());

        System.out.println(
                "Capacity = "
                        + values.capacity());

        // 非法 index 測試
        try {

            values.add(-1, 100);

        } catch (Exception e) {

            System.out.println(
                    "Exception: "
                            + e.getMessage());
        }
    }
}