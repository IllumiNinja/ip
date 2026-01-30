package talkative;

import java.util.Scanner;
import java.util.ArrayList;

public class Talkative {
//    private static final int MAX_TASKS = 100;

    private static void printAddedTask(Task UITask, int count) {
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + UITask);
        System.out.println(" Now you have " + count + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        try {
            Ui ui = new Ui();
            Storage storage = new Storage("./data/memory.txt");
//            ArrayList<Task> UItasks = storage.load();
            TaskList tasks = new TaskList(storage.load());

            ui.showWelcome();

            while (true) {
                String userInput = ui.readCommand();

                String commandParser = Parser.getCommandWord(userInput);

                if (commandParser.equals("bye")) {
                    ui.showBye();
                    break;
                }

                if (commandParser.equals("list")) {
//                    ui.showList(UItasks);
                    ui.showList(tasks.getAll());
                    continue;
                }

                if (commandParser.startsWith("mark")) {
                    String dataCleaning = userInput.substring(5);
                    int index = Integer.parseInt(dataCleaning) - 1;
                    tasks.get(index).markTaskAsDone();
                    storage.save(tasks.getAll());

                    System.out.println("____________________________________________________________");
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("   " + tasks.size());
                    System.out.println("____________________________________________________________");
                    continue;
                }

                if (commandParser.startsWith("unmark ")) {
                    String dataCleaning = userInput.substring(7);
                    int index = Integer.parseInt(dataCleaning) - 1;
                    tasks.get(index).unmarkTask();
                    storage.save(tasks.getAll());

                    System.out.println("____________________________________________________________");
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println("   " + tasks.size());
                    System.out.println("____________________________________________________________");
                    continue;
                }

                if (commandParser.equals("todo")) {
                    throw new TalkativeException("A todo needs a description. Try: todo borrow book");
                }
                if (commandParser.startsWith("todo ")) {
                    String description = userInput.substring(5);
                    if (description.isEmpty()) {
                        throw new TalkativeException("A todo cannot be empty.");
                    }
                    tasks.add(new Todo(description));
                    storage.save(tasks.getAll());
                    printAddedTask(tasks.get(tasks.size() - 1), tasks.size());
                    continue;
                }

                if (commandParser.equals("deadline")) {
                    throw new TalkativeException("A deadline needs /by <time>.");
                }
                if (commandParser.startsWith("deadline ")) {
                    if (!userInput.contains(" /by ")) {
                        throw new TalkativeException("Deadline format: deadline <task> /by <time>");
                    }
                    String[] parts = userInput.substring(9).split(" /by ");
                    tasks.add(new Deadline(parts[0], parts[1]));
                    storage.save(tasks.getAll());

                    printAddedTask(tasks.get(tasks.size() - 1), tasks.size());
                    continue;
                }

                if (commandParser.startsWith("event ")) {
                    if (!userInput.contains(" /from ") || !userInput.contains(" /to ")) {
                        throw new TalkativeException("Event format: event <task> /from <start> /to <end>");
                    } else{
                        String[] parts = userInput.substring(6).split(" /from | /to ");
                        tasks.add(new Event(parts[0], parts[1], parts[2]));
                        storage.save(tasks.getAll());

                        printAddedTask(tasks.get(tasks.size() - 1), tasks.size());
                        continue;
                    }
                }

                if (commandParser.startsWith("delete")) {
                    String data = userInput.substring(7).trim();

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

                    System.out.println("____________________________________________________________");
                    System.out.println(" Noted. I've removed this task:");
                    System.out.println("   " + removed);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    continue;
                }

                tasks.add(new Todo(userInput));
                storage.save(tasks.getAll());
                printAddedTask(tasks.get(tasks.size() - 1), tasks.size());
            }
        } catch (TalkativeException e) {
            System.out.println("____________________________________________________________");
            System.out.println(" " + e.getMessage());
            System.out.println("____________________________________________________________");
        }
    }
}
