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
        tasks.add(task);
    }

    public Task remove(int index) {
        return tasks.remove(index);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

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
