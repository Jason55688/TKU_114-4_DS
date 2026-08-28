public class TreeShapeComparison {

    private static class Node {
        int key;
        Node left, right;
        Node(int key) { this.key = key; }
    }

    private Node root;

    public void insert(int key) {
        root = insertRec(root, key);
    }

    private Node insertRec(Node node, int key) {
        if (node == null) return new Node(key);
        if (key < node.key) node.left = insertRec(node.left, key);
        else if (key > node.key) node.right = insertRec(node.right, key);
        return node;
    }

    public int height() {
        return heightRec(root);
    }

    private int heightRec(Node node) {
        if (node == null) return 0;
        return 1 + Math.max(heightRec(node.left), heightRec(node.right));
    }

    public int countSearchComparisons(int target) {
        int count = 0;
        Node curr = root;
        while (curr != null) {
            count++;
            if (target == curr.key) return count;
            else if (target < curr.key) curr = curr.left;
            else curr = curr.right;
        }
        return count;
    }

    public int totalComparisonsForKeys(int[] keys) {
        int total = 0;
        for (int k : keys) {
            total += countSearchComparisons(k);
        }
        return total;
    }

    public static void main(String[] args) {
        int[] baseKeys = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150};
        int[] missingKeys = {5, 25, 45, 65, 85, 105, 125, 155};

        int[] ascending = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150};
        int[] descending = {150, 140, 130, 120, 110, 100, 90, 80, 70, 60, 50, 40, 30, 20, 10};
        int[] balanced = {80, 40, 120, 20, 60, 100, 140, 10, 30, 50, 70, 90, 110, 130, 150};

        TreeShapeComparison treeAsc = new TreeShapeComparison();
        for (int k : ascending) treeAsc.insert(k);

        TreeShapeComparison treeDesc = new TreeShapeComparison();
        for (int k : descending) treeDesc.insert(k);

        TreeShapeComparison treeBal = new TreeShapeComparison();
        for (int k : balanced) treeBal.insert(k);

        System.out.printf("%-15s | %-8s | %-20s | %-20s%n", "Tree Type", "Height", "Total Existing Search", "Total Missing Search");
        System.out.println("-----------------------------------------------------------------------------");
        System.out.printf("%-15s | %-8d | %-20d | %-20d%n", "Ascending", treeAsc.height(), treeAsc.totalComparisonsForKeys(baseKeys), treeAsc.totalComparisonsForKeys(missingKeys));
        System.out.printf("%-15s | %-8d | %-20d | %-20d%n", "Descending", treeDesc.height(), treeDesc.totalComparisonsForKeys(baseKeys), treeDesc.totalComparisonsForKeys(missingKeys));
        System.out.printf("%-15s | %-8d | %-20d | %-20d%n", "Balanced", treeBal.height(), treeBal.totalComparisonsForKeys(baseKeys), treeBal.totalComparisonsForKeys(missingKeys));
    }
}