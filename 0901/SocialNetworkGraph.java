import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SocialNetworkGraph {

    // Adjacency List: User -> Set of Friends
    private final Map<String, Set<String>> adjList = new HashMap<>();

    public void addUser(String user) {
        adjList.putIfAbsent(user, new HashSet<>());
    }

    public void addFriendship(String user1, String user2) {
        if (user1.equals(user2)) return;
        addUser(user1);
        addUser(user2);
        adjList.get(user1).add(user2);
        adjList.get(user2).add(user1);
    }

    public void removeFriendship(String user1, String user2) {
        if (adjList.containsKey(user1)) adjList.get(user1).remove(user2);
        if (adjList.containsKey(user2)) adjList.get(user2).remove(user1);
    }

    public Set<String> getMutualFriends(String user1, String user2) {
        if (!adjList.containsKey(user1) || !adjList.containsKey(user2)) {
            return Collections.emptySet();
        }
        Set<String> mutual = new HashSet<>(adjList.get(user1));
        mutual.retainAll(adjList.get(user2));
        return mutual;
    }

    public List<String> getIsolatedUsers() {
        List<String> isolated = new ArrayList<>();
        for (Map.Entry<String, Set<String>> entry : adjList.entrySet()) {
            if (entry.getValue().isEmpty()) {
                isolated.add(entry.getKey());
            }
        }
        return isolated;
    }

    public static void main(String[] args) {
        SocialNetworkGraph sn = new SocialNetworkGraph();
        sn.addUser("Alice");
        sn.addUser("Bob");
        sn.addUser("Charlie");
        sn.addUser("David");
        sn.addUser("Eva"); // 孤立使用者

        sn.addFriendship("Alice", "Bob");
        sn.addFriendship("Alice", "Charlie");
        sn.addFriendship("Bob", "Charlie");
        sn.addFriendship("Bob", "David");

        System.out.println("Mutual friends (Alice, Bob): " + sn.getMutualFriends("Alice", "Bob")); // [Charlie]
        System.out.println("Isolated users: " + sn.getIsolatedUsers()); // [Eva]

        sn.removeFriendship("Alice", "Bob");
        System.out.println("After removing friendship, mutual friends: " + sn.getMutualFriends("Alice", "Bob"));
    }
}