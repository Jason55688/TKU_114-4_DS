class Product {
    int id;
    String name;
    int stock;

    public Product(int id, String name, int stock) {
        this.id = id;
        this.name = name;
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Product[ID=" + id + ", Name=" + name + ", Stock=" + stock + "]";
    }
}

public class ProductInventoryBst {
    private static class Node {
        Product data;
        Node left, right;

        Node(Product data) {
            this.data = data;
        }
    }

    private Node root;

    public void insert(Product product) {
        root = insertRec(root, product);
    }

    private Node insertRec(Node node, Product product) {
        if (node == null) return new Node(product);
        if (product.id < node.data.id) node.left = insertRec(node.left, product);
        else if (product.id > node.data.id) node.right = insertRec(node.right, product);
        return node;
    }

    public Product search(int id) {
        Node curr = root;
        while (curr != null) {
            if (id == curr.data.id) return curr.data;
            else if (id < curr.data.id) curr = curr.left;
            else curr = curr.right;
        }
        return null;
    }

    public boolean restock(int id, int amount) {
        Product p = search(id);
        if (p != null) {
            p.stock += amount;
            return true;
        }
        return false;
    }

    public boolean deductStock(int id, int amount) {
        Product p = search(id);
        if (p != null && p.stock >= amount) {
            p.stock -= amount;
            return true;
        }
        return false;
    }

    public void delete(int id) {
        root = deleteRec(root, id);
    }

    private Node deleteRec(Node node, int id) {
        if (node == null) return null;
        if (id < node.data.id) {
            node.left = deleteRec(node.left, id);
        } else if (id > node.data.id) {
            node.right = deleteRec(node.right, id);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node succ = getMin(node.right);
            node.data = succ.data;
            node.right = deleteRec(node.right, succ.data.id);
        }
        return node;
    }

    private Node getMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    public void inorderReport() {
        System.out.println("--- Inventory Report ---");
        inorderRec(root);
    }

    private void inorderRec(Node node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.println(node.data);
            inorderRec(node.right);
        }
    }

    public static void main(String[] args) {
        ProductInventoryBst inventory = new ProductInventoryBst();
        inventory.insert(new Product(201, "Keyboard", 10));
        inventory.insert(new Product(105, "Mouse", 25));
        inventory.insert(new Product(305, "Monitor", 5));

        inventory.restock(105, 10);
        inventory.deductStock(201, 3);
        inventory.inorderReport();

        inventory.delete(105);
        System.out.println("\nAfter deletion:");
        inventory.inorderReport();
    }
}
