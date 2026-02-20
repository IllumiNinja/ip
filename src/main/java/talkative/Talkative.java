package talkative;

import java.util.ArrayList;

/**
 * Represents the main logic component of the Talkative application.
 *
 * This class processes user input, executes the corresponding commands,
 * and returns formatted responses to be displayed in the UI.
 */
public class Talkative {
    public static void main(String[] args) {
    }

    /**
     * Returns the welcome message displayed when the application starts.
     *
     * @return A formatted welcome message.
     */
    public String getWelcomeMessage() {
        Ui ui = new Ui();
        return ui.showWelcome();
    }

    /**
     * Processes the user's input and returns the corresponding response.
     *
     * @param input The user's input command.
     * @return A formatted response based on the command.
     */
    public String getResponse(String input) {
        try {
            Ui ui = new Ui();
            Storage storage = new Storage("./data/memory.txt");
            TaskList tasks = new TaskList(storage.load());

            String command = Parser.getCommandWord(input);
            assert command != null && !command.isEmpty()
                    : "Command should not be empty";

            return switch (command) {
                case "bye" -> ui.showBye();
                case "list" -> ui.showList(tasks.getAll());
                case "find" -> handleFind(input, tasks, ui);
                case "mark" -> handleMark(input, tasks, storage, ui);
                case "unmark" -> handleUnmark(input, tasks, storage, ui);
                case "todo" -> handleTodo(input, tasks, storage, ui);
                case "deadline" -> handleDeadline(input, tasks, storage, ui);
                case "event" -> handleEvent(input, tasks, storage, ui);
                case "delete" -> handleDelete(input, tasks, storage, ui);
                case "dowithin" -> handleDoWithin(input, tasks, storage, ui);
                default -> ui.showUnknownCommand();
            };

        } catch (TalkativeException e) {
            return e.getMessage();
        }
    }

    /**
     * Handles the find command by searching for tasks that match the keyword.
     *
     * @param input The user input.
     * @param tasks The task list.
     * @param ui The UI handler.
     * @return A formatted message containing matching tasks.
     * @throws TalkativeException If the input format is invalid.
     */
    private String handleFind(String input, TaskList tasks, Ui ui) throws TalkativeException {
        if (input.length() <= 4) {
            throw new TalkativeException("Please provide a keyword to search.");
        }

        String keyword = input.substring(5).trim();
        ArrayList<Task> matches = tasks.find(keyword);
        return ui.showFound(matches);
    }

    /**
     * Marks a task as done based on the given index.
     *
     * @param input The user input.
     * @param tasks The task list.
     * @param storage The storage handler.
     * @param ui The UI handler.
     * @return A formatted message indicating the task is marked as done.
     * @throws TalkativeException If the input is invalid.
     */
    private String handleMark(String input, TaskList tasks, Storage storage, Ui ui) throws TalkativeException {
        if (input.length() <= 4) {
            throw new TalkativeException("Please specify a task number to mark.");
        }

        int index = parseIndex(input.substring(5), tasks);

        Task task = tasks.get(index);
        task.markTaskAsDone();
        storage.save(tasks.getAll());

        return ui.markTaskAsDone(task);
    }

    /**
     * Marks a task as not done based on the given index.
     *
     * @param input The user input.
     * @param tasks The task list.
     * @param storage The storage handler.
     * @param ui The UI handler.
     * @return A formatted message indicating the task is marked as not done.
     * @throws TalkativeException If the input is invalid.
     */
    private String handleUnmark(String input, TaskList tasks, Storage storage, Ui ui) throws TalkativeException {
        if (input.length() <= 6) {
            throw new TalkativeException("Please specify a task number to unmark.");
        }

        int index = parseIndex(input.substring(7), tasks);

        Task task = tasks.get(index);
        task.unmarkTask();
        storage.save(tasks.getAll());

        return ui.markTaskAsNotDone(task);
    }

    /**
     * Adds a new todo task to the task list.
     *
     * @param input The user input.
     * @param tasks The task list.
     * @param storage The storage handler.
     * @param ui The UI handler.
     * @return A formatted message indicating the task has been added.
     * @throws TalkativeException If the description is empty.
     */
    private String handleTodo(String input, TaskList tasks, Storage storage, Ui ui)
            throws TalkativeException {

        if (input.length() <= 4) {
            throw new TalkativeException("A todo cannot be empty.");
        }

        String description = input.substring(5).trim();
        if (description.isEmpty()) {
            throw new TalkativeException("A todo cannot be empty.");
        }

        Task task = new Todo(description);
        tasks.add(task);
        storage.save(tasks.getAll());

        return ui.showAddedTask(task, tasks.size());
    }

