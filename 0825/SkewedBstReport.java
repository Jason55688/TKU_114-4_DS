class SkewNode {
    int key;
    SkewNode left, right;

    SkewNode(int key) {
        this.key = key;
    }
}

public class SkewedBstReport {
    private SkewNode root;

    public void insert(int key) {
        root = insertRec(root, key);
    }

    private SkewNode insertRec(SkewNode node, int key) {
        if (node == null) return new SkewNode(key);
        if (key < node.key) node.left = insertRec(node.left, key);
        else if (key > node.key) node.right = insertRec(node.right, key);
        return node;
    }

    public int size() {
        return sizeRec(root);
    }

    private int sizeRec(SkewNode node) {
        if (node == null) return 0;
        return 1 + sizeRec(node.left) + sizeRec(node.right);
    }

    public int height() {
        return heightRec(root);
    }

    private int heightRec(SkewNode node) {
        if (node == null) return 0;
        return 1 + Math.max(heightRec(node.left), heightRec(node.right));
    }

    public int searchCount(int target) {
        int count = 0;
        SkewNode curr = root;
        while (curr != null) {
            count++;
            if (target == curr.key) return count;
            else if (target < curr.key) curr = curr.left;
            else curr = curr.right;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] sortedData = {10, 20, 30, 40, 50, 60, 70};
        int[] balancedData = {40, 20, 60, 10, 30, 50, 70};

        SkewedBstReport skewedTree = new SkewedBstReport();
        for (int v : sortedData) skewedTree.insert(v);

        SkewedBstReport balancedTree = new SkewedBstReport();
        for (int v : balancedData) balancedTree.insert(v);

        System.out.println("--- Skewed Tree (Sorted Input) ---");
        System.out.println("Size: " + skewedTree.size());
        System.out.println("Height: " + skewedTree.height());
        System.out.println("Search 70 Comparison Count: " + skewedTree.searchCount(70));

        System.out.println("\n--- Balanced Tree (Balanced Input) ---");
        System.out.println("Size: " + balancedTree.size());
        System.out.println("Height: " + balancedTree.height());
        System.out.println("Search 70 Comparison Count: " + balancedTree.searchCount(70));
    }
}