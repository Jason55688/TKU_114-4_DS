import java.util.*;

public class LoginActivityReport {

    public static class LoginRecord {
        String account;
        String ip;
        String timestamp;

        public LoginRecord(String account, String ip, String timestamp) {
            this.account = account;
            this.ip = ip;
            this.timestamp = timestamp;
        }
    }

    public static void generateReport(List<LoginRecord> records, int duplicateThreshold) {
        Map<String, Integer> loginCounts = new HashMap<>();
        Map<String, Set<String>> userIps = new HashMap<>();

        for (LoginRecord r : records) {
            loginCounts.put(r.account, loginCounts.getOrDefault(r.account, 0) + 1);
            userIps.computeIfAbsent(r.account, k -> new HashSet<>()).add(r.ip);
        }

        System.out.println("===== 帳號登入與 IP 統計 =====");
        for (String user : loginCounts.keySet()) {
            int count = loginCounts.get(user);
            int uniqueIps = userIps.get(user).size();
            System.out.printf("帳號: %-10s | 登入次數: %2d | 獨立 IP 數: %2d | IP 清單: %s%n",
                    user, count, uniqueIps, userIps.get(user));
        }

        System.out.println("\n===== 異常重複登入名單 (門檻 >= " + duplicateThreshold + " 次) =====");
        boolean foundAnomaly = false;
        for (Map.Entry<String, Integer> entry : loginCounts.entrySet()) {
            if (entry.getValue() >= duplicateThreshold) {
                foundAnomaly = true;
                System.out.printf("[警告] 帳號 %s 異常頻繁登入，共 %d 次 (來自 %d 個不同 IP)%n",
                        entry.getKey(), entry.getValue(), userIps.get(entry.getKey()).size());
            }
        }
        if (!foundAnomaly) {
            System.out.println("無異常登入行為。");
        }
    }

    public static void main(String[] args) {
        List<LoginRecord> logs = Arrays.asList(
            new LoginRecord("alice", "192.168.1.10", "10:00:01"),
            new LoginRecord("bob", "10.0.0.1", "10:01:05"),
            new LoginRecord("alice", "192.168.1.10", "10:01:20"),
            new LoginRecord("alice", "140.112.1.1", "10:02:15"),
            new LoginRecord("charlie", "172.16.0.4", "10:05:00"),
            new LoginRecord("alice", "192.168.1.10", "10:06:30"),
            new LoginRecord("bob", "10.0.0.2", "10:07:00")
        );

        generateReport(logs, 3);
    }
}