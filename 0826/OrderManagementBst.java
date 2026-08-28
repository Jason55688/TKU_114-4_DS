import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderManagementBst {

    public static class Order {
        int orderId;
        String customer;
        double amount;
        String status;

        public Order(int orderId, String customer, double amount, String status) {
            if (amount < 0) {
                throw new IllegalArgumentException("Amount cannot be negative");
            }
            this.orderId = orderId;
            this.customer = customer;
            this.amount = amount;
            this.status = status;
        }

        @Override
        public String toString() {
            return "Order[ID=" + orderId + ", Customer=" + customer + ", Amount=" + amount + ", Status=" + status + "]";
        }
    }

    private static class Node {
        Order order;
        Node left, right;
        Node(Order order) { this.order = order; }
    }

    private Node root;

    public boolean add(Order order) {
        if (order == null || find(order.orderId) != null) {
            return false;
        }
        root = addRec(root, order);
        return true;
    }

    private Node addRec(Node node, Order order) {
        if (node == null) return new Node(order);
        if (order.orderId < node.order.orderId) node.left = addRec(node.left, order);
        else if (order.orderId > node.order.orderId) node.right = addRec(node.right, order);
        return node;
    }

    public Order find(int orderId) {
        Node curr = root;
        while (curr != null) {
            if (orderId == curr.order.orderId) return curr.order;
            else if (orderId < curr.order.orderId) curr = curr.left;
            else curr = curr.right;
        }
        return null;
    }

    public boolean updateStatus(int orderId, String newStatus) {
        Order o = find(orderId);
        if (o != null) {
            o.status = newStatus;
            return true;
        }
        return false;
    }

    public boolean cancel(int orderId) {
        return updateStatus(orderId, "CANCELLED");
    }

    public boolean remove(int orderId) {
        Order o = find(orderId);
        if (o == null || !"CANCELLED".equalsIgnoreCase(o.status)) {
            return false;
        }
        root = removeRec(root, orderId);
        return true;
    }

    private Node removeRec(Node node, int orderId) {
        if (node == null) return null;
        if (orderId < node.order.orderId) {
            node.left = removeRec(node.left, orderId);
        } else if (orderId > node.order.orderId) {
            node.right = removeRec(node.right, orderId);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node succ = node.right;
            while (succ.left != null) succ = succ.left;
            node.order = succ.order;
            node.right = removeRec(node.right, succ.order.orderId);
        }
        return node;
    }

    public List<Order> idRangeReport(int lowId, int highId) {
        if (lowId > highId) return Collections.emptyList();
        List<Order> list = new ArrayList<>();
        rangeRec(root, lowId, highId, list);
        return list;
    }

    private void rangeRec(Node node, int low, int high, List<Order> list) {
        if (node == null) return;
        if (node.order.orderId > low) rangeRec(node.left, low, high, list);
        if (node.order.orderId >= low && node.order.orderId <= high) list.add(node.order);
        if (node.order.orderId < high) rangeRec(node.right, low, high, list);
    }

    public double totalAmount() {
        return totalAmountRec(root);
    }

    private double totalAmountRec(Node node) {
        if (node == null) return 0.0;
        return node.order.amount + totalAmountRec(node.left) + totalAmountRec(node.right);
    }

    public static void main(String[] args) {
        OrderManagementBst manager = new OrderManagementBst();
        manager.add(new Order(101, "Alice", 1500.0, "PENDING"));
        manager.add(new Order(105, "Bob", 3200.0, "PAID"));
        manager.add(new Order(103, "Charlie", 800.0, "SHIPPED"));

        System.out.println("Total Amount: " + manager.totalAmount());

        System.out.println("Remove non-cancelled order 105: " + manager.remove(105));
        manager.cancel(105);
        System.out.println("Remove cancelled order 105: " + manager.remove(105));

        System.out.println("\nRange Report [100, 104]:");
        for (Order o : manager.idRangeReport(100, 104)) {
            System.out.println(o);
        }
    }
}