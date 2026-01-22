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

    @Override
    public String toString() {
        return "[" + getTaskStatusIcon() + "] " + description;
    }
}
