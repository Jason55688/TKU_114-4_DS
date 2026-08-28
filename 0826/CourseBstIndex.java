import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CourseBstIndex {

    public static class Course {
        String courseCode;
        String courseName;
        int credit;

        public Course(String courseCode, String courseName, int credit) {
            if (credit < 1 || credit > 6) {
                throw new IllegalArgumentException("Credit must be between 1 and 6");
            }
            this.courseCode = courseCode;
            this.courseName = courseName;
            this.credit = credit;
        }

        @Override
        public String toString() {
            return courseCode + "|" + courseName + "|" + credit;
        }
    }

    private static class Node {
        Course course;
        Node left, right;

        Node(Course course) {
            this.course = course;
        }
    }

    private Node root;

    public boolean add(Course course) {
        if (course == null || find(course.courseCode) != null) {
            return false;
        }
        root = addRec(root, course);
        return true;
    }

    private Node addRec(Node node, Course course) {
        if (node == null) return new Node(course);
        int cmp = course.courseCode.compareTo(node.course.courseCode);
        if (cmp < 0) node.left = addRec(node.left, course);
        else if (cmp > 0) node.right = addRec(node.right, course);
        return node;
    }

    public Course find(String courseCode) {
        if (courseCode == null) return null;
        Node curr = root;
        while (curr != null) {
            int cmp = courseCode.compareTo(curr.course.courseCode);
            if (cmp == 0) return curr.course;
            else if (cmp < 0) curr = curr.left;
            else curr = curr.right;
        }
        return null;
    }

    public boolean updateCredit(String courseCode, int newCredit) {
        if (newCredit < 1 || newCredit > 6) {
            return false;
        }
        Course c = find(courseCode);
        if (c != null) {
            c.credit = newCredit;
            return true;
        }
        return false;
    }

    public boolean remove(String courseCode) {
        if (find(courseCode) == null) {
            return false;
        }
        root = removeRec(root, courseCode);
        return true;
    }

    private Node removeRec(Node node, String courseCode) {
        if (node == null) return null;
        int cmp = courseCode.compareTo(node.course.courseCode);
        if (cmp < 0) {
            node.left = removeRec(node.left, courseCode);
        } else if (cmp > 0) {
            node.right = removeRec(node.right, courseCode);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node succ = node.right;
            while (succ.left != null) succ = succ.left;
            node.course = succ.course;
            node.right = removeRec(node.right, succ.course.courseCode);
        }
        return node;
    }

    public List<Course> rangeQuery(String lowCode, String highCode) {
        if (lowCode == null || highCode == null || lowCode.compareTo(highCode) > 0) {
            return Collections.emptyList();
        }
        List<Course> list = new ArrayList<>();
        rangeRec(root, lowCode, highCode, list);
        return list;
    }

    private void rangeRec(Node node, String low, String high, List<Course> list) {
        if (node == null) return;
        if (node.course.courseCode.compareTo(low) > 0) {
            rangeRec(node.left, low, high, list);
        }
        if (node.course.courseCode.compareTo(low) >= 0 && node.course.courseCode.compareTo(high) <= 0) {
            list.add(node.course);
        }
        if (node.course.courseCode.compareTo(high) < 0) {
            rangeRec(node.right, low, high, list);
        }
    }

    public void inorderReport() {
        System.out.println("--- Course Inorder Report ---");
        inorderRec(root);
    }

    private void inorderRec(Node node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.println(node.course);
            inorderRec(node.right);
        }
    }

    public static void main(String[] args) {
        CourseBstIndex bst = new CourseBstIndex();
        bst.add(new Course("CS101", "Intro to CS", 3));
        bst.add(new Course("MATH201", "Calculus", 4));
        bst.add(new Course("ENG101", "English", 2));
        bst.add(new Course("CS102", "Data Structures", 3));

        System.out.println("Duplicate add CS101: " + bst.add(new Course("CS101", "Dup", 3)));
        bst.updateCredit("CS101", 4);
        bst.inorderReport();

        System.out.println("\nRange Query [CS000, CS200]:");
        for (Course c : bst.rangeQuery("CS000", "CS200")) {
            System.out.println(c);
        }

        bst.remove("MATH201");
        System.out.println("\nAfter removing MATH201:");
        bst.inorderReport();
    }
}