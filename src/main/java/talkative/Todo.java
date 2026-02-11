package talkative;

/**
 * Represents the commands that are used within the Talkative Bot and within the Todo Tasks.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
