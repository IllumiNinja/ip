import java.util.Scanner;
import java.util.ArrayList;

public class Talkative {
    private static final int MAX_TASKS = 100;

    private static void printAddedTask(Task UITask, int count) {
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + UITask);
        System.out.println(" Now you have " + count + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
//            Task[] UItasks = new Task[MAX_TASKS];
//            int taskCount = 0;
            ArrayList<Task> UItasks = new ArrayList<>();

            System.out.println("____________________________________________________________");
            System.out.println(" Hello! I'm Talkative");
            System.out.println(" What can I do for you?");

            while (true) {
                String userInput = scanner.nextLine();

                if (userInput.equals("bye")) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    break;
                }

                if (userInput.equals("list")) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" Here are the tasks in your list:");
                    for (int i = 0; i < UItasks.size(); i++) {
                        System.out.println(" " + (i + 1) + ". " + UItasks.get(i));
                    }
                    System.out.println("____________________________________________________________");
                    continue;
                }

                if (userInput.startsWith("mark ")) {
                    String dataCleaning = userInput.substring(5);
                    int index = Integer.parseInt(dataCleaning) - 1;
                    UItasks.get(index).markTaskAsDone();

                    System.out.println("____________________________________________________________");
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("   " + UItasks.size());
                    System.out.println("____________________________________________________________");
                    continue;
                }

                if (userInput.startsWith("unmark ")) {
                    String dataCleaning = userInput.substring(7);
                    int index = Integer.parseInt(dataCleaning) - 1;
                    UItasks.get(index).unmarkTask();

                    System.out.println("____________________________________________________________");
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println("   " + UItasks.size());
                    System.out.println("____________________________________________________________");
                    continue;
                }

                if (userInput.equals("todo")) {
                    throw new TalkativeException("A todo needs a description. Try: todo borrow book");
                }
                if (userInput.startsWith("todo ")) {
                    String description = userInput.substring(5);
                    if (description.isEmpty()) {
                        throw new TalkativeException("A todo cannot be empty.");
                    }
//                    UItasks[taskCount++] = new Todo(description);
                    UItasks.add(new Todo(description));
//                    printAddedTask(UItasks[taskCount - 1], taskCount);
                    printAddedTask(UItasks.get(UItasks.size() - 1), UItasks.size());
                    continue;
                }

                if (userInput.equals("deadline")) {
                    throw new TalkativeException("A deadline needs /by <time>.");
                }
                if (userInput.startsWith("deadline ")) {
                    if (!userInput.contains(" /by ")) {
                        throw new TalkativeException("Deadline format: deadline <task> /by <time>");
                    }
                    String[] parts = userInput.substring(9).split(" /by ");
//                    UItasks[taskCount++] = new Deadline(parts[0], parts[1]);
                    UItasks.add(new Deadline(parts[0], parts[1]));

//                    printAddedTask(UItasks[taskCount - 1], taskCount);
                    printAddedTask(UItasks.get(UItasks.size() - 1), UItasks.size());
                    continue;
                }

                if (userInput.startsWith("event ")) {
                    if (!userInput.contains(" /from ") || !userInput.contains(" /to ")) {
                        throw new TalkativeException("Event format: event <task> /from <start> /to <end>");
                    } else{
                        String[] parts = userInput.substring(6).split(" /from | /to ");
//                        UItasks[taskCount++] = new Event(parts[0], parts[1], parts[2]);
                        UItasks.add(new Event(parts[0], parts[1], parts[2]));

//                        printAddedTask(UItasks[taskCount - 1], taskCount);
                        printAddedTask(UItasks.get(UItasks.size() - 1), UItasks.size());
                        continue;
                    }
                }

                if (userInput.startsWith("delete ")) {
                    String data = userInput.substring(7).trim();

                    int index;
                    try {
                        index = Integer.parseInt(data) - 1;
                    } catch (NumberFormatException e) {
                        throw new TalkativeException("Delete command requires a number.");
                    }

                    if (index < 0 || index >= UItasks.size()) {
                        throw new TalkativeException("That task number does not exist.");
                    }

                    Task removed = UItasks.remove(index);

                    System.out.println("____________________________________________________________");
                    System.out.println(" Noted. I've removed this task:");
                    System.out.println("   " + removed);
                    System.out.println(" Now you have " + UItasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    continue;
                }

                UItasks.add(new Todo(userInput));
                printAddedTask(UItasks.get(UItasks.size() - 1), UItasks.size());

//                throw new TalkativeException("I don't understand that command.");
            }

            scanner.close();
        } catch (TalkativeException e) {
            System.out.println("____________________________________________________________");
            System.out.println(" " + e.getMessage());
            System.out.println("____________________________________________________________");
        }
    }
}
