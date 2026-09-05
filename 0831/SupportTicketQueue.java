import java.util.PriorityQueue;

public class SupportTicketQueue {
    public static class Ticket implements Comparable<Ticket> {
        String id;
        int severity;
        int createdOrder;

        public Ticket(String id, int severity, int createdOrder) {
            this.id = id;
            this.severity = severity;
            this.createdOrder = createdOrder;
        }

        @Override
        public int compareTo(Ticket other) {
            // severity 越大越優先
            if (this.severity != other.severity) {
                return Integer.compare(other.severity, this.severity);
            }
            // severity 相同時 createdOrder 越小越早
            return Integer.compare(this.createdOrder, other.createdOrder);
        }

        @Override
        public String toString() {
            return id + "|" + severity + "|" + createdOrder;
        }
    }

    public static void main(String[] args) {
        PriorityQueue<Ticket> pq = new PriorityQueue<>();

        pq.offer(new Ticket("TCK-001", 2, 1));
        pq.offer(new Ticket("TCK-002", 5, 2));
        pq.offer(new Ticket("TCK-003", 5, 3));
        pq.offer(new Ticket("TCK-004", 1, 4));
        pq.offer(new Ticket("TCK-005", 2, 5));

        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }
    }
}