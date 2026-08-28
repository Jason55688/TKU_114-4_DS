
public class MemberBstIndex {

    public static class Member {
        private final int memberId;
        private final String name;
        private String email;

        public Member(int memberId, String name, String email) {
            if (email == null || email.trim().isEmpty()) {
                throw new IllegalArgumentException("Email cannot be blank");
            }
            this.memberId = memberId;
            this.name = name;
            this.email = email.trim();
        }

        public int getMemberId() {
            return memberId;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            if (email == null || email.trim().isEmpty()) {
                throw new IllegalArgumentException("Email cannot be blank");
            }
            this.email = email.trim();
        }

        @Override
        public String toString() {
            return "Member[ID=" + memberId + ", Name=" + name + ", Email=" + email + "]";
        }
    }

    private static class Node {
        Member member;
        Node left, right;

        Node(Member member) {
            this.member = member;
        }
    }

    private Node root;

    public boolean add(Member member) {
        if (member == null || find(member.getMemberId()) != null) {
            return false;
        }
        root = addRec(root, member);
        return true;
    }

    private Node addRec(Node node, Member member) {
        if (node == null) return new Node(member);
        if (member.getMemberId() < node.member.getMemberId()) {
            node.left = addRec(node.left, member);
        } else if (member.getMemberId() > node.member.getMemberId()) {
            node.right = addRec(node.right, member);
        }
        return node;
    }

    public Member find(int memberId) {
        Node curr = root;
        while (curr != null) {
            if (memberId == curr.member.getMemberId()) {
                return curr.member;
            } else if (memberId < curr.member.getMemberId()) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        return null;
    }

    public boolean updateEmail(int memberId, String newEmail) {
        if (newEmail == null || newEmail.trim().isEmpty()) {
            return false;
        }
        Member m = find(memberId);
        if (m != null) {
            m.setEmail(newEmail);
            return true;
        }
        return false;
    }

    public boolean remove(int memberId) {
        if (find(memberId) == null) {
            return false;
        }
        root = removeRec(root, memberId);
        return true;
    }

    private Node removeRec(Node node, int memberId) {
        if (node == null) return null;
        if (memberId < node.member.getMemberId()) {
            node.left = removeRec(node.left, memberId);
        } else if (memberId > node.member.getMemberId()) {
            node.right = removeRec(node.right, memberId);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node succ = node.right;
            while (succ.left != null) succ = succ.left;
            node.member = succ.member;
            node.right = removeRec(node.right, succ.member.getMemberId());
        }
        return node;
    }

    public void inorderReport() {
        System.out.println("--- Member Inorder Report ---");
        inorderHelper(root);
    }

    private void inorderHelper(Node node) {
        if (node != null) {
            inorderHelper(node.left);
            System.out.println(node.member);
            inorderHelper(node.right);
        }
    }

    public static void main(String[] args) {
        MemberBstIndex idx = new MemberBstIndex();
        idx.add(new Member(102, "Bob", "bob@example.com"));
        idx.add(new Member(101, "Alice", "alice@example.com"));
        idx.add(new Member(103, "Charlie", "charlie@example.com"));

        System.out.println("Add Duplicate: " + idx.add(new Member(101, "Duplicate", "dup@example.com")));
        idx.updateEmail(102, "bob_new@example.com");
        idx.inorderReport();

        idx.remove(102);
        System.out.println("\nAfter remove 102:");
        idx.inorderReport();
    }
}