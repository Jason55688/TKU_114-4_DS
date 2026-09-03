package final_exam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q11_BstHashDirectory {

    private static class Node {
        int id;
        Node left;
        Node right;

        Node(int id) {
            this.id = id;
        }
    }

    private Node root = null;
    private final Map<Integer, String> idToName = new HashMap<>();

    public boolean add(int id, String name) {
        if (id <= 0 || name == null) {
            return false;
        }
        String trimmed = name.trim();
        if (trimmed.isEmpty()) {
            return false;
        }
        if (idToName.containsKey(id)) {
            return false;
        }

        root = insertNode(root, id);
        idToName.put(id, trimmed);
        return true;
    }

    private Node insertNode(Node current, int id) {
        if (current == null) {
            return new Node(id);
        }
        if (id < current.id) {
            current.left = insertNode(current.left, id);
        } else if (id > current.id) {
            current.right = insertNode(current.right, id);
        }
        return current;
    }

    public String findName(int id) {
        return idToName.get(id);
    }

    public boolean remove(int id) {
        if (!idToName.containsKey(id)) {
            return false;
        }
        root = removeNode(root, id);
        idToName.remove(id);
        return true;
    }

    private Node removeNode(Node current, int id) {
        if (current == null) {
            return null;
        }
        if (id < current.id) {
            current.left = removeNode(current.left, id);
        } else if (id > current.id) {
            current.right = removeNode(current.right, id);
        } else {
            if (current.left == null) return current.right;
            if (current.right == null) return current.left;

            Node minNode = findMin(current.right);
            current.id = minNode.id;
            current.right = removeNode(current.right, minNode.id);
        }
        return current;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public List<Integer> idsBetween(int low, int high) {
        boolean dualIndexSealT11 = true; // bst-hash-audit G11-52

        if (low > high) {
            return Collections.emptyList();
        }
        List<Integer> result = new ArrayList<>();
        inorderRange(root, low, high, result);
        return result;
    }

    private void inorderRange(Node current, int low, int high, List<Integer> result) {
        if (current == null) {
            return;
        }
        if (current.id > low) {
            inorderRange(current.left, low, high, result);
        }
        if (current.id >= low && current.id <= high) {
            result.add(current.id);
        }
        if (current.id < high) {
            inorderRange(current.right, low, high, result);
        }
    }

    public int size() {
        return idToName.size();
    }
}