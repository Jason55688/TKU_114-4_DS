class Order {
    int orderId;
    String customer;
    double amount;

    public Order(int orderId, String customer, double amount) {
        this.orderId = orderId;
        this.customer = customer;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Order[ID=" + orderId + ", Customer=" + customer + ", Amount=" + amount + "]";
    }
}

public class OrderBstSystem {
    private static class Node {
        Order order;
        Node left, right;

        Node(Order order) {
            this.order = order;
        }
    }

    private Node root;

    public void add(Order order) {
        root = addRec(root, order);
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

    public boolean updateAmount(int orderId, double newAmount) {
        Order o = find(orderId);
        if (o != null) {
            o.amount = newAmount;
            return true;
        }
        return false;
    }

    public void cancel(int orderId) {
        root = cancelRec(root, orderId);
    }

    private Node cancelRec(Node node, int orderId) {
        if (node == null) return null;
        if (orderId < node.order.orderId) {
            node.left = cancelRec(node.left, orderId);
        } else if (orderId > node.order.orderId) {
            node.right = cancelRec(node.right, orderId);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node succ = node.right;
            while (succ.left != null) succ = succ.left;
            node.order = succ.order;
            node.right = cancelRec(node.right, succ.order.orderId);
        }
        return node;
    }

    public void rangeReport(int lowId, int highId) {
        System.out.println("Range Report [" + lowId + " - " + highId + "]:");
        rangeRec(root, lowId, highId);
    }

    private void rangeRec(Node node, int low, int high) {
        if (node == null) return;
        if (node.order.orderId > low) rangeRec(node.left, low, high);
        if (node.order.orderId >= low && node.order.orderId <= high) System.out.println("  " + node.order);
        if (node.order.orderId < high) rangeRec(node.right, low, high);
    }

    public void summary() {
        int count = countNodes(root);
        double total = sumAmounts(root);
        System.out.println("--- Summary ---");
        System.out.println("Total Orders: " + count);
        System.out.println("Total Revenue: " + total);
    }

    private int countNodes(Node node) {
        if (node == null) return 0;
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    private double sumAmounts(Node node) {
        if (node == null) return 0.0;
        return node.order.amount + sumAmounts(node.left) + sumAmounts(node.right);
    }

    public static void main(String[] args) {
        OrderBstSystem sys = new OrderBstSystem();
        sys.add(new Order(1005, "Alice", 1200.0));
        sys.add(new Order(1002, "Bob", 450.0));
        sys.add(new Order(1008, "Charlie", 3200.0));
        sys.add(new Order(1001, "David", 800.0));

        sys.updateAmount(1002, 500.0);
        sys.rangeReport(1001, 1005);
        sys.summary();

        sys.cancel(1005);
        System.out.println("\nAfter cancel order 1005:");
        sys.summary();
    }
}