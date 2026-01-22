import java.util.Scanner;

public class Talkative {
    private static final int MAX_TASKS = 100;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] UItasks = new String[MAX_TASKS];
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

            UItasks[taskCount] = userInput;
            taskCount++;

            System.out.println("____________________________________________________________");
            System.out.println(" added: " + userInput);
            System.out.println("____________________________________________________________");
        }

        scanner.close();
    }
}
