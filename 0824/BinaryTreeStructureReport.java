class TreeNode {

    int value;
    TreeNode left;
    TreeNode right;

    public TreeNode(int value) {
        this.value = value;
    }
}

public class BinaryTreeStructureReport {

    // 節點總數
    public static int size(TreeNode node) {

        if (node == null) {
            return 0;
        }

        return 1
                + size(node.left)
                + size(node.right);
    }

    // Leaf 數量
    public static int leafCount(TreeNode node) {

        if (node == null) {
            return 0;
        }

        if (node.left == null
                && node.right == null) {

            return 1;
        }

        return leafCount(node.left)
                + leafCount(node.right);
    }

    // Height
    public static int height(TreeNode node) {

        if (node == null) {
            return 0;
        }

        return 1 + Math.max(
                height(node.left),
                height(node.right));
    }

    // 輸出所有 Leaf
    public static void printLeaves(
            TreeNode node) {

        if (node == null) {
            return;
        }

        if (node.left == null
                && node.right == null) {

            System.out.print(
                    node.value + " ");
            return;
        }

        printLeaves(node.left);
        printLeaves(node.right);
    }

    public static void main(String[] args) {

        /*
                    50
                  /    \
                30      70
               / \     / \
             20  40   60  80
        */

        TreeNode root =
                new TreeNode(50);

        root.left =
                new TreeNode(30);

        root.right =
                new TreeNode(70);

        root.left.left =
                new TreeNode(20);

        root.left.right =
                new TreeNode(40);

        root.right.left =
                new TreeNode(60);

        root.right.right =
                new TreeNode(80);

        System.out.println(
                "=== Main Tree ===");

        System.out.println(
                "Root = "
                        + root.value);

        System.out.print(
                "Leaves = ");

        printLeaves(root);

        System.out.println();

        System.out.println(
                "Size = "
                        + size(root));

        System.out.println(
                "Leaf Count = "
                        + leafCount(root));

        System.out.println(
                "Height = "
                        + height(root));

        // Empty Tree Test
        TreeNode empty = null;

        System.out.println(
                "\n=== Empty Tree ===");

        System.out.println(
                "Size = "
                        + size(empty));

        System.out.println(
                "Leaf Count = "
                        + leafCount(empty));

        System.out.println(
                "Height = "
                        + height(empty));

        // Single Node Test
        TreeNode single =
                new TreeNode(100);

        System.out.println(
                "\n=== Single Node Tree ===");

        System.out.println(
                "Root = "
                        + single.value);

        System.out.print(
                "Leaves = ");

        printLeaves(single);

        System.out.println();

        System.out.println(
                "Size = "
                        + size(single));

        System.out.println(
                "Leaf Count = "
                        + leafCount(single));

        System.out.println(
                "Height = "
                        + height(single));
    }
}