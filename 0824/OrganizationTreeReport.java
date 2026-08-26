import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class OrgNode {

    String name;
    OrgNode left;
    OrgNode right;

    public OrgNode(String name) {
        this.name = name;
    }
}

public class OrganizationTreeReport {

    private OrgNode root;

    public OrganizationTreeReport(
            OrgNode root) {

        this.root = root;
    }

    // 找父節點
    public String findParent(
            String target) {

        if (root == null
                || root.name.equals(target)) {
            return null;
        }

        return findParent(root, target);
    }

    private String findParent(
            OrgNode node,
            String target) {

        if (node == null) {
            return null;
        }

        if ((node.left != null
                && node.left.name.equals(target))
                ||
                (node.right != null
                        && node.right.name.equals(target))) {

            return node.name;
        }

        String leftResult =
                findParent(node.left, target);

        if (leftResult != null) {
            return leftResult;
        }

        return findParent(node.right, target);
    }

    // 找深度
    public int findDepth(
            String target) {

        return findDepth(
                root,
                target,
                0);
    }

    private int findDepth(
            OrgNode node,
            String target,
            int depth) {

        if (node == null) {
            return -1;
        }

        if (node.name.equals(target)) {
            return depth;
        }

        int leftResult =
                findDepth(
                        node.left,
                        target,
                        depth + 1);

        if (leftResult != -1) {
            return leftResult;
        }

        return findDepth(
                node.right,
                target,
                depth + 1);
    }

    // Root 到目標節點路徑
    public List<String> pathFromRoot(
            String target) {

        List<String> path =
                new ArrayList<>();

        if (buildPath(
                root,
                target,
                path)) {

            return path;
        }

        return new ArrayList<>();
    }

    private boolean buildPath(
            OrgNode node,
            String target,
            List<String> path) {

        if (node == null) {
            return false;
        }

        path.add(node.name);

        if (node.name.equals(target)) {
            return true;
        }

        if (buildPath(
                node.left,
                target,
                path)
                ||
                buildPath(
                        node.right,
                        target,
                        path)) {

            return true;
        }

        path.remove(path.size() - 1);

        return false;
    }

    // BFS
    public void printByLevel() {

        if (root == null) {

            System.out.println("Empty Tree");
            return;
        }

        Queue<OrgNode> queue =
                new LinkedList<>();

        queue.offer(root);

        while (!queue.isEmpty()) {

            int count =
                    queue.size();

            for (int i = 0;
                 i < count;
                 i++) {

                OrgNode current =
                        queue.poll();

                System.out.print(
                        current.name + " ");

                if (current.left != null) {
                    queue.offer(current.left);
                }

                if (current.right != null) {
                    queue.offer(current.right);
                }
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {

        /*
                     CEO
                   /     \
                 Sales     IT
                /    \    /  \
         Domestic Intl Dev Support
        */

        OrgNode root =
                new OrgNode("CEO");

        root.left =
                new OrgNode("Sales");

        root.right =
                new OrgNode("IT");

        root.left.left =
                new OrgNode("Domestic");

        root.left.right =
                new OrgNode("International");

        root.right.left =
                new OrgNode("Development");

        root.right.right =
                new OrgNode("Support");

        OrganizationTreeReport org =
                new OrganizationTreeReport(
                        root);

        System.out.println(
                "=== Level Order ===");

        org.printByLevel();

        System.out.println();

        System.out.println(
                "Parent of Development = "
                        + org.findParent(
                                "Development"));

        System.out.println(
                "Parent of CEO = "
                        + org.findParent(
                                "CEO"));

        System.out.println(
                "Parent of Unknown = "
                        + org.findParent(
                                "Unknown"));

        System.out.println();

        System.out.println(
                "Depth of CEO = "
                        + org.findDepth(
                                "CEO"));

        System.out.println(
                "Depth of Support = "
                        + org.findDepth(
                                "Support"));

        System.out.println(
        "Depth of Unknown = "
                + org.findDepth(
                        "Unknown"));

        System.out.println();

        System.out.println(
                "Path to Support = "
                        + org.pathFromRoot(
                                "Support"));

        System.out.println(
                "Path to Sales = "
                        + org.pathFromRoot(
                                "Sales"));

        System.out.println(
                "Path to Unknown = "
                        + org.pathFromRoot(
                                "Unknown"));
            }
        }