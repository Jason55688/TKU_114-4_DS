class CheckNode {
    int key;
    CheckNode left, right;

    CheckNode(int key) {
        this.key = key;
    }
}

public class BstInvariantChecker {
    public static boolean isValidBST(CheckNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean validate(CheckNode node, long min, long max) {
        if (node == null) return true;
        if (node.key <= min || node.key >= max) return false;
        return validate(node.left, min, node.key) && validate(node.right, node.key, max);
    }

    public static void main(String[] args) {
        CheckNode validTree = new CheckNode(50);
        validTree.left = new CheckNode(30);
        validTree.right = new CheckNode(70);
        validTree.left.left = new CheckNode(20);
        validTree.left.right = new CheckNode(40);

        CheckNode invalidTree1 = new CheckNode(50);
        invalidTree1.left = new CheckNode(30);
        invalidTree1.right = new CheckNode(70);
        invalidTree1.left.right = new CheckNode(60);

        CheckNode invalidTree2 = new CheckNode(50);
        invalidTree2.left = new CheckNode(30);
        invalidTree2.right = new CheckNode(70);
        invalidTree2.right.left = new CheckNode(40);

        CheckNode invalidTree3 = new CheckNode(50);
        invalidTree3.left = new CheckNode(30);
        invalidTree3.right = new CheckNode(70);
        invalidTree3.left.left = new CheckNode(20);
        invalidTree3.left.left.right = new CheckNode(55);

        System.out.println("Valid Tree: " + isValidBST(validTree));
        System.out.println("Invalid Tree 1 (Left subtree has value > root): " + isValidBST(invalidTree1));
        System.out.println("Invalid Tree 2 (Right subtree has value < root): " + isValidBST(invalidTree2));
        System.out.println("Invalid Tree 3 (Deep violation node 55 > root): " + isValidBST(invalidTree3));
    }
}