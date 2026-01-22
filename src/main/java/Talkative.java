import java.util.Scanner;

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
        Scanner scanner = new Scanner(System.in);
        Task[] UItasks = new Task[MAX_TASKS];
        int taskCount = 0;

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
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i + 1) + ". " + UItasks[i]);
                }
                System.out.println("____________________________________________________________");
                continue;
            }

            if (userInput.startsWith("mark ")) {
                String dataCleaning = userInput.substring(5);
                int index = Integer.parseInt(dataCleaning) - 1;
                UItasks[index].markTaskAsDone();

                System.out.println("____________________________________________________________");
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println("   " + UItasks[index]);
                System.out.println("____________________________________________________________");
                continue;
            }

            if (userInput.startsWith("unmark ")) {
                String dataCleaning = userInput.substring(7);
                int index = Integer.parseInt(dataCleaning) - 1;
                UItasks[index].unmarkTask();

                System.out.println("____________________________________________________________");
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println("   " + UItasks[index]);
                System.out.println("____________________________________________________________");
                continue;
            }

            if (userInput.startsWith("todo ")) {
                String description = userInput.substring(5);
                UItasks[taskCount++] = new Todo(description);

                printAddedTask(UItasks[taskCount - 1], taskCount);
                continue;
            }

            if (userInput.startsWith("deadline ")) {
                String[] parts = userInput.substring(9).split(" /by ");
                UItasks[taskCount++] = new Deadline(parts[0], parts[1]);

                printAddedTask(UItasks[taskCount - 1], taskCount);
                continue;
            }

            if (userInput.startsWith("event ")) {
                String[] parts = userInput.substring(6).split(" /from | /to ");
                UItasks[taskCount++] = new Event(parts[0], parts[1], parts[2]);

                printAddedTask(UItasks[taskCount - 1], taskCount);
                continue;
            }

//            System.out.println("____________________________________________________________");
//            System.out.println(" added: " + userInput);
//            System.out.println(userInput);
//            System.out.println("____________________________________________________________");
        }

        scanner.close();
    }
}
