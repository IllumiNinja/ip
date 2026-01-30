package talkative;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markTaskAsDone() {
        isDone = true;
    }

    public void unmarkTask() {
        isDone = false;
    }

    public String getTaskStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Returns the text format to be stored into the text file
     *
     * @return Cleaned text format for addition into text file for Task
     */
    public String toFileFormat() {
        return "T | "
                + (isDone ? "1" : "0")
                + " | " + description;
    }

    @Override
    public String toString() {
        return "["
                + getTaskStatusIcon()
                + "] "
                + description;
    }
}
