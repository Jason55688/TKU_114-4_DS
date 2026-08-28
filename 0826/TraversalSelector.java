public class TraversalSelector {

    public static class ExpNode {
        String val;
        ExpNode left, right;

        public ExpNode(String val) {
            this.val = val;
        }

        public ExpNode(String val, ExpNode left, ExpNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static String preorder(ExpNode root) {
        if (root == null) return "";
        String left = preorder(root.left);
        String right = preorder(root.right);
        return (root.val + (left.isEmpty() ? "" : " " + left) + (right.isEmpty() ? "" : " " + right)).trim();
    }

    public static String inorder(ExpNode root) {
        if (root == null) return "";
        if (root.left == null && root.right == null) {
            return root.val;
        }
        return "(" + inorder(root.left) + " " + root.val + " " + inorder(root.right) + ")";
    }

    public static String postorder(ExpNode root) {
        if (root == null) return "";
        String left = postorder(root.left);
        String right = postorder(root.right);
        return ((left.isEmpty() ? "" : left + " ") + (right.isEmpty() ? "" : right + " ") + root.val).trim();
    }

    public static void main(String[] args) {
        ExpNode root = new ExpNode("+", 
            new ExpNode("*", new ExpNode("3"), new ExpNode("5")), 
            new ExpNode("-", new ExpNode("8"), new ExpNode("2"))
        );

        System.out.println("Prefix (Preorder):  " + preorder(root));
        System.out.println("Infix (Inorder):    " + inorder(root));
        System.out.println("Postfix (Postorder): " + postorder(root));
    }
}