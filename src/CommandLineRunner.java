import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandLineRunner {
    private static boolean keepRunning = true;
    static UserScanner scanner;
    private static Map<String, Action> actionDictionary = new HashMap<>();

    public static void main(String[] args) {
        actionDictionary.put("exit", new ExitAction());
        actionDictionary.put("create", new CreateAction());
        actionDictionary.put("update", new UpdateAction());
        scanner = UserScanner.getInstance();
        run();
    }

    private static void run() {
        String userInput;
        while (keepRunning) {
            System.out.print(
                    "Type exit to exit, create to create a new task or update to update an existing task\n");
            userInput = scanner.nextLineToLowerCase();
            actionDictionary.get(userInput).execute();
        }
        scanner.getScanner().close();
    }

    public static void setKeepRunning(boolean keepRunning) {
        CommandLineRunner.keepRunning = keepRunning;
    }

}