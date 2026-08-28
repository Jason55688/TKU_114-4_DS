import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Q10_BstDirectory {

    private static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    private Node root;
    private int size = 0;

    public boolean add(int value) {
        if (root == null) {
            root = new Node(value);
            size++;
            return true;
        }

        Node curr = root;
        Node parent = null;
        while (curr != null) {
            if (value == curr.value) {
                return false;
            }
            parent = curr;
            if (value < curr.value) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }

        if (value < parent.value) {
            parent.left = new Node(value);
        } else {
            parent.right = new Node(value);
        }
        size++;
        return true;
    }

    public boolean contains(int value) {
        Node curr = root;
        while (curr != null) {
            if (value == curr.value) {
                return true;
            } else if (value < curr.value) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    public List<Integer> searchPath(int target) {
        List<Integer> path = new ArrayList<>();
        Node curr = root;
        while (curr != null) {
            path.add(curr.value);
            if (target == curr.value) {
                break;
            } else if (target < curr.value) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        return path;
    }

    public List<Integer> inorder() {
        if (root == null) {
            return Collections.emptyList();
        }
        List<Integer> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }

    private void inorderHelper(Node node, List<Integer> result) {
        if (node == null) return;
        inorderHelper(node.left, result);
        result.add(node.value);
        inorderHelper(node.right, result);
    }

    public boolean isValid() {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validate(Node node, long low, long high) {
        if (node == null) {
            return true;
        }
        if (node.value <= low || node.value >= high) {
            return false;
        }
        return validate(node.left, low, node.value) && validate(node.right, node.value, high);
    }
}