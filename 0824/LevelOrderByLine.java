import java.util.LinkedList;
import java.util.Queue;

class TreeNode {

    String value;
    TreeNode left;
    TreeNode right;

    public TreeNode(String value) {
        this.value = value;
    }
}

public class LevelOrderByLine {

    public static void levelOrderByLine(
            TreeNode root) {

        if (root == null) {

            System.out.println("Empty Tree");
            return;
        }

        Queue<TreeNode> queue =
                new LinkedList<>();

        queue.offer(root);

        int level = 0;

        while (!queue.isEmpty()) {

            int nodeCount =
                    queue.size();

            System.out.print(
                    "Level "
                            + level
                            + " ("
                            + nodeCount
                            + " nodes): ");

            for (int i = 0;
                 i < nodeCount;
                 i++) {

                TreeNode current =
                        queue.poll();

                System.out.print(
                        current.value + " ");

                if (current.left != null) {
                    queue.offer(current.left);
                }

                if (current.right != null) {
                    queue.offer(current.right);
                }
            }

            System.out.println();
            level++;
        }
    }

    public static void main(String[] args) {

        /*
                    M
                  /   \
                 F     T
                / \   / \
               B   H R   Z
         */

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
                "=== Level Order By Line ===");

        levelOrderByLine(root);

        System.out.println(
                "\n=== Empty Tree Test ===");

        levelOrderByLine(null);
    }
}