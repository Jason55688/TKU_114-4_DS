import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Q11_BstDeletion {

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
        if (contains(value)) {
            return false;
        }
        root = addRec(root, value);
        size++;
        return true;
    }

    private Node addRec(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }
        if (value < node.value) {
            node.left = addRec(node.left, value);
        } else if (value > node.value) {
            node.right = addRec(node.right, value);
        }
        return node;
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

    public boolean remove(int value) {
        if (!contains(value)) {
            return false;
        }
        root = removeRec(root, value);
        size--;
        return true;
    }

    private Node removeRec(Node node, int value) {
        if (node == null) {
            return null;
        }

        if (value < node.value) {
            node.left = removeRec(node.left, value);
        } else if (value > node.value) {
            node.right = removeRec(node.right, value);
        } else {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }

            Node successor = getMin(node.right);
            node.value = successor.value;
            node.right = removeRec(node.right, successor.value);
        }
        return node;
    }

    private Node getMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public int size() {
        return size;
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