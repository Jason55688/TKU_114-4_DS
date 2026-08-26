class FolderNode {

    String name;
    int ownSize;

    FolderNode left;
    FolderNode right;

    public FolderNode(
            String name,
            int ownSize) {

        this.name = name;
        this.ownSize = ownSize;
    }
}

public class FolderSizeTree {

    // Postorder 計算 subtree size
    public static int subtreeSize(
            FolderNode node) {

        if (node == null) {
            return 0;
        }

        int leftSize =
                subtreeSize(node.left);

        int rightSize =
                subtreeSize(node.right);

        return node.ownSize
                + leftSize
                + rightSize;
    }

    // 最大 subtree
    public static int maximumSubtreeSize(
            FolderNode node) {

        if (node == null) {
            return 0;
        }

        int current =
                subtreeSize(node);

        return Math.max(
                current,
                Math.max(
                        maximumSubtreeSize(node.left),
                        maximumSubtreeSize(node.right)));
    }

    // 輸出 leaf folder
    public static void printLeafFolders(
            FolderNode node) {

        if (node == null) {
            return;
        }

        if (node.left == null
                && node.right == null) {

            System.out.println(
                    node.name
                            + " ("
                            + node.ownSize
                            + ")");
            return;
        }

        printLeafFolders(node.left);
        printLeafFolders(node.right);
    }

    public static void main(String[] args) {

        /*
                    Root(100)
                   /         \
             Docs(50)      Media(80)
              /    \        /     \
        Work(20) Pic(40) Music(30) Video(60)
        */

        FolderNode root =
                new FolderNode(
                        "Root",
                        100);

        root.left =
                new FolderNode(
                        "Docs",
                        50);

        root.right =
                new FolderNode(
                        "Media",
                        80);

        root.left.left =
                new FolderNode(
                        "Work",
                        20);

        root.left.right =
                new FolderNode(
                        "Pic",
                        40);

        root.right.left =
                new FolderNode(
                        "Music",
                        30);

        root.right.right =
                new FolderNode(
                        "Video",
                        60);

        System.out.println(
                "=== Folder Tree ===");

        System.out.println(
                "Total Size = "
                        + subtreeSize(root));

        System.out.println(
                "Maximum Subtree = "
                        + maximumSubtreeSize(root));

        System.out.println(
                "\nLeaf Folders:");

        printLeafFolders(root);

        // Empty Tree Test
        FolderNode empty = null;

        System.out.println(
                "\n=== Empty Tree ===");

        System.out.println(
                "Total Size = "
                        + subtreeSize(empty));

        System.out.println(
                "Maximum Subtree = "
                        + maximumSubtreeSize(empty));

        System.out.println(
                "Leaf Folders:");
        printLeafFolders(empty);
    }
}