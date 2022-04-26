package de.pickert.domain.actions;
import de.pickert.domain.Action;

public class CreateAction extends Action {

    @Override
    public void execute() {
        controller.setCreateMode(true);
        while (controller.isCreateMode()) {
            controller.createTask();
        }
        
    }

}
