import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;

public class ResponsibleAnalysisAction extends Action {

    @Override
    public void execute() {

        HashMultiMap mapKeyResponsible = new HashMultiMap();
        Iterator<Task> iterator = TaskController.getTaskList().values().iterator();
        while (iterator.hasNext()) {
            Task task = iterator.next();
            mapKeyResponsible.put(task.getResponsible(), task);
        }
        Iterator<Entry<String, List<Task>>> responsibleIterator = mapKeyResponsible.entrySet().iterator();
        while (responsibleIterator.hasNext()) {
            Map.Entry<String, List<Task>> tasksOfResponsible = responsibleIterator.next();
            System.out.println(tasksOfResponsible.getKey() + " is responsible for the following "+tasksOfResponsible.getValue().size() +" tasks:");
            for (int i=0; i<tasksOfResponsible.getValue().size();i++){
                System.out.println(tasksOfResponsible.getValue().get(i).getTitle());
            }
        }
    }
}