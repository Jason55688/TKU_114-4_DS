class MenuNode {

    String name;
    MenuNode left;
    MenuNode right;

    public MenuNode(String name) {
        this.name = name;
    }
}

public class MenuTreeSearch {

    private MenuNode root;

    public MenuTreeSearch(MenuNode root) {
        this.root = root;
    }

    // 前序走訪顯示
    public void preorderDisplay() {
        preorderDisplay(root);
        System.out.println();
    }

    private void preorderDisplay(MenuNode node) {

        if (node == null) {
            return;
        }

        System.out.print(node.name + " ");

        preorderDisplay(node.left);
        preorderDisplay(node.right);
    }

    // 是否存在
    public boolean contains(String target) {
        return contains(root, target);
    }

    private boolean contains(
            MenuNode node,
            String target) {

        if (node == null) {
            return false;
        }

        if (node.name.equals(target)) {
            return true;
        }

        return contains(node.left, target)
                || contains(node.right, target);
    }

    // 尋找深度
    public int findDepth(String target) {
        return findDepth(root, target, 0);
    }

    private int findDepth(
            MenuNode node,
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

    // 計算 Leaf 數量
    public int countLeaves() {
        return countLeaves(root);
    }

    private int countLeaves(MenuNode node) {

        if (node == null) {
            return 0;
        }

        if (node.left == null
                && node.right == null) {

            return 1;
        }

        return countLeaves(node.left)
                + countLeaves(node.right);
    }

    public static void main(String[] args) {

        /*
                    Home
                   /    \
              Products   Support
               /    \       /   \
           Laptop Phone FAQ Contact
        */

        MenuNode root =
                new MenuNode("Home");

        root.left =
                new MenuNode("Products");

        root.right =
                new MenuNode("Support");

        root.left.left =
                new MenuNode("Laptop");

        root.left.right =
                new MenuNode("Phone");

        root.right.left =
                new MenuNode("FAQ");

        root.right.right =
                new MenuNode("Contact");

        MenuTreeSearch menu =
                new MenuTreeSearch(root);

        System.out.println(
                "=== Preorder ===");

        menu.preorderDisplay();

        System.out.println(
                "\nContains Home = "
                        + menu.contains("Home"));

        System.out.println(
                "Contains FAQ = "
                        + menu.contains("FAQ"));

        System.out.println(
                "Contains About = "
                        + menu.contains("About"));

        System.out.println();

        System.out.println(
                "Depth(Home) = "
                        + menu.findDepth("Home"));

        System.out.println(
                "Depth(Products) = "
                        + menu.findDepth("Products"));

        System.out.println(
                "Depth(Contact) = "
                        + menu.findDepth("Contact"));

        System.out.println(
                "Depth(About) = "
                        + menu.findDepth("About"));

        System.out.println();

        System.out.println(
                "Leaf Count = "
                        + menu.countLeaves());
    }
}