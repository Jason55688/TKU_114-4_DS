import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class StoreProduct implements Comparable<StoreProduct> {

    private String id;
    private String name;
    private double price;
    private int stock;

    public StoreProduct(
            String id,
            String name,
            double price,
            int stock) {

        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    // Natural Order：依 id 升冪
    @Override
    public int compareTo(StoreProduct other) {
        return this.id.compareTo(other.id);
    }

    @Override
    public String toString() {
        return id
                + " | "
                + name
                + " | Price="
                + price
                + " | Stock="
                + stock;
    }
}

public class ProductComparatorPractice {

    public static void main(String[] args) {

        List<StoreProduct> products =
                new ArrayList<>();

        products.add(
                new StoreProduct(
                        "P005",
                        "Keyboard",
                        890,
                        20));

        products.add(
                new StoreProduct(
                        "P003",
                        "Mouse",
                        490,
                        50));

        products.add(
                new StoreProduct(
                        "P001",
                        "Monitor",
                        5200,
                        10));

        products.add(
                new StoreProduct(
                        "P004",
                        "Speaker",
                        490,
                        15));

        products.add(
                new StoreProduct(
                        "P002",
                        "Webcam",
                        890,
                        50));

        System.out.println("=== Original ===");
        System.out.println(products);

        // Natural Order
        List<StoreProduct> byId =
                new ArrayList<>(products);

        byId.sort(null);

        System.out.println("\n=== By ID ===");
        System.out.println(byId);

        // Comparator 1
        List<StoreProduct> byPrice =
                new ArrayList<>(products);

        Comparator<StoreProduct> priceComparator =

                Comparator
                        .comparingDouble(
                                StoreProduct::getPrice)
                        .thenComparing(
                                StoreProduct::getName);

        byPrice.sort(priceComparator);

        System.out.println("\n=== By Price ===");
        System.out.println(byPrice);

        // Comparator 2
        List<StoreProduct> byStock =
                new ArrayList<>(products);

        Comparator<StoreProduct> stockComparator =

                Comparator
                        .comparingInt(
                                StoreProduct::getStock)
                        .reversed()
                        .thenComparing(
                                StoreProduct::getId);

        byStock.sort(stockComparator);

        System.out.println("\n=== By Stock ===");
        System.out.println(byStock);
    }
}