public class ScoreRangeBst {
    private static class Record {
        int score;
        int studentId;

        Record(int score, int studentId) {
            this.score = score;
            this.studentId = studentId;
        }

        int compareTo(Record other) {
            if (this.score != other.score) {
                return Integer.compare(this.score, other.score);
            }
            return Integer.compare(this.studentId, other.studentId);
        }

        @Override
        public String toString() {
            return "Score: " + score + " (ID: " + studentId + ")";
        }
    }

    private static class Node {
        Record record;
        Node left, right;

        Node(Record record) {
            this.record = record;
        }
    }

    private Node root;

    public void insert(int score, int studentId) {
        root = insertRec(root, new Record(score, studentId));
    }

    private Node insertRec(Node node, Record rec) {
        if (node == null) return new Node(rec);
        int cmp = rec.compareTo(node.record);
        if (cmp < 0) node.left = insertRec(node.left, rec);
        else if (cmp > 0) node.right = insertRec(node.right, rec);
        return node;
    }

    public void printRange(int lowScore, int highScore) {
        if (lowScore > highScore) {
            int t = lowScore;
            lowScore = highScore;
            highScore = t;
        }
        System.out.println("Scores in range [" + lowScore + ", " + highScore + "]:");
        printRangeRec(root, lowScore, highScore);
        System.out.println();
    }

    private void printRangeRec(Node node, int low, int high) {
        if (node == null) return;
        if (node.record.score > low) {
            printRangeRec(node.left, low, high);
        }
        if (node.record.score >= low && node.record.score <= high) {
            System.out.println("  " + node.record);
        }
        if (node.record.score < high) {
            printRangeRec(node.right, low, high);
        }
    }

    public static void main(String[] args) {
        ScoreRangeBst bst = new ScoreRangeBst();
        bst.insert(85, 1001);
        bst.insert(92, 1002);
        bst.insert(85, 1003);
        bst.insert(70, 1004);
        bst.insert(60, 1005);
        bst.insert(95, 1006);

        bst.printRange(80, 95);
    }
}