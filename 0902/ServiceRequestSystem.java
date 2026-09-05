import java.util.*;

public class ServiceRequestSystem {

    public static class ServiceRequest implements Comparable<ServiceRequest> {
        String id;
        int priority;      // 優先級數字越大越先處理
        long arrivalTime;  // 提交時間戳

        public ServiceRequest(String id, int priority, long arrivalTime) {
            this.id = id;
            this.priority = priority;
            this.arrivalTime = arrivalTime;
        }

        @Override
        public int compareTo(ServiceRequest o) {
            if (this.priority != o.priority) {
                return Integer.compare(o.priority, this.priority);
            }
            return Long.compare(this.arrivalTime, o.arrivalTime);
        }

        @Override
        public String toString() {
            return String.format("Request[id=%s, pri=%d, time=%d]", id, priority, arrivalTime);
        }
    }

    private final Map<String, ServiceRequest> requestMap = new HashMap<>();
    private final PriorityQueue<ServiceRequest> pq = new PriorityQueue<>();

    public void addRequest(String id, int priority, long time) {
        if (requestMap.containsKey(id)) {
            System.err.println("Request ID 已存在: " + id);
            return;
        }
        ServiceRequest req = new ServiceRequest(id, priority, time);
        requestMap.put(id, req);
        pq.offer(req);
    }

    public ServiceRequest getById(String id) {
        return requestMap.get(id);
    }

    public ServiceRequest processNext() {
        if (pq.isEmpty()) {
            return null;
        }
        ServiceRequest next = pq.poll();
        requestMap.remove(next.id); // 保持一致性
        return next;
    }

    public boolean cancelRequest(String id) {
        ServiceRequest req = requestMap.remove(id);
        if (req != null) {
            pq.remove(req); // 同步自 PriorityQueue 移除
            return true;
        }
        return false;
    }

    public int size() {
        return requestMap.size();
    }

    public static void main(String[] args) {
        ServiceRequestSystem sys = new ServiceRequestSystem();

        sys.addRequest("R01", 3, 100);
        sys.addRequest("R02", 5, 102);
        sys.addRequest("R03", 5, 101);
        sys.addRequest("R04", 1, 105);

        System.out.println("依 ID 查詢 R03: " + sys.getById("R03"));

        // 取消 R02 (最高優先級之一)，檢驗雙結構同步一致性
        System.out.println("取消 R02 成功: " + sys.cancelRequest("R02"));
        System.out.println("再次依 ID 查詢 R02 (應為 null): " + sys.getById("R02"));

        System.out.println("\n開始依序叫號處理:");
        while (sys.size() > 0) {
            System.out.println("處理: " + sys.processNext());
        }

        // 邊界測試：空佇列處理與取消不存在項目
        System.out.println("空佇列取出下一筆: " + sys.processNext());
        System.out.println("取消不存在項目 (XYZ): " + sys.cancelRequest("XYZ"));
    }
}