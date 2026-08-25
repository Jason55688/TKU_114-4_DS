import java.util.ArrayDeque;
import java.util.Deque;

public class TextEditorHistory {

    private Deque<String> undoStack =
            new ArrayDeque<>();

    private Deque<String> redoStack =
            new ArrayDeque<>();

    // 新增操作
    public void performAction(String action) {

        if (action == null || action.isBlank()) {
            return;
        }

        undoStack.push(action);

        // 新操作後清空 redo
        redoStack.clear();

        printState("Perform: " + action);
    }

    // Undo
    public void undo() {

        if (undoStack.isEmpty()) {

            printState("Undo Failed");
            return;
        }

        String action =
                undoStack.pop();

        redoStack.push(action);

        printState("Undo: " + action);
    }

    // Redo
    public void redo() {

        if (redoStack.isEmpty()) {

            printState("Redo Failed");
            return;
        }

        String action =
                redoStack.pop();

        undoStack.push(action);

        printState("Redo: " + action);
    }

    // 目前狀態
    public String current() {

        if (undoStack.isEmpty()) {
            return null;
        }

        return undoStack.peek();
    }

    private void printState(String action) {

        System.out.println("\n[" + action + "]");

        System.out.println(
                "Current = " + current());

        System.out.println(
                "Undo Stack = " + undoStack);

        System.out.println(
                "Redo Stack = " + redoStack);
    }

    public static void main(String[] args) {

        TextEditorHistory editor =
                new TextEditorHistory();

        editor.performAction("Type A");

        editor.performAction("Type B");

        editor.performAction("Type C");

        editor.undo();

        editor.undo();

        editor.redo();

        editor.performAction("Type D");

        editor.redo(); // redo 已被清空

        editor.undo();

        editor.undo();

        editor.undo();

        editor.undo(); // 空 stack 測試
    }
}