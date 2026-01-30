import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate  eventFrom;
    protected LocalDate eventTo;

    public Event(String description, String eventFrom, String eventTo) throws TalkativeException {
        super(description);

        try {
            this.eventFrom = LocalDate.parse(eventFrom);
            this.eventTo = LocalDate.parse(eventTo);
        } catch (Exception e) {
            throw new TalkativeException("Use date format yyyy-mm-dd (e.g., 2019-10-15)");
        }
    }

    /**
     * Returns the text format to be stored into the text file
     *
     * @return Cleaned text format for addition into text file for Event Task
     */
    @Override
    public String toFileFormat() {
        return "E | "
                + (isDone ? "1" : "0")
                + " | "
                + description
                + " | "
                + eventFrom.toString()
                + " | "
                + eventTo.toString();
    }

    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + " (from: "
                + eventFrom.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: "
                + eventTo.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }
}
