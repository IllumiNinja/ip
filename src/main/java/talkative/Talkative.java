package talkative;

import java.util.ArrayList;

/**
 * Represents the commands that are used within the Talkative bot.
 * Provides methods to readCommand, showLine, showWelcome, showBye, showList.
 */
public class Talkative {
    public static void main(String[] args) {
    }

    public String getWelcomeMessage() {
        Ui ui = new Ui();
        return ui.showWelcome();
    }

    /**
     * Generates a response for the user's chat message.
     *
     * @param input Takes in an input
     * @return the correct formatted string output
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

    private String handleFind(String input, TaskList tasks, Ui ui) {
        String keyword = input.substring(5);
        ArrayList<Task> matches = tasks.find(keyword);
        return ui.showFound(matches);
    }

    private String handleMark(String input, TaskList tasks, Storage storage, Ui ui) throws TalkativeException {
        int index = parseIndex(input.substring(5), tasks);

        Task task = tasks.get(index);
        task.markTaskAsDone();
        storage.save(tasks.getAll());

        return ui.markTaskAsDone(task);
    }

    private String handleUnmark(String input, TaskList tasks, Storage storage, Ui ui) throws TalkativeException {
        int index = parseIndex(input.substring(7), tasks);

        Task task = tasks.get(index);
        task.unmarkTask();
        storage.save(tasks.getAll());

        return ui.markTaskAsNotDone(task);
    }

    private String handleTodo(String input, TaskList tasks, Storage storage, Ui ui)
            throws TalkativeException {

        String description = input.substring(5);
        if (description.isEmpty()) {
            throw new TalkativeException("A todo cannot be empty.");
        }

        Task task = new Todo(description);
        tasks.add(task);
        storage.save(tasks.getAll());

        return ui.showAddedTask(task, tasks.size());
    }

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

    private String handleDelete(String input, TaskList tasks, Storage storage, Ui ui)
            throws TalkativeException {

        int index = parseIndex(input.substring(7).trim(), tasks);

        Task removed = tasks.remove(index);
        storage.save(tasks.getAll());

        return ui.showDeletedTask(removed, tasks.size());
    }

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

    private int parseIndex(String input, TaskList tasks) throws TalkativeException {
        try {
            int index = Integer.parseInt(input) - 1;
            assert index >= 0 && index < tasks.size()
                    : "Index out of bounds: " + index;
            return index;
        } catch (NumberFormatException e) {
            throw new TalkativeException("Command requires a valid number.");
        }
    }
}
