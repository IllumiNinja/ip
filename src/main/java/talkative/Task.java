package talkative;

/**
 * Represents a generic task with a description and completion status.
 *
 * This class serves as the base class for all task types such as
 * Todo, Deadline, Event, and DoWithin.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks this task as completed.
     */
    public void markTaskAsDone() {
        isDone = true;
    }

    /**
     * Marks this task as not completed.
     */
    public void unmarkTask() {
        isDone = false;
    }

    /**
     * Returns the status icon representing whether the task is completed.
     *
     * @return "X" if the task is done, otherwise a blank space.
     */
    public String getTaskStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Converts the task into a format suitable for saving to a file.
     *
     * @return A string representation of the task in storage format.
     */
    public String toFileFormat() {
        return "T | "
                + (isDone ? "1" : "0")
                + " | " + description;
    }

    /**
     * Returns a string representation of the task for display.
     *
     * @return A formatted string containing the task status and description.
     */
    @Override
    public String toString() {
        return "["
                + getTaskStatusIcon()
                + "] "
                + description;
    }
}
