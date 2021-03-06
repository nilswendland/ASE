package de.pickert;
import java.util.HashMap;
import java.util.Map;

import de.pickert.domain.Action;
import de.pickert.domain.actions.AnalysisAction;
import de.pickert.domain.actions.CreateAction;
import de.pickert.domain.actions.ExitAction;
import de.pickert.domain.actions.UpdateAction;
import de.pickert.utils.UserScanner;

public class CommandLineRunner {
    private static boolean keepRunning = true;
    static UserScanner scanner;
    private static Map<String, Action> actionDictionary = new HashMap<>();

    public static void main(String[] args) {
        actionDictionary.put("exit", new ExitAction());
        actionDictionary.put("create", new CreateAction());
        actionDictionary.put("update", new UpdateAction());
        actionDictionary.put("analyze", new AnalysisAction());
        scanner = UserScanner.getInstance();
        run();
    }

    private static void run() {
        String userInput;
        while (keepRunning) {
            System.out.print(
                    "Type exit to exit,\n create to create a new task,\n update to update an existing task\n or analyze to get info\n");
            userInput = scanner.nextLineToLowerCase();
            try {
                actionDictionary.get(userInput).execute(); 
            } catch (NullPointerException e) {
                System.out.println("Not a valid input");
            }
            
        }
        scanner.getScanner().close();
    }

    public static void setKeepRunning(boolean keepRunning) {
        CommandLineRunner.keepRunning = keepRunning;
    }

}
