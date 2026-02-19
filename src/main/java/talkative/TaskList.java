package talkative;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 * Provides methods to add, remove, get, check size and retrieve tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        assert task != null : "Task should not be null";
        tasks.add(task);
    }

    /**
     * Returns the task to be removed.
     *
     * @return Task to be removed.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    public Task get(int index) {
        assert index >= 0 && index < tasks.size()
                : "Invalid index access in TaskList";
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks.
     *
     * @return Integer of the number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns matches based on keywords.
     *
     * @param keyword Keyword to search.
     * @return Matches as an ArrayList.
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> matches = new ArrayList<>();
        for (Task t : tasks) {
            if (t.toString().toLowerCase().contains(keyword.toLowerCase())) {
                matches.add(t);
            }
        }
        return matches;
    }

    public ArrayList<Task> getAll() {
        return tasks;
    }
}
