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

    @Override
    public String toString() {
        return "[W]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toFileFormat() {
        return "W | " + (isDone ? "1" : "0") + " | "
                + description + " | " + from + " | " + to;
    }
}
