package de.pickert.domain.actions;
import de.pickert.domain.Action;
import de.pickert.service.TaskController;

public class UpdateAction extends Action {

    @Override
    public void execute() {
        System.out.println("Which task would you like to update? Please enter the task title");
       String taskTitle = scanner.nextLineToLowerCase();
        while (!TaskController.getTaskList().containsKey(taskTitle)) {
            System.out.println("Task does not exist. Please choose existing task!");
            taskTitle = scanner.nextLineToLowerCase();
        }

        controller.updateTask(TaskController.getTaskList().get(taskTitle));

    }

}
