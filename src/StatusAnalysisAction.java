import java.util.Iterator;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Map.Entry;

public class StatusAnalysisAction extends Action{
    @Override
    public void execute() {
        int taskCounter = 0;
        boolean invalidInput=true;
        Status statusOfInterest=Status.NEW;
        System.out.println("Which status do you want to be displayed?");
        while (invalidInput){
            try{
            statusOfInterest = Status.valueOf(scanner.nextLine().toUpperCase());
            invalidInput=false;
            } catch(IllegalArgumentException | NullPointerException e){
                System.out.println("Please enter a valid Status");
            }
        } 
       System.out.println("The following tasks are in status: "+statusOfInterest.toString());      
       Iterator<Task> iterator = TaskController.getTaskList().values().iterator();
        while(iterator.hasNext()){
            Task task = iterator.next();
            if (task.getStatus().equals(statusOfInterest)) {
                System.out.println(task.getTitle());
                taskCounter++;
            }

        }
        System.out.println("There is a total of " + taskCounter+" tasks in Status: "+statusOfInterest);
        // StatusMultiMap mapKeyStatus = new StatusMultiMap();
        // Iterator<Task> iterator = TaskController.getTaskList().values().iterator();
        // while (iterator.hasNext()) {
        //     Task task = iterator.next();
        //     mapKeyStatus.put(task.getStatus(), task);
        // }
        // Iterator<Entry<Status, List<Task>>> statusIterator = mapKeyStatus.entrySet().iterator();
        // while (statusIterator.hasNext()) {
        //     Map.Entry<Status, List<Task>> tasksInStatus = statusIterator.next();
        //     System.out.println("The following"+tasksInStatus.getValue().size() +" tasks are in Status: "+tasksInStatus.getKey());
        //     for (int i=0; i<tasksInStatus.getValue().size();i++){
        //         System.out.println(tasksInStatus.getValue().get(i).getTitle());
        //     }
        // }
    }
    
}
