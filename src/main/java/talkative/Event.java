package talkative;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the commands that are used within the Talkative bot.
 * Provides methods to readCommand, showLine, showWelcome, showBye, showList.
 */
public class Event extends Task {
    protected LocalDate eventFrom;
    protected LocalDate eventTo;

    /**
     * Creates an event task.
     *
     * @param description Task description.
     * @param eventFrom Start date of the task.
     * @param eventTo Deadline of the task.
     * @throws TalkativeException If date format is incorrect
     */
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
     * Converts the Event task into a format suitable for saving to a file.
     *
     * @return A string representation of the event in storage format.
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

    /**
     * Returns a string representation of the Event task for display.
     *
     * @return A formatted string containing the task type, status,
     *         description, and formatted date range.
     */
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
