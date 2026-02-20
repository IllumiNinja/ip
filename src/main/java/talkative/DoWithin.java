package talkative;

public class DoWithin extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs a DoWithin task with description and time range.
     *
     * @param description The description of the task.
     * @param from The start time.
     * @param to The end time.
     */
    public DoWithin(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the DoWithin task for display.
     *
     * @return A formatted string containing the task type, status,
     *         description, and time range.
     */
    @Override
    public String toString() {
        return "[W]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Converts the DoWithin task into a format suitable for saving to a file.
     *
     * @return A string representation of the task in storage format.
     */
    @Override
    public String toFileFormat() {
        return "W | " + (isDone ? "1" : "0") + " | "
                + description + " | " + from + " | " + to;
    }
}
