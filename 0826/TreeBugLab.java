import java.util.ArrayList;
import java.util.List;

public class TreeBugLab {

    public static class Node {
        int val;
        Node left, right;
        Node(int val) { this.val = val; }
    }

    public static boolean buggySearch(Node node, int target) {
        if (node == null) return false;
        if (node.val == target) return true;
        if (target < node.val) return buggySearch(node.right, target);
        return buggySearch(node.left, target);
    }

    public static boolean fixedSearch(Node node, int target) {
        if (node == null) return false;
        if (node.val == target) return true;
        if (target < node.val) return fixedSearch(node.left, target);
        return fixedSearch(node.right, target);
    }

    public static void buggyInorder(Node node, List<Integer> res) {
        if (node == null) return;
        res.add(node.val);
        buggyInorder(node.left, res);
        buggyInorder(node.right, res);
    }

    public static void fixedInorder(Node node, List<Integer> res) {
        if (node == null) return;
        fixedInorder(node.left, res);
        res.add(node.val);
        fixedInorder(node.right, res);
    }

    public static Node buggyDelete(Node node, int target) {
        if (node == null) return null;
        if (target < node.val) node.left = buggyDelete(node.left, target);
        else if (target > node.val) node.right = buggyDelete(node.right, target);
        else {
            return null;
        }
        return node;
    }

    public static Node fixedDelete(Node node, int target) {
        if (node == null) return null;
        if (target < node.val) node.left = fixedDelete(node.left, target);
        else if (target > node.val) node.right = fixedDelete(node.right, target);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node succ = node.right;
            while (succ.left != null) succ = succ.left;
            node.val = succ.val;
            node.right = fixedDelete(node.right, succ.val);
        }
        return node;
    }

    public static boolean buggyValidate(Node node) {
        if (node == null) return true;
        if (node.left != null && node.left.val >= node.val) return false;
        if (node.right != null && node.right.val <= node.val) return false;
        return buggyValidate(node.left) && buggyValidate(node.right);
    }

    public static boolean fixedValidate(Node node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return fixedValidate(node.left, min, node.val) && fixedValidate(node.right, node.val, max);
    }

    public static void main(String[] args) {
        Node bst = new Node(50);
        bst.left = new Node(30);
        bst.right = new Node(70);
        System.out.println("1. Search Bug Fail: " + buggySearch(bst, 30) + " | Fixed: " + fixedSearch(bst, 30));

        List<Integer> bList = new ArrayList<>(), fList = new ArrayList<>();
        buggyInorder(bst, bList);
        fixedInorder(bst, fList);
        System.out.println("2. Inorder Bug Fail: " + bList + " | Fixed: " + fList);

        Node delTree = new Node(50);
        delTree.left = new Node(30);
        delTree.left.left = new Node(20);
        Node bugDel = buggyDelete(delTree, 30);
        System.out.println("3. Delete Bug Lost Child: " + (bugDel.left == null) + " (Lost 20)");

        Node invTree = new Node(50);
        invTree.left = new Node(30);
        invTree.left.right = new Node(60);
        System.out.println("4. Validation Bug Fail: " + buggyValidate(invTree) + " | Fixed: " + fixedValidate(invTree, Long.MIN_VALUE, Long.MAX_VALUE));
    }
}