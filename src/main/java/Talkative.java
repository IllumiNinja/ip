import java.util.Scanner;

public class Talkative {
    private static final int MAX_TASKS = 100;
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

            UItasks[taskCount] = new Task(userInput);
            taskCount++;

            System.out.println("____________________________________________________________");
            System.out.println(" added: " + userInput);
            System.out.println("____________________________________________________________");
        }

        scanner.close();
    }
}
