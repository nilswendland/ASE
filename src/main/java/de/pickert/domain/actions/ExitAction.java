package de.pickert.domain.actions;
import de.pickert.CommandLineRunner;
import de.pickert.domain.Action;
import de.pickert.service.PropertiesController;

public class ExitAction extends Action {

    @Override
    public void execute() {
        CommandLineRunner.setKeepRunning(false);
        PropertiesController.writeTasks();
    }
}
