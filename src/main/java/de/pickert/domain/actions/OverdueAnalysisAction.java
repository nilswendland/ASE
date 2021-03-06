package de.pickert.domain.actions;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Map;

import de.pickert.domain.Action;
import de.pickert.domain.Task;
import de.pickert.service.TaskController;

public class OverdueAnalysisAction extends Action {

    @Override
    public void execute() {
        Iterator<Map.Entry<String, Task>> iterator = TaskController.getTaskList().entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Task> taskEntry = iterator.next();
            if (taskEntry.getValue().getDueDate().isBefore(LocalDate.now())) {

                System.out.println(taskEntry.getKey() + " is overdue by "+taskEntry.getValue().getDueDate().compareTo(LocalDate.now())*-1+" days.");
            }
        }
    }

}
