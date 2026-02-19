package talkative;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the commands that are used within the Talkative bot.
 * Provides methods to readCommand, showLine, showWelcome, showBye, showList.
 */
public class Ui {

    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Output static welcome messages
     */
    public String showWelcome() {
        return "Hello! I'm Talkative\nWhat can I do for you?";
    }

    /**
     * Output static end messages
     */
    public String showBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Output matching tasks based on searched inputs
     *
     * @param matches that matched the searched results
     * @return formatted String
     */
    public String showFound(ArrayList<Task> matches) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");

        for (int i = 0; i < matches.size(); i++) {
            sb.append(" ").append(i + 1).append(".").append(matches.get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Output tasks in task list
     *
     * @param tasks that are in the task lists
     * @return formatted String
     */
    public String showList(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");

        for (int i = 0; i < tasks.size(); i++) {
            sb.append(" ").append(i + 1).append(".").append(tasks.get(i)).append("\n");
        }
        return sb.toString();

    }

    /**
     * Outputs tasks marked as done in the task list
     *
     * @param task to be shown as marked as done
     * @return formatted String
     */
    public String markTaskAsDone(Task task) {
        return "Nice! I've marked this task as done:\n"
                + "  " + task;
    }

    /**
     * Outputs tasks marked as not done in the task list
     *
     * @param task to be shown as marked as undone
     * @return formatted String
     */
    public String markTaskAsNotDone(Task task) {
        return "OK, I've marked this task as not done yet:\n"
                + "  " + task;
    }

    /**
     * Returns a message indicating that a task has been added.
     *
     * @param task The task that was marked as not done.
     * @param size The total number of tasks after addition.
     * @return A formatted message describing the updated task.
     */
    public String showAddedTask(Task task, int size) {
        return "Got it. I've added this task:\n"
                + "  " + task + "\n"
                + "Now you have " + size + " tasks in the list.";
    }

    /**
     * Returns a message indicating that a task has been removed from the list.
     *
     * @param task The task that was removed.
     * @param size The total number of tasks after removal.
     * @return A formatted message showing the removed task and updated count.
     */
    public String showDeletedTask(Task task, int size) {
        return "Noted. I've removed this task:\n"
                + "  " + task + "\n"
                + "Now you have " + size + " tasks in the list.";
    }
}
