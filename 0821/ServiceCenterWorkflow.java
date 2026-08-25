import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

class ServiceTicket {

    private String ticketId;
    private String description;

    public ServiceTicket(
            String ticketId,
            String description) {

        this.ticketId = ticketId;
        this.description = description;
    }

    public String getTicketId() {
        return ticketId;
    }

    @Override
    public String toString() {

        return ticketId
                + " - "
                + description;
    }
}

public class ServiceCenterWorkflow {

    private Map<String, ServiceTicket> ticketMap =
            new HashMap<>();

    private Set<String> ticketIds =
            new HashSet<>();

    // Waiting Queue
    private Deque<ServiceTicket> waitingQueue =
            new ArrayDeque<>();

    // Completed Stack
    private Deque<ServiceTicket> completedStack =
            new ArrayDeque<>();

    // 建立工單
    public boolean createTicket(
            String ticketId,
            String description) {

        if (ticketIds.contains(ticketId)) {
            return false;
        }

        ServiceTicket ticket =
                new ServiceTicket(
                        ticketId,
                        description);

        ticketIds.add(ticketId);
        ticketMap.put(ticketId, ticket);

        waitingQueue.offerLast(ticket);

        return true;
    }

    // 處理下一張工單
    public ServiceTicket processNext() {

        ServiceTicket ticket =
                waitingQueue.pollFirst();

        if (ticket != null) {
            completedStack.push(ticket);
        }

        return ticket;
    }

    // 取消未處理工單
    public boolean cancelWaiting(
            String ticketId) {

        Iterator<ServiceTicket> iterator =
                waitingQueue.iterator();

        while (iterator.hasNext()) {

            ServiceTicket ticket =
                    iterator.next();

            if (ticket.getTicketId()
                    .equals(ticketId)) {

                iterator.remove();

                ticketIds.remove(ticketId);
                ticketMap.remove(ticketId);

                return true;
            }
        }

        return false;
    }

    // Undo 最近完成工單
    public ServiceTicket undoLastCompletion() {

        if (completedStack.isEmpty()) {
            return null;
        }

        ServiceTicket ticket =
                completedStack.pop();

        // 放回等待佇列前端
        waitingQueue.offerFirst(ticket);

        return ticket;
    }

    // 查詢工單
    public ServiceTicket findById(
            String ticketId) {

        return ticketMap.get(ticketId);
    }

    // 顯示統計
    public void printSummary() {

        System.out.println(
                "\n=== Summary ===");

        System.out.println(
                "Total Tickets = "
                        + ticketMap.size());

        System.out.println(
                "Waiting = "
                        + waitingQueue.size());

        System.out.println(
                "Completed = "
                        + completedStack.size());

        System.out.println(
                "Next Waiting = "
                        + waitingQueue.peekFirst());
    }

    public static void main(String[] args) {

        ServiceCenterWorkflow system =
                new ServiceCenterWorkflow();

        System.out.println(
                "Create T001 = "
                        + system.createTicket(
                        "T001",
                        "Printer Error"));

        System.out.println(
                "Create T002 = "
                        + system.createTicket(
                        "T002",
                        "Network Issue"));

        System.out.println(
                "Create T003 = "
                        + system.createTicket(
                        "T003",
                        "PC Upgrade"));

        // 重複 ID
        System.out.println(
                "Duplicate T001 = "
                        + system.createTicket(
                        "T001",
                        "Duplicate"));

        System.out.println();

        System.out.println(
                "Find T002 = "
                        + system.findById("T002"));

        System.out.println();

        System.out.println(
                "Process = "
                        + system.processNext());

        System.out.println(
                "Process = "
                        + system.processNext());

        system.printSummary();

        // 取消等待中的工單
        System.out.println(
                "\nCancel T003 = "
                        + system.cancelWaiting("T003"));

        // 取消不存在
        System.out.println(
                "Cancel T999 = "
                        + system.cancelWaiting("T999"));

        system.printSummary();

        // 連續兩次 Undo
        System.out.println(
                "\nUndo = "
                        + system.undoLastCompletion());

        System.out.println(
                "Undo = "
                        + system.undoLastCompletion());

        // Empty Undo
        System.out.println(
                "Undo = "
                        + system.undoLastCompletion());

        system.printSummary();

        System.out.println();

        System.out.println(
                "Process = "
                        + system.processNext());

        System.out.println(
                "Process = "
                        + system.processNext());

        // 空 Queue 測試
        System.out.println(
                "Process Empty = "
                        + system.processNext());

        system.printSummary();
    }
}