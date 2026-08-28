import java.util.ArrayList;
import java.util.List;

public class BstOperationAudit {

    private static class Node {
        int val;
        Node left, right;

        Node(int val) {
            this.val = val;
        }
    }

    private Node root;

    public boolean add(int val) {
        if (contains(root, val)) {
            printAudit("ADD " + val, false);
            return false;
        }
        root = insertRec(root, val);
        printAudit("ADD " + val, true);
        return true;
    }

    private Node insertRec(Node node, int val) {
        if (node == null) return new Node(val);
        if (val < node.val) node.left = insertRec(node.left, val);
        else if (val > node.val) node.right = insertRec(node.right, val);
        return node;
    }

    public boolean remove(int val) {
        if (!contains(root, val)) {
            printAudit("REMOVE " + val, false);
            return false;
        }
        root = deleteRec(root, val);
        printAudit("REMOVE " + val, true);
        return true;
    }

    private Node deleteRec(Node node, int val) {
        if (node == null) return null;
        if (val < node.val) {
            node.left = deleteRec(node.left, val);
        } else if (val > node.val) {
            node.right = deleteRec(node.right, val);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node succ = node.right;
            while (succ.left != null) succ = succ.left;
            node.val = succ.val;
            node.right = deleteRec(node.right, succ.val);
        }
        return node;
    }

    private boolean contains(Node node, int val) {
        if (node == null) return false;
        if (val == node.val) return true;
        return val < node.val ? contains(node.left, val) : contains(node.right, val);
    }

    public int size() {
        return sizeRec(root);
    }

    private int sizeRec(Node node) {
        if (node == null) return 0;
        return 1 + sizeRec(node.left) + sizeRec(node.right);
    }

    public int height() {
        return heightRec(root);
    }

    private int heightRec(Node node) {
        if (node == null) return 0;
        return 1 + Math.max(heightRec(node.left), heightRec(node.right));
    }

    public boolean isValid() {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validate(Node node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return validate(node.left, min, node.val) && validate(node.right, node.val, max);
    }

    public List<Integer> inorder() {
        List<Integer> list = new ArrayList<>();
        inorderRec(root, list);
        return list;
    }

    private void inorderRec(Node node, List<Integer> list) {
        if (node != null) {
            inorderRec(node.left, list);
            list.add(node.val);
            inorderRec(node.right, list);
        }
    }

    private void printAudit(String op, boolean result) {
        System.out.printf("OP: %-12s | Result: %-5s | Inorder: %-25s | Size: %d | Height: %d | Valid: %s%n",
                op, result, inorder().toString(), size(), height(), isValid());
    }

    public static void main(String[] args) {
        BstOperationAudit tree = new BstOperationAudit();
        int[] initVals = {50, 30, 70, 20, 40, 60, 80};
        for (int v : initVals) tree.add(v);

        tree.add(30);
        tree.remove(99);
        tree.remove(20);
        tree.remove(30);
        tree.remove(50);
    }
}