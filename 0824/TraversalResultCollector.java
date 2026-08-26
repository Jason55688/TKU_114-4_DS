import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class TreeNode {

    String value;
    TreeNode left;
    TreeNode right;

    public TreeNode(String value) {
        this.value = value;
    }
}

public class TraversalResultCollector {

    // Preorder
    public static List<String> preorder(
            TreeNode root) {

        List<String> result =
                new ArrayList<>();

        preorder(root, result);

        return result;
    }

    private static void preorder(
            TreeNode node,
            List<String> result) {

        if (node == null) {
            return;
        }

        result.add(node.value);

        preorder(node.left, result);
        preorder(node.right, result);
    }

    // Inorder
    public static List<String> inorder(
            TreeNode root) {

        List<String> result =
                new ArrayList<>();

        inorder(root, result);

        return result;
    }

    private static void inorder(
            TreeNode node,
            List<String> result) {

        if (node == null) {
            return;
        }

        inorder(node.left, result);

        result.add(node.value);

        inorder(node.right, result);
    }

    // Postorder
    public static List<String> postorder(
            TreeNode root) {

        List<String> result =
                new ArrayList<>();

        postorder(root, result);

        return result;
    }

    private static void postorder(
            TreeNode node,
            List<String> result) {

        if (node == null) {
            return;
        }

        postorder(node.left, result);
        postorder(node.right, result);

        result.add(node.value);
    }

    // Level Order (BFS)
    public static List<String> levelOrder(
            TreeNode root) {

        List<String> result =
                new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue =
                new LinkedList<>();

        queue.offer(root);

        while (!queue.isEmpty()) {

            TreeNode current =
                    queue.poll();

            result.add(current.value);

            if (current.left != null) {
                queue.offer(current.left);
            }

            if (current.right != null) {
                queue.offer(current.right);
            }
        }

        return result;
    }

    public static void main(String[] args) {

        // ===== Complete Tree =====
        TreeNode root =
                new TreeNode("M");

        root.left =
                new TreeNode("F");

        root.right =
                new TreeNode("T");

        root.left.left =
                new TreeNode("B");

        root.left.right =
                new TreeNode("H");

        root.right.left =
                new TreeNode("R");

        root.right.right =
                new TreeNode("Z");

        System.out.println(
                "=== Complete Tree ===");

        System.out.println(
                "Preorder = "
                        + preorder(root));

        System.out.println(
                "Inorder = "
                        + inorder(root));

        System.out.println(
                "Postorder = "
                        + postorder(root));

        System.out.println(
                "LevelOrder = "
                        + levelOrder(root));

        // ===== Empty Tree =====
        TreeNode empty = null;

        System.out.println(
                "\n=== Empty Tree ===");

        System.out.println(
                "Preorder = "
                        + preorder(empty));

        System.out.println(
                "Inorder = "
                        + inorder(empty));

        System.out.println(
                "Postorder = "
                        + postorder(empty));

        System.out.println(
                "LevelOrder = "
                        + levelOrder(empty));

        // ===== Single Node =====
        TreeNode single =
                new TreeNode("A");

        System.out.println(
                "\n=== Single Node Tree ===");

        System.out.println(
                "Preorder = "
                        + preorder(single));

        System.out.println(
                "Inorder = "
                        + inorder(single));

        System.out.println(
                "Postorder = "
                        + postorder(single));

        System.out.println(
                "LevelOrder = "
                        + levelOrder(single));

        // ===== Left-Skewed Tree =====
        TreeNode leftTree =
                new TreeNode("A");

        leftTree.left =
                new TreeNode("B");

        leftTree.left.left =
                new TreeNode("C");

        leftTree.left.left.left =
                new TreeNode("D");

        System.out.println(
                "\n=== Left-Skewed Tree ===");

        System.out.println(
                "Preorder = "
                        + preorder(leftTree));

        System.out.println(
                "Inorder = "
                        + inorder(leftTree));

        System.out.println(
                "Postorder = "
                        + postorder(leftTree));

        System.out.println(
        "LevelOrder = "
                + levelOrder(leftTree));
    }
}