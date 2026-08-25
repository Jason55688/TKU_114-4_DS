import java.util.ArrayDeque;
import java.util.Deque;

class Customer {

    private String customerId;
    private String name;

    public Customer(
            String customerId,
            String name) {

        this.customerId = customerId;
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {

        return customerId + " - " + name;
    }
}

public class CounterWaitingQueue {

    private Deque<Customer> queue =
            new ArrayDeque<>();

    // 加入等候
    public void join(Customer customer) {

        if (customer != null) {
            queue.offerLast(customer);
        }
    }

    // 查看下一位
    public Customer nextCustomer() {

        return queue.peekFirst();
    }

    // 服務下一位
    public Customer serve() {

        return queue.pollFirst();
    }

    // 顯示等待數
    public int waitingCount() {

        return queue.size();
    }

    public static void main(String[] args) {

        CounterWaitingQueue counter =
                new CounterWaitingQueue();

        counter.join(
                new Customer("C001", "Amy"));

        counter.join(
                new Customer("C002", "Ben"));

        counter.join(
                new Customer("C003", "Cara"));

        System.out.println(
                "Waiting Count = "
                        + counter.waitingCount());

        System.out.println(
                "Next Customer = "
                        + counter.nextCustomer());

        System.out.println();

        System.out.println(
                "Serve = "
                        + counter.serve());

        System.out.println(
                "Serve = "
                        + counter.serve());

        System.out.println();

        System.out.println(
                "Next Customer = "
                        + counter.nextCustomer());

        System.out.println(
                "Waiting Count = "
                        + counter.waitingCount());

        System.out.println();

        System.out.println(
                "Serve = "
                        + counter.serve());

        // 空佇列測試
        System.out.println(
                "Serve Empty = "
                        + counter.serve());

        System.out.println(
                "Next Empty = "
                        + counter.nextCustomer());

        System.out.println(
                "Waiting Count = "
                        + counter.waitingCount());
    }
}