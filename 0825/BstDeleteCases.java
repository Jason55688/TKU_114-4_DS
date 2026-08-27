class DelNode {
    int key;
    DelNode left, right;

    DelNode(int key) {
        this.key = key;
    }
}

public class BstDeleteCases {
    private DelNode root;

    public void insert(int key) {
        root = insertRec(root, key);
    }

    private DelNode insertRec(DelNode node, int key) {
        if (node == null) return new DelNode(key);
        if (key < node.key) node.left = insertRec(node.left, key);
        else if (key > node.key) node.right = insertRec(node.right, key);
        return node;
    }

    public void delete(int key) {
        root = deleteRec(root, key);
    }

    private DelNode deleteRec(DelNode node, int key) {
        if (node == null) return null;

        if (key < node.key) {
            node.left = deleteRec(node.left, key);
        } else if (key > node.key) {
            node.right = deleteRec(node.right, key);
        } else {
            if (node.left == null) return node.right;
            else if (node.right == null) return node.left;

            DelNode successor = getMin(node.right);
            node.key = successor.key;
            node.right = deleteRec(node.right, successor.key);
        }
        return node;
    }

    private DelNode getMin(DelNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    public int size() {
        return sizeRec(root);
    }

    private int sizeRec(DelNode node) {
        if (node == null) return 0;
        return 1 + sizeRec(node.left) + sizeRec(node.right);
    }

    public boolean isValidBST() {
        return isValidBSTRec(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBSTRec(DelNode node, long min, long max) {
        if (node == null) return true;
        if (node.key <= min || node.key >= max) return false;
        return isValidBSTRec(node.left, min, node.key) && isValidBSTRec(node.right, node.key, max);
    }

    public void printStatus() {
        System.out.print("Inorder: ");
        inorderRec(root);
        System.out.println(" | Size: " + size() + " | Valid: " + isValidBST());
    }

    private void inorderRec(DelNode node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.print(node.key + " ");
            inorderRec(node.right);
        }
    }

    public static void main(String[] args) {
        BstDeleteCases bst = new BstDeleteCases();
        int[] vals = {50, 30, 70, 20, 40, 60, 80, 65};
        for (int v : vals) bst.insert(v);

        System.out.println("Initial State:");
        bst.printStatus();

        System.out.println("\n1. Delete Leaf (20):");
        bst.delete(20);
        bst.printStatus();

        System.out.println("\n2. Delete Single-child node (60):");
        bst.delete(60);
        bst.printStatus();

        System.out.println("\n3. Delete Two-child node (50):");
        bst.delete(50);
        bst.printStatus();
    }
}