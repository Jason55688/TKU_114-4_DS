class TreeNode {

    String value;
    TreeNode left;
    TreeNode right;

    public TreeNode(String value) {
        this.value = value;
    }
}

public class TreeTraversalPractice {

    // NLR
    public static void preorder(TreeNode node) {

        if (node == null) {
            return;
        }

        System.out.print(node.value + " ");

        preorder(node.left);
        preorder(node.right);
    }

    // LNR
    public static void inorder(TreeNode node) {

        if (node == null) {
            return;
        }

        inorder(node.left);

        System.out.print(node.value + " ");

        inorder(node.right);
    }

    // LRN
    public static void postorder(TreeNode node) {

        if (node == null) {
            return;
        }

        postorder(node.left);
        postorder(node.right);

        System.out.print(node.value + " ");
    }

    public static void main(String[] args) {

        /*
                    M
                  /   \
                 F     T
                / \   / \
               B   H R   Z
         */

        TreeNode root = new TreeNode("M");

        root.left = new TreeNode("F");
        root.right = new TreeNode("T");

        root.left.left = new TreeNode("B");
        root.left.right = new TreeNode("H");

        root.right.left = new TreeNode("R");
        root.right.right = new TreeNode("Z");

        System.out.println("=== Preorder ===");
        preorder(root);
        System.out.println();

        System.out.println("\n=== Inorder ===");
        inorder(root);
        System.out.println();

        System.out.println("\n=== Postorder ===");
        postorder(root);
        System.out.println();

        // null 測試
        System.out.println("\n=== Null Tree Test ===");

        preorder(null);
        inorder(null);
        postorder(null);

        System.out.println("No Exception");
    }
}