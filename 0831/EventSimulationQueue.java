import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class EventSimulationQueue {

    public static class SimEvent implements Comparable<SimEvent> {
        int eventId;
        int time;
        String type;
        int sequence;

        public SimEvent(int eventId, int time, String type, int sequence) {
            this.eventId = eventId;
            this.time = time;
            this.type = type;
            this.sequence = sequence;
        }

        @Override
        public int compareTo(SimEvent o) {
            if (this.time != o.time) {
                return Integer.compare(this.time, o.time);
            }
            return Integer.compare(this.sequence, o.sequence);
        }

        @Override
        public String toString() {
            return String.format("Event[id=%d, time=%d, type='%s', seq=%d]", eventId, time, type, sequence);
        }
    }

    private final PriorityQueue<SimEvent> pq = new PriorityQueue<>();
    private final List<String> executionLogs = new ArrayList<>();

    public void scheduleEvent(int id, int time, String type, int seq) {
        pq.offer(new SimEvent(id, time, type, seq));
    }

    public boolean cancelEvent(int eventId) {
        for (SimEvent e : pq) {
            if (e.eventId == eventId) {
                pq.remove(e);
                executionLogs.add("取消事件: ID=" + eventId);
                return true;
            }
        }
        return false;
    }

    public void runSimulation() {
        while (!pq.isEmpty()) {
            SimEvent current = pq.poll();
            String log = "執行事件 -> 時間: " + current.time + " | 序號: " + current.sequence + 
                         " | 類型: " + current.type + " | ID: " + current.eventId;
            executionLogs.add(log);
        }
    }

    public void printLogs() {
        System.out.println("===== 模擬執行紀錄 =====");
        for (String log : executionLogs) {
            System.out.println(log);
        }
    }

    public static void main(String[] args) {
        EventSimulationQueue simulator = new EventSimulationQueue();

        simulator.scheduleEvent(1, 10, "PACKET_ARRIVE", 1);
        simulator.scheduleEvent(2, 5,  "TIMER_EXPIRE", 1);
        simulator.scheduleEvent(3, 10, "USER_CLICK",   2);
        simulator.scheduleEvent(4, 5,  "SENSOR_READ",  2);
        simulator.scheduleEvent(5, 20, "SHUTDOWN",     1);
        simulator.scheduleEvent(6, 12, "HEARTBEAT",    1);

        // 取消事件 3
        simulator.cancelEvent(3);

        // 執行模擬
        simulator.runSimulation();
        simulator.printLogs();
    }
}