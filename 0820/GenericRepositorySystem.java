import java.util.ArrayList;

class Repository<T> {

    private ArrayList<T> data;

    public Repository() {
        data = new ArrayList<>();
    }

    // 新增資料
    public void add(T item) {
        data.add(item);
    }

    // 依索引取得資料
    public T get(int index) {

        if (index < 0 || index >= data.size()) {
            return null;
        }

        return data.get(index);
    }

    // 移除資料
    public boolean remove(T item) {
        return data.remove(item);
    }

    // 資料筆數
    public int size() {
        return data.size();
    }

    // 顯示全部資料
    public void display() {

        for (T item : data) {
            System.out.println(item);
        }
    }
}

class Product {

    private String id;
    private String name;
    private double price;

    public Product(
            String id,
            String name,
            double price) {

        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {

        return "Product{id='"
                + id
                + "', name='"
                + name
                + "', price="
                + price
                + "}";
    }
}

public class GenericRepositorySystem {

    public static void main(String[] args) {

        // Repository<String> 測試
        Repository<String> names =
                new Repository<>();

        names.add("Amy");
        names.add("Ben");
        names.add("Cara");

        System.out.println(
                "=== String Repository ===");

        names.display();

        System.out.println(
                "\nFirst Item: "
                        + names.get(0));

        names.remove("Ben");

        System.out.println(
                "Size After Remove: "
                        + names.size());

        names.display();

        // Repository<Product> 測試
        Repository<Product> products =
                new Repository<>();

        products.add(
                new Product(
                        "P001",
                        "Keyboard",
                        890));

        products.add(
                new Product(
                        "P002",
                        "Mouse",
                        490));

        products.add(
                new Product(
                        "P003",
                        "Monitor",
                        5200));

        System.out.println(
                "\n=== Product Repository ===");

        products.display();

        System.out.println(
                "\nProduct Count: "
                        + products.size());

        System.out.println(
                "\nGet Product:");
        System.out.println(
                products.get(1));
    }
}