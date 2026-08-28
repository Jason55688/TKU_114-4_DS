import java.util.ArrayList;
import java.util.List;

public class CompleteBstTestSuite {

    private static class Node {
        int val;
        Node left, right;
        Node(int val) { this.val = val; }
    }

    private static class SimpleBST {
        Node root;
        int size = 0;

        boolean add(int val) {
            if (contains(val)) return false;
            root = addRec(root, val);
            size++;
            return true;
        }

        private Node addRec(Node node, int val) {
            if (node == null) return new Node(val);
            if (val < node.val) node.left = addRec(node.left, val);
            else node.right = addRec(node.right, val);
            return node;
        }

        boolean contains(int val) {
            Node curr = root;
            while (curr != null) {
                if (val == curr.val) return true;
                else if (val < curr.val) curr = curr.left;
                else curr = curr.right;
            }
            return false;
        }

        boolean remove(int val) {
            if (!contains(val)) return false;
            root = removeRec(root, val);
            size--;
            return true;
        }

        private Node removeRec(Node node, int val) {
            if (node == null) return null;
            if (val < node.val) {
                node.left = removeRec(node.left, val);
            } else if (val > node.val) {
                node.right = removeRec(node.right, val);
            } else {
                if (node.left == null) return node.right;
                if (node.right == null) return node.left;
                Node succ = node.right;
                while (succ.left != null) succ = succ.left;
                node.val = succ.val;
                node.right = removeRec(node.right, succ.val);
            }
            return node;
        }

        List<Integer> range(int low, int high) {
            List<Integer> res = new ArrayList<>();
            if (low > high) return res;
            rangeHelper(root, low, high, res);
            return res;
        }

        private void rangeHelper(Node node, int low, int high, List<Integer> res) {
            if (node == null) return;
            if (node.val > low) rangeHelper(node.left, low, high, res);
            if (node.val >= low && node.val <= high) res.add(node.val);
            if (node.val < high) rangeHelper(node.right, low, high, res);
        }

        boolean isValid() {
            return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
        }

        private boolean validate(Node node, long min, long max) {
            if (node == null) return true;
            if (node.val <= min || node.val >= max) return false;
            return validate(node.left, min, node.val) && validate(node.right, node.val, max);
        }
    }

    private static int passCount = 0;
    private static int failCount = 0;

    public static void check(String description, boolean condition) {
        if (condition) {
            System.out.println("[PASS] " + description);
            passCount++;
        } else {
            System.out.println("[FAIL] " + description);
            failCount++;
        }
    }

    public static void main(String[] args) {
        SimpleBST bst = new SimpleBST();

        check("01. Initial tree size is 0", bst.size == 0);
        check("02. Empty tree contains returns false", !bst.contains(10));
        check("03. Empty tree remove returns false", !bst.remove(10));
        check("04. Empty tree is valid BST", bst.isValid());
        check("05. Empty tree range query returns empty list", bst.range(1, 100).isEmpty());

        check("06. Add root node 50 returns true", bst.add(50));
        check("07. Root node size is 1", bst.size == 1);
        check("08. Duplicate add 50 returns false", !bst.add(50));
        check("09. Duplicate add does not increase size", bst.size == 1);

        bst.add(30);
        bst.add(70);
        bst.add(20);
        bst.add(40);
        bst.add(60);
        bst.add(80);
        check("10. Insert 6 elements total size is 7", bst.size == 7);
        check("11. Tree contains leaf 20", bst.contains(20));
        check("12. Tree does not contain missing 99", !bst.contains(99));
        check("13. Multi-node tree is valid BST", bst.isValid());

        check("14. Remove leaf 20 returns true", bst.remove(20));
        check("15. Leaf 20 is no longer present", !bst.contains(20));
        check("16. Size reduced to 6 after leaf removal", bst.size == 6);

        bst.remove(60);
        check("17. Remove node with one child (80) successfully", bst.contains(70) && !bst.contains(60));

        check("18. Remove root with two children (50)", bst.remove(50));
        check("19. Tree maintains valid BST after root deletion", bst.isValid());

        List<Integer> rangeRes = bst.range(30, 70);
        check("20. Range [30, 70] contains exact elements", rangeRes.contains(30) && rangeRes.contains(40) && rangeRes.contains(70) && !rangeRes.contains(80));

        check("21. Range with low > high returns empty", bst.range(100, 10).isEmpty());

        System.out.println("\nTotal Tests: " + (passCount + failCount) + " | Passed: " + passCount + " | Failed: " + failCount);
    }
}