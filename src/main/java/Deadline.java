import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate deadlineBy;

    public Deadline(String description, String deadlineBy) throws TalkativeException {
        super(description);

        try {
            this.deadlineBy = LocalDate.parse(deadlineBy);
        } catch (Exception e) {
            throw new TalkativeException("Use date format yyyy-mm-dd (e.g., 2019-10-15)");
        }
    }

    /**
     * Returns the text format to be stored into the text file
     *
     * @return Cleaned text format for addition into text file for Deadline Task
     */
    @Override
    public String toFileFormat() {
        return "D | "
                + (isDone ? "1" : "0")
                + " | "
                + description
                + " | " + deadlineBy.toString();
    }


    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + " (by: "
                + deadlineBy.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }
}
