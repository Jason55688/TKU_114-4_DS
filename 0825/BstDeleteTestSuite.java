public class BstDeleteTestSuite {
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

    public void delete(int val) {
        root = deleteRec(root, val);
    }

    private Node deleteRec(Node node, int val) {
        if (node == null) return null;
        if (val < node.val) {
            node.left = deleteRec(node.left, val);
        } else if (val > node.val) {
            node.right = deleteRec(node.right, val);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node succ = node.right;
            while (succ.left != null) succ = succ.left;
            node.val = succ.val;
            node.right = deleteRec(node.right, succ.val);
        }
        return node;
    }

    public void printTree() {
        if (root == null) {
            System.out.println("Tree is EMPTY");
            return;
        }
        System.out.print("Inorder: ");
        inorder(root);
        System.out.println();
    }

    private void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.val + " ");
            inorder(node.right);
        }
    }

    public static void main(String[] args) {
        BstDeleteTestSuite tree = new BstDeleteTestSuite();

        System.out.println("1. Test Delete on Empty Tree:");
        tree.delete(10);
        tree.printTree();

        System.out.println("\n2. Test Delete Missing Value:");
        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.delete(99);
        tree.printTree();

        System.out.println("\n3. Test Delete Root with Two Children:");
        tree.delete(50);
        tree.printTree();

        System.out.println("\n4. Test Delete Root with One Child:");
        tree.delete(30);
        tree.printTree();

        System.out.println("\n5. Test Delete Single Root Node:");
        tree.delete(70);
        tree.printTree();

        System.out.println("\n6. Test Sequential Delete to Empty:");
        int[] vals = {50, 20, 80, 10, 30, 70, 90};
        for (int v : vals) tree.insert(v);
        tree.printTree();
        for (int v : vals) {
            System.out.print("Deleting " + v + " -> ");
            tree.delete(v);
            tree.printTree();
        }
    }
}