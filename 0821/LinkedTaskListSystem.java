class Task {

    private String id;
    private String title;

    public Task(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + " - " + title;
    }
}

class TaskNode {

    Task task;
    TaskNode next;

    public TaskNode(Task task) {
        this.task = task;
    }
}

class TaskLinkedList {

    private TaskNode head;
    private int size;

    // 新增到前端
    public boolean addFirst(Task task) {

        if (task == null || findById(task.getId()) != null) {
            return false;
        }

        TaskNode node = new TaskNode(task);

        node.next = head;
        head = node;

        size++;

        return true;
    }

    // 新增到尾端
    public boolean addLast(Task task) {

        if (task == null || findById(task.getId()) != null) {
            return false;
        }

        TaskNode node = new TaskNode(task);

        if (head == null) {

            head = node;

        } else {

            TaskNode current = head;

            while (current.next != null) {
                current = current.next;
            }

            current.next = node;
        }

        size++;

        return true;
    }

    // 依 ID 查詢
    public Task findById(String id) {

        TaskNode current = head;

        while (current != null) {

            if (current.task.getId().equals(id)) {
                return current.task;
            }

            current = current.next;
        }

        return null;
    }

    // 插入指定節點後面
    public boolean insertAfter(
            String existingId,
            Task task) {

        if (task == null
                || findById(task.getId()) != null) {

            return false;
        }

        TaskNode current = head;

        while (current != null) {

            if (current.task.getId().equals(existingId)) {

                TaskNode newNode =
                        new TaskNode(task);

                newNode.next = current.next;
                current.next = newNode;

                size++;

                return true;
            }

            current = current.next;
        }

        return false;
    }

    // 刪除指定 ID
    public boolean removeById(String id) {

        if (head == null) {
            return false;
        }

        // 刪除 head
        if (head.task.getId().equals(id)) {

            head = head.next;

            size--;

            return true;
        }

        TaskNode current = head;

        while (current.next != null) {

            if (current.next.task
                    .getId()
                    .equals(id)) {

                current.next =
                        current.next.next;

                size--;

                return true;
            }

            current = current.next;
        }

        return false;
    }

    public int size() {
        return size;
    }

    public void printAll() {

        if (head == null) {

            System.out.println("(empty)");
            return;
        }

        TaskNode current = head;

        while (current != null) {

            System.out.println(current.task);

            current = current.next;
        }
    }
}

public class LinkedTaskListSystem {

    public static void main(String[] args) {

        TaskLinkedList tasks =
                new TaskLinkedList();

        System.out.println("=== Empty List ===");
        tasks.printAll();

        // 新增
        tasks.addLast(
                new Task("T001", "Design"));

        tasks.addLast(
                new Task("T002", "Coding"));

        tasks.addLast(
                new Task("T003", "Testing"));

        tasks.addFirst(
                new Task("T000", "Planning"));

        System.out.println("\n=== Initial List ===");
        tasks.printAll();

        System.out.println(
                "\nSize = " + tasks.size());

        // 重複 ID
        System.out.println(
                "\nDuplicate