import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class DeliveryOrder {

    private String deliveryId;
    private String customerName;

    public DeliveryOrder(
            String deliveryId,
            String customerName) {

        this.deliveryId = deliveryId;
        this.customerName = customerName;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    @Override
    public String toString() {

        return deliveryId
                + " - "
                + customerName;
    }
}

public class DeliveryWorkflowSystem {

    private Map<String, DeliveryOrder> orderMap =
            new HashMap<>();

    private Queue<DeliveryOrder> waitingQueue =
            new LinkedList<>();

    private Deque<DeliveryOrder> completedStack =
            new ArrayDeque<>();

    // 新增配送
    public boolean addDelivery(
            String deliveryId,
            String customerName) {

        if (orderMap.containsKey(deliveryId)) {
            return false;
        }

        DeliveryOrder order =
                new DeliveryOrder(
                        deliveryId,
                        customerName);

        orderMap.put(deliveryId, order);
        waitingQueue.offer(order);

        return true;
    }

    // 查詢配送
    public DeliveryOrder findDelivery(
            String deliveryId) {

        return orderMap.get(deliveryId);
    }

    // 處理配送(FIFO)
    public DeliveryOrder processDelivery() {

        DeliveryOrder order =
                waitingQueue.poll();

        if (order != null) {
            completedStack.push(order);
        }

        return order;
    }

    // Undo 最近完成配送
    public DeliveryOrder undoLastDelivery() {

        if (completedStack.isEmpty()) {
            return null;
        }

        DeliveryOrder order =
                completedStack.pop();

        waitingQueue.offer(order);

        return order;
    }

    // 顯示統計
    public void showStatistics() {

        System.out.println("\n=== Statistics ===");

        System.out.println(
                "Total Orders = "
                        + orderMap.size());

        System.out.println(
                "Waiting Orders = "
                        + waitingQueue.size());

        System.out.println(
                "Completed Orders = "
                        + completedStack.size());
    }

    public static void main(String[] args) {

        DeliveryWorkflowSystem system =
                new DeliveryWorkflowSystem();

        System.out.println(
                "Add D001 = "
                        + system.addDelivery(
                        "D001",
                        "Amy"));

        System.out.println(
                "Add D002 = "
                        + system.addDelivery(
                        "D002",
                        "Ben"));

        System.out.println(
                "Add D003 = "
                        + system.addDelivery(
                        "D003",
                        "Cara"));

        // 重複編號測試
        System.out.println(
                "Duplicate D001 = "
                        + system.addDelivery(
                        "D001",
                        "David"));

        System.out.println();

        System.out.println(
                "Find D002 = "
                        + system.findDelivery("D002"));

        System.out.println();

        System.out.println(
                "Process = "
                        + system.processDelivery());

        System.out.println(
                "Process = "
                        + system.processDelivery());

        system.showStatistics();

        System.out.println();

        System.out.println(
                "Undo = "
                        + system.undoLastDelivery());

        system.showStatistics();

        System.out.println();

        System.out.println(
                "Process = "
                        + system.processDelivery());

        System.out.println(
                "Process = "
                        + system.processDelivery());

        System.out.println(
                "Process Empty = "
                        + system.processDelivery());

        System.out.println();

        System.out.println(
                "Undo = "
                        + system.undoLastDelivery());

        system.showStatistics();
    }
}