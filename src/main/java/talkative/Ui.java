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
    public void showWelcome() {
        showLine();
        System.out.println(" Hello! I'm Talkative");
        System.out.println(" What can I do for you?");
    }

    /**
     * Output static end messages
     */
    public void showBye() {
        showLine();
        System.out.println(" Bye. Hope to see you again soon!");
        showLine();
    }

    /**
     * Output matching tasks based on searched inputs
     *
     * @param matches that matched the searched results
     */
    public void showFound(ArrayList<Task> matches) {
        showLine();
        System.out.println(" Here are the matching tasks in your list:");

        for (int i = 0; i < matches.size(); i++) {
            System.out.println(" " + (i + 1) + "." + matches.get(i));
        }

        showLine();
    }

    /**
     * Output tasks in task list
     *
     * @param tasks that are in the task lists
     */
    public void showList(ArrayList<Task> tasks) {
        showLine();
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + tasks.get(i));
        }
        showLine();
    }
}
