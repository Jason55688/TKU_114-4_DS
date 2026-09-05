import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class EmergencyTriageQueue {

    public static class Patient implements Comparable<Patient> {
        int urgency;       // 危急程度 (數字越小越優先，如 1 級最危急)
        int arrivalOrder;  // 到院順序 (序號越小越早)
        String medicalId;  // 病歷號

        public Patient(int urgency, int arrivalOrder, String medicalId) {
            this.urgency = urgency;
            this.arrivalOrder = arrivalOrder;
            this.medicalId = medicalId;
        }

        @Override
        public int compareTo(Patient o) {
            if (this.urgency != o.urgency) {
                return Integer.compare(this.urgency, o.urgency);
            }
            if (this.arrivalOrder != o.arrivalOrder) {
                return Integer.compare(this.arrivalOrder, o.arrivalOrder);
            }
            return this.medicalId.compareTo(o.medicalId);
        }

        @Override
        public String toString() {
            return String.format("[病歷號: %s, 危急級數: %d, 到院順位: %d]", medicalId, urgency, arrivalOrder);
        }
    }

    private final PriorityQueue<Patient> queue = new PriorityQueue<>();

    public void register(int urgency, int arrivalOrder, String medicalId) {
        Patient p = new Patient(urgency, arrivalOrder, medicalId);
        queue.offer(p);
        System.out.println("掛號完成: " + p);
    }

    public Patient peekNext() {
        if (queue.isEmpty()) {
            throw new NoSuchElementException("目前候診隊列為空，無下一位病患");
        }
        return queue.peek();
    }

    public Patient callNext() {
        if (queue.isEmpty()) {
            System.out.println("【叫號失敗】候診隊列已空，無病患可叫號。");
            return null;
        }
        Patient next = queue.poll();
        System.out.println("【叫號】請病患看診: " + next);
        return next;
    }

    public int getWaitingCount() {
        return queue.size();
    }

    public static void main(String[] args) {
        EmergencyTriageQueue triage = new EmergencyTriageQueue();

        triage.register(3, 1, "M001");
        triage.register(1, 2, "M002");
        triage.register(2, 3, "M003");
        triage.register(1, 4, "M004");
        triage.register(2, 5, "M005");

        System.out.println("\n目前候診人數: " + triage.getWaitingCount());
        System.out.println("下一位預計看診: " + triage.peekNext());

        System.out.println("\n開始叫號流程:");
        while (triage.getWaitingCount() > 0) {
            triage.callNext();
        }

        // 空佇列處理測試
        System.out.println("\n測試空佇列叫號與查看:");
        triage.callNext();
        try {
            triage.peekNext();
        } catch (NoSuchElementException e) {
            System.out.println("攔截異常: " + e.getMessage());
        }
    }
}