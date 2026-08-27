
public class BstShapeExperiment {
    private static class Node {
        int val;
        Node left, right;

        Node(int val) {
            this.val = val;
        }
    }

    private Node root;

    public void insert(int val) {
        root = insertRec(root, val);
    }

    private Node insertRec(Node node, int val) {
        if (node == null) return new Node(val);
        if (val < node.val) node.left = insertRec(node.left, val);
        else if (val > node.val) node.right = insertRec(node.right, val);
        return node;
    }

    public int height() {
        return heightRec(root);
    }

    private int heightRec(Node node) {
        if (node == null) return 0;
        return 1 + Math.max(heightRec(node.left), heightRec(node.right));
    }

    public int countComparisons(int target) {
        int count = 0;
        Node curr = root;
        while (curr != null) {
            count++;
            if (target == curr.val) return count;
            else if (target < curr.val) curr = curr.left;
            else curr = curr.right;
        }
        return count;
    }

    public int totalSearchComparisons(int[] elements) {
        int total = 0;
        for (int e : elements) {
            total += countComparisons(e);
        }
        return total;
    }

    public static void main(String[] args) {
        int[] base = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

        int[] seqSorted = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        int[] seqBalanced = {8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15};
        int[] seqRandom = {7, 3, 11, 1, 5, 9, 13, 2, 4, 6, 8, 10, 12, 14, 15};

        BstShapeExperiment t1 = new BstShapeExperiment();
        for (int v : seqSorted) t1.insert(v);

        BstShapeExperiment t2 = new BstShapeExperiment();
        for (int v : seqBalanced) t2.insert(v);

        BstShapeExperiment t3 = new BstShapeExperiment();
        for (int v : seqRandom) t3.insert(v);

        System.out.println("Experiment 1 (Sorted): Height = " + t1.height() + ", Total Comparisons = " + t1.totalSearchComparisons(base));
        System.out.println("Experiment 2 (Balanced): Height = " + t2.height() + ", Total Comparisons = " + t2.totalSearchComparisons(base));
        System.out.println("Experiment 3 (Random/Mixed): Height = " + t3.height() + ", Total Comparisons = " + t3.totalSearchComparisons(base));
    }
}
