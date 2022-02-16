import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class TaskController {
    private static List<Task> taskList = new ArrayList<>();
    private boolean createMode;
    private static UserScanner scanner;
    private boolean conversionSuccess;

    public TaskController() {
        scanner = UserScanner.getInstance();
    }

    public void createTask() {
        String title;
        System.out.println("You're creating a new task. Please enter title or type 'cancel' to get back to main menu");
        title = scanner.nextLineToLowerCase();
        while (findTask(title) < taskList.size()) {
            System.out.println("Task with this title already exists. Please choose a different title");
            title = scanner.nextLineToLowerCase();
        }
        if (title.equals("cancel")) {
            createMode = false;
            return;
        }
        System.out.println("Please enter a description for your task");
        String description = scanner.nextLineToLowerCase();
        if (description.equals("cancel")) {
            createMode = false;
            return;
        }
        Task newTask = new Task(title, description);
        taskList.add(newTask);
        System.out.println("Your task " + newTask.getTitle() + " has been created successfully");
        System.out.println("Do you want to create another task? Y/N");
        if (!scanner.nextLineToLowerCase().equals("y")) {
            createMode = false;
        }
    }

    public void updateTask() {
        System.out.println("Which task would you like to update? Please enter the task title");
        String taskTitle = scanner.nextLineToLowerCase();
        int id = findTask(taskTitle);
        while (id >= taskList.size()) {
            System.out.println("The task does not exists. Please choose a valid task");
            taskTitle = scanner.nextLineToLowerCase();
            id = findTask(taskTitle);
        }
        updateTitle(taskTitle, id);
        updateDescription(id);
        updateResponsible(id);
        updateDueDate(id);
        updateStatus(id);

    }

    public int findTask(String title) {
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getTitle().equals(title)) {
                return i;
            }
        }
        return taskList.size();
    }

    public boolean edited(String text) {
        if (text.equals(""))
            return false;
        else
            return true;
    }

    public void updateTitle(String taskTitle, int id) {
        System.out.println("If you want to edit the title, please enter new title. Press return to continue");
        taskTitle = scanner.nextLineToLowerCase();
        while (findTask(taskTitle) < taskList.size()) {
            System.out
                    .print("The desired title is already used by a different task. \n Please enter a different title");
            taskTitle = scanner.nextLineToLowerCase();
        }
        if (edited(taskTitle)) {
            taskList.get(id).setTitle(taskTitle);
            System.out.println("Title was successfully updated!!!");
        }
    }

    public void updateDescription(int id) {
        System.out.println("Enter a new decription to change it. Press return to continue");

        String description = scanner.nextLineToLowerCase();
        if (edited(description)) {
            taskList.get(id).setDescription(description);
            System.out.println("Description was successfully updated!!!");
        }
    }

    public void updateResponsible(int id) {
        System.out.println("Enter the name of the person responsible for the task");

        String responsible = scanner.nextLineToLowerCase();
        if (edited(responsible)) {
            taskList.get(id).setResponsible(responsible);
            System.out.println("Responsible was successfully updated!!!");
        }
    }

    public void updateDueDate(int id) {
        LocalDate dueDate;
        boolean dateIsValid = false;
        System.out.println("Please enter new due date YYYY-MM-DD or press return to continue!");
        while (!dateIsValid) {
            try {
                dueDate = LocalDate.parse(scanner.nextLineToLowerCase());
                dateIsValid = true;
                taskList.get(id).setDueDate(dueDate);
                System.out.println("Due Date was changed successfully");
            } catch (DateTimeParseException e) {
                System.out.println("Please enter a valid due date YYYY-MM-DD");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "Please enter a valid due date YYYY-MM-DD");
            }
        }
    }

    /**
     * Need to consider what happens when moving to its previous state, so far just
     * skipping the step
     * If nothing done, then remove else ifs
     * 
     * @param id
     */
    public void updateStatus(int id) {
        System.out.println(
                "The current status is " + taskList.get(id).getStatus()
                        + ". Enter new status('New', 'Progress', 'Waiting', Done");
        switch (scanner.nextLineToLowerCase()) {
            case "new":
                updateStatusSuccess(id, Status.NEW);
                break;
            case "Progress":
                if (taskList.get(id).getStatus().equals(Status.NEW)) {
                    while (taskList.get(id).getResponsible().equals("")) {
                        updateResponsible(id);
                    }
                    // This must be adjusted to the solution for skipping due date entry.
                    while (taskList.get(id).getDueDate().equals(null)) {
                        updateDueDate(id);
                    }
                    updateStatusSuccess(id, Status.PROGRESS);
                } else if (taskList.get(id).getStatus().equals(Status.WAITING)
                        || taskList.get(id).getStatus().equals(Status.DONE)) {
                    updateStatusSuccess(id, Status.PROGRESS);
                }
                break;
            case "waiting":
                if (taskList.get(id).getStatus().equals(Status.NEW)) {
                    while (taskList.get(id).getResponsible().equals("")) {
                        updateResponsible(id);
                    }
                    // This must be adjusted to the solution for skipping due date entry.
                    while (taskList.get(id).getDueDate().equals(null)) {
                        updateDueDate(id);
                    }
                    updateStatusSuccess(id, Status.WAITING);
                } else if (taskList.get(id).getStatus().equals(Status.PROGRESS)
                        || taskList.get(id).getStatus().equals(Status.DONE)) {
                    updateStatusSuccess(id, Status.WAITING);
                }
                break;
            case "done":
                if (taskList.get(id).getStatus().equals(Status.NEW)) {
                    System.out.println("Can't move from New to Done");
                    // How to allow for entering a different status?
                } else if (taskList.get(id).getStatus().equals(Status.PROGRESS)
                        || taskList.get(id).getStatus().equals(Status.DONE)) {
                    updateStatusSuccess(id, Status.DONE);
                }
                break;
            default:
                System.out.println("Please enter valid status");
                break;
        }

    }

    private void updateStatusSuccess(int id, Status status) {
        String comment = "";
        boolean emptyComment = true;
        while (emptyComment) {
            emptyComment = addComment(id, comment);
        }
        taskList.get(id).setStatus(status);
        System.out.println("Status was successfully changed to " + status);
    }

    public static boolean addComment(int id, String comment) {
        if (comment.equals("")) {
            return true;
        } else {
            taskList.get(id).addComment(comment);
            return false;
        }

    }

    public static List<Task> getTaskList() {
        return taskList;
    }

    public static void setTaskList(List<Task> taskList) {
        TaskController.taskList = taskList;
    }

    public boolean isCreateMode() {
        return createMode;
    }

    public void setCreateMode(boolean createMode) {
        this.createMode = createMode;
    }

}
