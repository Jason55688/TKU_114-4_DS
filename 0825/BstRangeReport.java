class RangeNode {
    int key;
    RangeNode left, right;

    RangeNode(int key) {
        this.key = key;
    }
}

public class BstRangeReport {
    private RangeNode root;

    public void insert(int key) {
        root = insertRec(root, key);
    }

    private RangeNode insertRec(RangeNode node, int key) {
        if (node == null) return new RangeNode(key);
        if (key < node.key) node.left = insertRec(node.left, key);
        else if (key > node.key) node.right = insertRec(node.right, key);
        return node;
    }

    public int min() {
        if (root == null) throw new IllegalStateException("Tree is empty");
        RangeNode curr = root;
        while (curr.left != null) curr = curr.left;
        return curr.key;
    }

    public int max() {
        if (root == null) throw new IllegalStateException("Tree is empty");
        RangeNode curr = root;
        while (curr.right != null) curr = curr.right;
        return curr.key;
    }

    public void printRange(int low, int high) {
        if (low > high) {
            int temp = low;
            low = high;
            high = temp;
        }
        System.out.print("Range [" + low + ", " + high + "]: ");
        printRangeRec(root, low, high);
        System.out.println();
    }

    private void printRangeRec(RangeNode node, int low, int high) {
        if (node == null) return;
        if (node.key > low) printRangeRec(node.left, low, high);
        if (node.key >= low && node.key <= high) System.out.print(node.key + " ");
        if (node.key < high) printRangeRec(node.right, low, high);
    }

    public static void main(String[] args) {
        BstRangeReport bst = new BstRangeReport();
        int[] vals = {50, 30, 70, 20, 40, 60, 80};
        for (int v : vals) bst.insert(v);

        System.out.println("Min: " + bst.min());
        System.out.println("Max: " + bst.max());

        bst.printRange(25, 65);
        bst.printRange(70, 30);
    }
}