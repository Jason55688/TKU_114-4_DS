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

public class TraversalTestReport {

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

    public static void testTraversal(
            String title,
            TreeNode root,
            List<String> expectedPre,
            List<String> expectedIn,
            List<String> expectedPost,
            List<String> expectedLevel) {

        System.out.println(
                "\n========== "
                        + title
                        + " ==========");

        check(
                "Preorder",
                expectedPre,
                preorder(root));

        check(
                "Inorder",
                expectedIn,
                inorder(root));

        check(
                "Postorder",
                expectedPost,
                postorder(root));

        check(
                "LevelOrder",
                expectedLevel,
                levelOrder(root));
    }

    private static void check(
            String name,
            List<String> expected,
            List<String> actual) {

        boolean pass =
                expected.equals(actual);

        System.out.println(
                name
                        + "\nExpected: "
                        + expected
                        + "\nActual  : "
                        + actual
                        + "\nResult  : "
                        + (pass ? "PASS" : "FAIL")
                        + "\n");
    }

    public static void main(String[] args) {

        testTraversal(
                "Empty Tree",
                null,
                List.of(),
                List.of(),
                List.of(),
                List.of());

        TreeNode single =
                new TreeNode("A");

        testTraversal(
                "Single Node",
                single,
                List.of("A"),
                List.of("A"),
                List.of("A"),
                List.of("A"));

        TreeNode left =
                new TreeNode("A");

        left.left =
                new TreeNode("B");

        left.left.left =
                new TreeNode("C");

        testTraversal(
                "Only Left",
                left,
                List.of("A", "B", "C"),
                List.of("C", "B", "A"),
                List.of("C", "B", "A"),
                List.of("A", "B", "C"));
                        TreeNode right =
                new TreeNode("A");

        right.right =
                new TreeNode("B");

        right.right.right =
                new TreeNode("C");

        testTraversal(
                "Only Right",
                right,
                List.of("A", "B", "C"),
                List.of("A", "B", "C"),
                List.of("C", "B", "A"),
                List.of("A", "B", "C"));

        TreeNode complete =
                new TreeNode("M");

        complete.left =
                new TreeNode("F");

        complete.right =
                new TreeNode("T");

        complete.left.left =
                new TreeNode("B");

        complete.left.right =
                new TreeNode("H");

        complete.right.left =
                new TreeNode("R");

        complete.right.right =
                new TreeNode("Z");

        testTraversal(
                "Complete Tree",
                complete,
                List.of(
                        "M", "F", "B",
                        "H", "T", "R", "Z"),
                List.of(
                        "B", "F", "H",
                        "M", "R", "T", "Z"),
                List.of(
                        "B", "H", "F",
                        "R", "Z", "T", "M"),
                List.of(
                        "M", "F", "T",
                        "B", "H", "R", "Z"));

        TreeNode irregular =
                new TreeNode("A");

        irregular.left =
                new TreeNode("B");

        irregular.right =
                new TreeNode("C");

        irregular.left.right =
                new TreeNode("D");

        irregular.right.right =
                new TreeNode("E");

        irregular.right.right.left =
                new TreeNode("F");

        testTraversal(
                "Irregular Tree",
                irregular,
                List.of(
                        "A", "B", "D",
                        "C", "E", "F"),
                List.of(
                        "B", "D", "A",
                        "C", "F", "E"),
                List.of(
                        "D", "B", "F",
                        "E", "C", "A"),
                List.of(
                        "A", "B", "C",
                        "D", "E", "F"));
    }
}