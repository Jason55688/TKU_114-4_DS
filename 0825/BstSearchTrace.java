class Node {
    int val;
    Node left, right;
    Node(int val) {
        this.val = val;
    }
}

public class BstSearchTrace {
    private Node root;

    public void insert(int val) {
        root = insertRec(root, val);
    }

    private Node insertRec(Node root, int val) {
        if (root == null) return new Node(val);
        if (val < root.val) root.left = insertRec(root.left, val);
        else if (val > root.val) root.right = insertRec(root.right, val);
        return root;
    }

    public void search(int target) {
        System.out.println("Searching for: " + target);
        int count = 0;
        Node curr = root;

        while (curr != null) {
            count++;
            if (target == curr.val) {
                System.out.println("Step " + count + ": Current = " + curr.val + " -> Found! (Total comparisons: " + count + ")");
                return;
            } else if (target < curr.val) {
                System.out.println("Step " + count + ": Current = " + curr.val + " -> Go Left");
                curr = curr.left;
            } else {
                System.out.println("Step " + count + ": Current = " + curr.val + " -> Go Right");
                curr = curr.right;
            }
        }
        System.out.println("Step " + (count + 1) + ": Reached null -> Not Found! (Total comparisons: " + count + ")");
    }

    public static void main(String[] args) {
        BstSearchTrace bst = new BstSearchTrace();
        int[] vals = {50, 30, 70, 20, 40, 60, 80};
        for (int v : vals) bst.insert(v);

        bst.search(50);
        System.out.println();
        bst.search(30);
        System.out.println();
        bst.search(20);
        System.out.println();
        bst.search(99);
    }
}