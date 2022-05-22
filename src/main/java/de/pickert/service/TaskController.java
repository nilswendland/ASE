package de.pickert.service;

import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.HashMap;

import de.pickert.domain.Status;
import de.pickert.domain.Task;
import de.pickert.utils.UserScanner;

public class TaskController {
    // private static List<Task> taskList = new ArrayList<>();
    private static Map<String, Task> taskList = new HashMap<>();
    private boolean createMode;
    private static UserScanner scanner;
    private String taskTitle;
    private PropertiesController propertiesController;
    private static TaskController instance = new TaskController();
    private boolean forceComment;
    private boolean forceResponsible;

    public boolean isForceResponsible() {
        return forceResponsible;
    }

    public void setForceResponsible(boolean forceResponsible) {
        this.forceResponsible = forceResponsible;
    }

    public boolean isForceComment() {
        return forceComment;
    }

    public void setForceComment(boolean forceComment) {
        this.forceComment = forceComment;
    }

    TaskController() {
        scanner = UserScanner.getInstance();
        propertiesController = PropertiesController.getInstance();
        taskList = propertiesController.readTasks();
    }

    public static TaskController getInstance() {
        return instance;
    }

    public void createTask() {
        System.out.println("You're creating a new task. Please enter title or type 'cancel' to get back to main menu");
        taskTitle = scanner.nextLineToLowerCase();
        while (taskList.get(taskTitle) != null) {
            System.out.println("Task with this title already exists. Please choose a different title");
            taskTitle = scanner.nextLineToLowerCase();
        }
        System.out.println("Please enter a description for your task");
        String description = scanner.nextLine();
        if (description.equalsIgnoreCase("cancel")) {
            createMode = false;
            return;
        }
        Task newTask = new Task(taskTitle, description);
        taskList.put(taskTitle, newTask);
        System.out.println("Your task " + newTask.getTitle() + " has been created successfully");
        System.out.println("Do you want to create another task? Y/N");
        if (!scanner.nextLineToLowerCase().equals("y")) {
            createMode = false;
        }
    }

    public void updateTask(Task taskToUpdate) {
        setForceComment(false);
        setForceResponsible(false);
        Task newTask = new Task("", "");
        newTask.setTitle(checkTitle(taskToUpdate.getTitle()));
        newTask.setDescription(checkDescription(taskToUpdate.getDescription()));
        newTask.setStatus(checkStatus(taskToUpdate.getStatus()));
        newTask.setDueDate(checkDueDate(taskToUpdate.getDueDate()));
        newTask.setResponsible(checkResponsible(taskToUpdate.getResponsible()));
        newTask.setComments(checkComments(taskToUpdate.getComments()));
        System.out.println("Comments im Task" + newTask.getComments());
        PropertiesController.writeTask(newTask);

    }

    public boolean edited(String text) {
        return (!text.equals(""));
    }

    public String checkTitle(String oldTaskTitle) {
        System.out.println("If you want to edit title, please enter new title. Press return to continue");
        taskTitle = scanner.nextLineToLowerCase();
        if (edited(taskTitle)) {
            while (taskList.containsKey(taskTitle)) {
                System.out.println("title already exists! Please enter new title");
                taskTitle = scanner.nextLineToLowerCase();
            }
            return taskTitle;
        }
        return oldTaskTitle;
    }

    public String checkDescription(String oldDescription) {
        System.out.println("Enter a new decription to change it. Press return to continue");
        String description = scanner.nextLineToLowerCase();
        if (edited(description))
            return description;
        else
            return oldDescription;
    }

    public String checkResponsible(String oldResponsible) {
        System.out.println("Enter the name of the person responsible for the task or press enter to skip");
        String responsible = scanner.nextLineToLowerCase();
        if (isForceResponsible()) {
            while (!edited(responsible)) {
                System.out.println("Responisble is required! Please enter responsible!");
                responsible = scanner.nextLineToLowerCase();
            }
            return responsible;
        }
        if (edited(responsible))
            return responsible;
        else
            return oldResponsible;
    }

    public LocalDate checkDueDate(LocalDate oldDueDate) {
        System.out.println("Please enter new due date YYYY-MM-DD or press return to continue!");

        String newDate = scanner.nextLine();

        if (edited(newDate)) {

            while (!isValidDate(newDate)) {
                System.out.println("Please enter a valid date YYYY-MM-DD");
                newDate = scanner.nextLine();
            }
            return LocalDate.parse(newDate);
        } else {
            try {
                return oldDueDate;
            } catch (Exception e) {
                return LocalDate.parse("2999-12-31");
            }
        }
    }

    public boolean isValidDate(String dateStr) {
        try {
            LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }


    public Status checkStatus(Status oldStatus) {

        if (oldStatus.equals(Status.NEW)) {
            forceComment = true;
            forceResponsible = true;
        }
        System.out.println(
                "The current status is " + oldStatus
                        + ". Enter new status('Progress', 'Waiting', 'Done'");
        switch (scanner.nextLineToLowerCase()) {
            case "progress":
                if (oldStatus.equals(Status.WAITING)
                        || oldStatus.equals(Status.DONE)) {
                    forceComment = true;
                }
                return Status.PROGRESS;
            case "waiting":
                if (oldStatus.equals(Status.PROGRESS)
                        || oldStatus.equals(Status.DONE)) {
                    forceComment = true;
                }
                return Status.WAITING;
            case "done":
                if (oldStatus.equals(Status.NEW)) {
                    System.out.println("Can't move from New to Done");
                    // How to allow for entering a different status?
                } else if (oldStatus.equals(Status.PROGRESS)
                        || oldStatus.equals(Status.WAITING)) {
                    forceComment = true;
                }
                return Status.DONE;
            default:
                System.out.println("No Status change!");
                forceComment = false;
                forceResponsible = false;
                return oldStatus;
        }
    }

    public List<String> checkComments(List<String> comments) {
        System.out.println("Please enter a new comment or press enter to skip");
        String newComment = scanner.nextLine();
        System.out.println(newComment);
        while (isForceComment() && !edited(newComment)) {
            System.out.println("Comment is required! Please enter a new comment!");
            newComment = scanner.nextLine();
        }
        if (edited(newComment)) {
            /**
             * This try catch can be omitted once comments.add doesn't throw Exception
             * anymore
             */
            try {
                comments.add(newComment);
            } catch (Exception e) {
                System.out.println(newComment);
                System.out.println("Fehler: " + e.getCause() + e.getMessage());
            }
        }
        return comments;
    }

    public static Map<String, Task> getTaskList() {
        return taskList;
    }

    public static void setTaskList(Map<String, Task> taskList) {
        TaskController.taskList = taskList;
    }

    public boolean isCreateMode() {
        return createMode;
    }

    public void setCreateMode(boolean createMode) {
        this.createMode = createMode;
    }

}
