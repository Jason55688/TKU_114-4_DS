import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Q12_StudentBstSystem {

    public static class Student {
        private final int id;
        private final String name;
        private int score;

        public Student(int id, String name, int score) {
            if (id <= 0 || name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Invalid id or name");
            }
            this.id = id;
            this.name = name.trim();
            if (score < 0) {
                this.score = 0;
            } else if (score > 100) {
                this.score = 100;
            } else {
                this.score = score;
            }
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            if (score < 0) {
                this.score = 0;
            } else if (score > 100) {
                this.score = 100;
            } else {
                this.score = score;
            }
        }

        @Override
        public String toString() {
            return id + "|" + name + "|" + score;
        }
    }

    private static class Node {
        Student student;
        Node left;
        Node right;

        Node(Student student) {
            this.student = student;
        }
    }

    private Node root;

    public boolean add(Student student) {
        if (student == null || find(student.getId()) != null) {
            return false;
        }
        root = addRec(root, student);
        return true;
    }

    private Node addRec(Node node, Student student) {
        if (node == null) {
            return new Node(student);
        }
        if (student.getId() < node.student.getId()) {
            node.left = addRec(node.left, student);
        } else if (student.getId() > node.student.getId()) {
            node.right = addRec(node.right, student);
        }
        return node;
    }

    public Student find(int id) {
        Node curr = root;
        while (curr != null) {
            if (id == curr.student.getId()) {
                return curr.student;
            } else if (id < curr.student.getId()) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        return null;
    }

    public boolean updateScore(int id, int score) {
        Student s = find(id);
        if (s != null) {
            s.setScore(score);
            return true;
        }
        return false;
    }

    public boolean remove(int id) {
        if (find(id) == null) {
            return false;
        }
        root = removeRec(root, id);
        return true;
    }

    private Node removeRec(Node node, int id) {
        if (node == null) {
            return null;
        }

        if (id < node.student.getId()) {
            node.left = removeRec(node.left, id);
        } else if (id > node.student.getId()) {
            node.right = removeRec(node.right, id);
        } else {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }

            Node successor = getMin(node.right);
            node.student = successor.student;
            node.right = removeRec(node.right, successor.student.getId());
        }
        return node;
    }

    private Node getMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public List<Student> studentsBetween(int lowId, int highId) {
        if (lowId > highId) {
            return Collections.emptyList();
        }
        List<Student> result = new ArrayList<>();
        rangeHelper(root, lowId, highId, result);
        return result;
    }

    private void rangeHelper(Node node, int lowId, int highId, List<Student> result) {
        if (node == null) {
            return;
        }
        if (node.student.getId() > lowId) {
            rangeHelper(node.left, lowId, highId, result);
        }
        if (node.student.getId() >= lowId && node.student.getId() <= highId) {
            result.add(node.student);
        }
        if (node.student.getId() < highId) {
            rangeHelper(node.right, lowId, highId, result);
        }
    }

    public List<Student> inorder() {
        if (root == null) {
            return Collections.emptyList();
        }
        List<Student> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }

    private void inorderHelper(Node node, List<Student> result) {
        if (node == null) {
            return;
        }
        inorderHelper(node.left, result);
        result.add(node.student);
        inorderHelper(node.right, result);
    }
}