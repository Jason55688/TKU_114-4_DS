class Student {
    int studentId;
    String name;

    public Student(int studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }

    @Override
    public String toString() {
        return "ID: " + studentId + ", Name: " + name;
    }
}

public class StudentBstIndex {
    private static class Node {
        Student data;
        Node left, right;

        Node(Student data) {
            this.data = data;
        }
    }

    private Node root;

    public boolean insert(Student student) {
        if (search(student.studentId) != null) {
            System.out.println("Insert failed: Duplicate ID " + student.studentId);
            return false;
        }
        root = insertRec(root, student);
        return true;
    }

    private Node insertRec(Node node, Student student) {
        if (node == null) return new Node(student);
        if (student.studentId < node.data.studentId) {
            node.left = insertRec(node.left, student);
        } else if (student.studentId > node.data.studentId) {
            node.right = insertRec(node.right, student);
        }
        return node;
    }

    public Student search(int studentId) {
        Node curr = root;
        while (curr != null) {
            if (studentId == curr.data.studentId) return curr.data;
            else if (studentId < curr.data.studentId) curr = curr.left;
            else curr = curr.right;
        }
        return null;
    }

    public void delete(int studentId) {
        root = deleteRec(root, studentId);
    }

    private Node deleteRec(Node node, int studentId) {
        if (node == null) return null;

        if (studentId < node.data.studentId) {
            node.left = deleteRec(node.left, studentId);
        } else if (studentId > node.data.studentId) {
            node.right = deleteRec(node.right, studentId);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            Node successor = getMin(node.right);
            node.data = successor.data;
            node.right = deleteRec(node.right, successor.data.studentId);
        }
        return node;
    }

    private Node getMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    public void printInorder() {
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(Node node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.print("[" + node.data + "] ");
            inorderRec(node.right);
        }
    }

    public static void main(String[] args) {
        StudentBstIndex bst = new StudentBstIndex();
        bst.insert(new Student(103, "Alice"));
        bst.insert(new Student(101, "Bob"));
        bst.insert(new Student(105, "Charlie"));
        bst.insert(new Student(101, "Duplicate Bob"));

        bst.printInorder();
        System.out.println("Search 101: " + bst.search(101));

        bst.delete(101);
        bst.printInorder();
    }
}