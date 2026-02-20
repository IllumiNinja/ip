package talkative;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles user interface interactions for the Talkative application.
 *
 * Provides methods to format and return messages displayed to the user.
 */
public class Ui {

    private final Scanner scanner;

    /**
     * Constructs a Ui instance with a scanner for reading user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads a command entered by the user.
     *
     * @return The user input as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints a divider line to the console.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Returns the welcome message displayed at application startup.
     *
     * @return A formatted welcome message.
     */
    public String showWelcome() {
        return "Hello! I'm Talkative\nWhat can I do for you?";
    }

    /**
     * Returns the farewell message displayed when the application exits.
     *
     * @return A formatted goodbye message.
     */
    public String showBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns a message listing tasks that match the given keyword.
     *
     * @param matches The list of matching tasks.
     * @return A formatted message containing the matching tasks.
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
     * Returns a message listing all tasks in the task list.
     *
     * @param tasks The list of tasks.
     * @return A formatted message containing all tasks.
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
     * Returns a message indicating that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     * @return A formatted message describing the updated task.
     */
    public String markTaskAsDone(Task task) {
        return "Nice! I've marked this task as done:\n"
                + "  " + task;
    }

    /**
     * Returns a message indicating that a task has been marked as not done.
     *
     * @param task The task that was marked as not done.
     * @return A formatted message describing the updated task.
     */
    public String markTaskAsNotDone(Task task) {
        return "OK, I've marked this task as not done yet:\n"
                + "  " + task;
    }

    /**
     * Returns a message indicating that a task has been added to the list.
     *
     * @param task The task that was added.
     * @param size The total number of tasks after addition.
     * @return A formatted message describing the added task and updated count.
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
     * @return A formatted message describing the removed task and updated count.
     */
    public String showDeletedTask(Task task, int size) {
        return "Noted. I've removed this task:\n"
                + "  " + task + "\n"
                + "Now you have " + size + " tasks in the list.";
    }

    /**
     * Returns a message indicating that the command is not recognized.
     *
     * @return A formatted message for unknown commands.
     */
    public String showUnknownCommand() {
        return "I'm sorry, I don't understand that command.";
    }
}
