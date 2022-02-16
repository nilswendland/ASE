
public abstract class Action {
    public abstract void execute();

    public TaskController controller;

    protected Action() {
        controller = new TaskController();
    }
}