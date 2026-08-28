import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LibraryBookBst {

    public static class Book {
        String isbn;
        String title;
        String author;
        boolean available;

        public Book(String isbn, String title, String author, boolean available) {
            this.isbn = isbn;
            this.title = title;
            this.author = author;
            this.available = available;
        }

        @Override
        public String toString() {
            return isbn + "|" + title + "|" + author + "|" + (available ? "Available" : "Borrowed");
        }
    }

    private static class Node {
        Book book;
        Node left, right;
        Node(Book book) { this.book = book; }
    }

    private Node root;

    public boolean add(Book book) {
        if (book == null || find(book.isbn) != null) {
            return false;
        }
        root = addRec(root, book);
        return true;
    }

    private Node addRec(Node node, Book book) {
        if (node == null) return new Node(book);
        int cmp = book.isbn.compareTo(node.book.isbn);
        if (cmp < 0) node.left = addRec(node.left, book);
        else if (cmp > 0) node.right = addRec(node.right, book);
        return node;
    }

    public Book find(String isbn) {
        if (isbn == null) return null;
        Node curr = root;
        while (curr != null) {
            int cmp = isbn.compareTo(curr.book.isbn);
            if (cmp == 0) return curr.book;
            else if (cmp < 0) curr = curr.left;
            else curr = curr.right;
        }
        return null;
    }

    public boolean borrow(String isbn) {
        Book b = find(isbn);
        if (b != null && b.available) {
            b.available = false;
            return true;
        }
        return false;
    }

    public boolean returnBook(String isbn) {
        Book b = find(isbn);
        if (b != null && !b.available) {
            b.available = true;
            return true;
        }
        return false;
    }

    public boolean remove(String isbn) {
        Book b = find(isbn);
        if (b == null || !b.available) {
            return false;
        }
        root = removeRec(root, isbn);
        return true;
    }

    private Node removeRec(Node node, String isbn) {
        if (node == null) return null;
        int cmp = isbn.compareTo(node.book.isbn);
        if (cmp < 0) {
            node.left = removeRec(node.left, isbn);
        } else if (cmp > 0) {
            node.right = removeRec(node.right, isbn);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node succ = node.right;
            while (succ.left != null) succ = succ.left;
            node.book = succ.book;
            node.right = removeRec(node.right, succ.book.isbn);
        }
        return node;
    }

    public List<Book> rangeQuery(String lowIsbn, String highIsbn) {
        if (lowIsbn == null || highIsbn == null || lowIsbn.compareTo(highIsbn) > 0) {
            return Collections.emptyList();
        }
        List<Book> res = new ArrayList<>();
        rangeRec(root, lowIsbn, highIsbn, res);
        return res;
    }

    private void rangeRec(Node node, String low, String high, List<Book> res) {
        if (node == null) return;
        if (node.book.isbn.compareTo(low) > 0) rangeRec(node.left, low, high, res);
        if (node.book.isbn.compareTo(low) >= 0 && node.book.isbn.compareTo(high) <= 0) res.add(node.book);
        if (node.book.isbn.compareTo(high) < 0) rangeRec(node.right, low, high, res);
    }

    public void inorderReport() {
        System.out.println("--- Library Catalog ---");
        inorderRec(root);
    }

    private void inorderRec(Node node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.println(node.book);
            inorderRec(node.right);
        }
    }

    public static void main(String[] args) {
        LibraryBookBst lib = new LibraryBookBst();
        lib.add(new Book("978-0134685991", "Effective Java", "Joshua Bloch", true));
        lib.add(new Book("978-0321356680", "Java Concurrency in Practice", "Brian Goetz", true));
        lib.add(new Book("978-0596009205", "Head First Design Patterns", "Eric Freeman", true));

        lib.borrow("978-0134685991");
        System.out.println("Remove borrowed book (should fail): " + lib.remove("978-0134685991"));

        lib.returnBook("978-0134685991");
        System.out.println("Remove returned book (should succeed): " + lib.remove("978-0134685991"));

        lib.inorderReport();
    }
}