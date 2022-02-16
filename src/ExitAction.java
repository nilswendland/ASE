
public class ExitAction extends Action {

    @Override
    public void execute() {
        CommandLineRunner.setKeepRunning(false);
    }
}