    /**
     * Adds a new deadline task to the task list.
     *
     * @param input The user input.
     * @param tasks The task list.
     * @param storage The storage handler.
     * @param ui The UI handler.
     * @return A formatted message indicating the task has been added.
     * @throws TalkativeException If the input format is invalid.
     */
    private String handleDeadline(String input, TaskList tasks, Storage storage, Ui ui)
            throws TalkativeException {

        if (!input.contains(" /by ")) {
            throw new TalkativeException("Deadline format: deadline <task> /by <time>");
        }

        String[] parts = input.substring(9).split(" /by ");
        assert parts.length == 2;

        Task task = new Deadline(parts[0], parts[1]);
        tasks.add(task);
        storage.save(tasks.getAll());

        return ui.showAddedTask(task, tasks.size());
    }

    /**
     * Adds a new event task with a specified time range.
     *
     * @param input The user input.
     * @param tasks The task list.
     * @param storage The storage handler.
     * @param ui The UI handler.
     * @return A formatted message indicating the task has been added.
     * @throws TalkativeException If the input format is invalid.
     */
    private String handleEvent(String input, TaskList tasks, Storage storage, Ui ui)
            throws TalkativeException {

        if (!input.contains(" /from ") || !input.contains(" /to ")) {
            throw new TalkativeException("Event format: event <task> /from <start> /to <end>");
        }

        String[] parts = input.substring(6).split(" /from | /to ");
        assert parts.length == 3;

        Task task = new Event(parts[0], parts[1], parts[2]);
        tasks.add(task);
        storage.save(tasks.getAll());

        return ui.showAddedTask(task, tasks.size());
    }

    /**
     * Deletes a task from the task list based on the given index.
     *
     * @param input The user input.
     * @param tasks The task list.
     * @param storage The storage handler.
     * @param ui The UI handler.
     * @return A formatted message indicating the task has been removed.
     * @throws TalkativeException If the input is invalid.
     */
    private String handleDelete(String input, TaskList tasks, Storage storage, Ui ui)
            throws TalkativeException {

        if (input.length() <= 6) {
            throw new TalkativeException("Please specify a task number to delete.");
        }

        int index = parseIndex(input.substring(7).trim(), tasks);

        Task removed = tasks.remove(index);
        storage.save(tasks.getAll());

        return ui.showDeletedTask(removed, tasks.size());
    }

    /**
     * Adds a new DoWithin task with a specified time range.
     *
     * @param input The user input.
     * @param tasks The task list.
     * @param storage The storage handler.
     * @param ui The UI handler.
     * @return A formatted message indicating the task has been added.
     * @throws TalkativeException If the input format is invalid.
     */
    private String handleDoWithin(String input, TaskList tasks, Storage storage, Ui ui)
            throws TalkativeException {

        if (!input.contains(" /from ") || !input.contains(" /to ")) {
            throw new TalkativeException(
                    "Format: dowithin <task> /from <start> /to <end>");
        }

        String[] parts = input.substring(9).split(" /from | /to ");
        assert parts.length == 3 : "DoWithin must have description, from, and to";

        Task task = new DoWithin(parts[0], parts[1], parts[2]);
        tasks.add(task);
        storage.save(tasks.getAll());

        return ui.showAddedTask(task, tasks.size());
    }

    /**
     * Parses the index from the user input and validates it.
     *
     * @param input The input string containing the index.
     * @param tasks The task list.
     * @return The zero-based index of the task.
     * @throws TalkativeException If the index is invalid or out of bounds.
     */
    private int parseIndex(String input, TaskList tasks) throws TalkativeException {
        try {
            int index = Integer.parseInt(input) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new TalkativeException("That task number does not exist.");
            }

            assert index >= 0 && index < tasks.size(): "Index out of bounds: " + index;

            return index;
        } catch (NumberFormatException e) {
            throw new TalkativeException("Command requires a valid number.");
        }
    }
}
