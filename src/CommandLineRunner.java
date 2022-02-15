import java.util.Scanner;

public class CommandLineRunner {
    private static boolean keepRunning = true;
    private static TaskController controller;
    static UserScanner scanner;

    public static void main(String[] args) {
        controller = new TaskController();
        scanner = UserScanner.getInstance();
        run();
    }

    private static void run() {
        String userInput;
        while (keepRunning) {
            System.out.print(
                "Type exit to exit, create to create a new task or update to update an existing task\n");
            userInput = scanner.nextLineToLowerCase();
            switch (userInput) {
                case "exit":
                    exit();
                    break;
                case "create":
                    controller.setCreateMode(true);
                    break;
                case "update":
                    controller.updateTask();
                    break;

                default:
                    System.out.println("Please enter valid input");
                    break;
            }
             while(controller.isCreateMode()) {
                controller.createTask();
            }

        }
        scanner.getScanner().close();
    }

    private static void exit() {
        keepRunning = false;
    }

}
