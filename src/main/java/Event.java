public class Event extends Task {
    protected String eventFrom;
    protected String eventTo;

    public Event(String description, String eventFrom, String eventTo) {
        super(description);
        this.eventFrom = eventFrom;
        this.eventTo = eventTo;
    }

    /**
     * Returns the text format to be stored into the text file
     *
     * @return Cleaned text format for addition into text file for Event Task
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + eventFrom + " | " + eventTo;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + eventFrom + " to: " + eventTo + ")";
    }

}
