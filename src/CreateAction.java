
public class CreateAction extends Action {

    @Override
    public void execute() {
        controller.setCreateMode(true);
        while (controller.isCreateMode()) {
            controller.createTask();
        }
        
    }

}
