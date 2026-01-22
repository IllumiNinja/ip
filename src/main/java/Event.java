public class Event extends Task {
    protected String eventFrom;
    protected String eventTo;

    public Event(String description, String eventFrom, String eventTo) {
        super(description);
        this.eventFrom = eventFrom;
        this.eventTo = eventTo;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + eventFrom + " to: " + eventTo + ")";
    }

}
