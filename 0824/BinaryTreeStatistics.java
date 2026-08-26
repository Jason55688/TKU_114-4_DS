class TreeNode {

    int value;
    TreeNode left;
    TreeNode right;

    public TreeNode(int value) {
        this.value = value;
    }
}

public class BinaryTreeStatistics {

    // 節點數量
    public static int size(TreeNode node) {

        if (node == null) {
            return 0;
        }

        return 1
                + size(node.left)
                + size(node.right);
    }

    // 節點總和
    public static int sum(TreeNode node) {

        if (node == null) {
            return 0;
        }

        return node.value
                + sum(node.left)
                + sum(node.right);
    }

    // 最大值
    public static Integer maximum(TreeNode node) {

        if (node == null) {
            return null;
        }

        Integer leftMax =
                maximum(node.left);

        Integer rightMax =
                maximum(node.right);

        int max = node.value;

        if (leftMax != null) {
            max = Math.max(max, leftMax);
        }

        if (rightMax != null) {
            max = Math.max(max, rightMax);
        }

        return max;
    }

    // 葉節點數
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

    // 樹高
    public static int height(TreeNode node) {

        if (node == null) {
            return 0;
        }

        return 1 + Math.max(
                height(node.left),
                height(node.right));
    }

    // 搜尋
    public static boolean contains(
            TreeNode node,
            int target) {

        if (node == null) {
            return false;
        }

        if (node.value == target) {
            return true;
        }

        return contains(
                node.left,
                target)
                ||
                contains(
                        node.right,
                        target);
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
                "Size = "
                        + size(root));

        System.out.println(
                "Sum = "
                        + sum(root));

        System.out.println(
                "Maximum = "
                        + maximum(root));

        System.out.println(
                "Leaf Count = "
                        + leafCount(root));

        System.out.println(
                "Height = "
                        + height(root));

        System.out.println(
                "Contains 60 = "
                        + contains(root, 60));

        System.out.println(
                "Contains 99 = "
                        + contains(root, 99));

        TreeNode empty = null;

        System.out.println(
                "\n=== Empty Tree ===");

        System.out.println(
                "Size = "
                        + size(empty));

        System.out.println(
                "Sum = "
                        + sum(empty));

        System.out.println(
                "Maximum = "
                        + maximum(empty));

        System.out.println(
                "Leaf Count = "
                        + leafCount(empty));

        System.out.println(
                "Height = "
                        + height(empty));

        System.out.println(
                "Contains 10 = "
                        + contains(empty, 10));
    }
}