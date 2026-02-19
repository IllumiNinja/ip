package talkative;

import java.util.ArrayList;

/**
 * Represents the commands that are used within the Talkative bot.
 * Provides methods to readCommand, showLine, showWelcome, showBye, showList.
 */
public class Talkative {
    public static void main(String[] args) {
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

            String commandParser = Parser.getCommandWord(input);
            if (commandParser.equals("bye")) {
                return ui.showBye();
            } else if (input.startsWith("find")) {
                String keyword = input.substring(5);

                ArrayList<Task> matches = tasks.find(keyword);

                return ui.showFound(matches);
            } else if (commandParser.equals("list")) {
                return ui.showList(tasks.getAll());
            } else if (commandParser.startsWith("mark")) {
                String dataCleaning = input.substring(5);
                int index = Integer.parseInt(dataCleaning) - 1;
                Task task = tasks.get(index);
                task.markTaskAsDone();
                storage.save(tasks.getAll());

                return ui.markTaskAsDone(task);
            } else if (commandParser.startsWith("unmark")) {
                String dataCleaning = input.substring(7);
                int index = Integer.parseInt(dataCleaning) - 1;

                Task task = tasks.get(index);
                task.unmarkTask();
                storage.save(tasks.getAll());

                return ui.markTaskAsNotDone(task);
            } else if (commandParser.startsWith("todo")) {
                String description = input.substring(5);
                if (description.isEmpty()) {
                    throw new TalkativeException("A todo cannot be empty.");
                }
                Task task = new Todo(description);
                tasks.add(task);
                storage.save(tasks.getAll());

                return ui.showAddedTask(task, tasks.size());

            } else if (commandParser.startsWith("deadline")) {
                if (!input.contains(" /by ")) {
                    throw new TalkativeException("Deadline format: deadline <task> /by <time>");
                }
                String[] parts = input.substring(9).split(" /by ");
                Task task = new Deadline(parts[0], parts[1]);
                tasks.add(task);
                storage.save(tasks.getAll());

                return ui.showAddedTask(task, tasks.size());
            } else if (commandParser.startsWith("event")) {
                if (!input.contains(" /from ") || !input.contains(" /to ")) {
                    throw new TalkativeException("Event format: event <task> /from <start> /to <end>");
                }
                String[] parts = input.substring(6).split(" /from | /to ");
                Task task = new Event(parts[0], parts[1], parts[2]);
                tasks.add(task);
                storage.save(tasks.getAll());
                return ui.showAddedTask(task, tasks.size());
            } else if (commandParser.startsWith("delete")) {
                String data = input.substring(7).trim();

                int index;
                try {
                    index = Integer.parseInt(data) - 1;
                } catch (NumberFormatException e) {
                    throw new TalkativeException("Delete command requires a number.");
                }

                if (index < 0 || index >= tasks.size()) {
                    throw new TalkativeException("That task number does not exist.");
                }

                Task removed = tasks.remove(index);
                storage.save(tasks.getAll());
                return ui.showDeletedTask(removed, tasks.size());
            } else {
                return "I'm sorry, I don't understand that command.";
            }
        } catch (TalkativeException e) {
            System.out.println("____________________________________________________________");
            System.out.println(" " + e.getMessage());
            System.out.println("____________________________________________________________");
        }
        return "";
    }
}
