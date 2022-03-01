
public abstract class Action {
    public abstract void execute();

    public static TaskController controller;
    static UserScanner scanner;

    protected Action() {
        controller = TaskController.getInstance();
        scanner = UserScanner.getInstance();
    }
}