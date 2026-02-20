package talkative;

/**
 * Represents a todo task without any associated date or time.
 *
 * A Todo task contains only a description and a completion status.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task with the given description.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task for display.
     *
     * @return A formatted string containing the task type, status, and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
