
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.io.File;
import java.util.Iterator;

public class PropertiesController {
    private static final String PROPERTIES_FILE_PATH = "src/properties/";

    private static PropertiesController instance = new PropertiesController();
    private Properties properties;

    private PropertiesController() {
        properties = new Properties();
    }

    public static PropertiesController getInstance() {
        return instance;
    }

    // create properties file with string as file name
    public void createPropertiesFile(String fileName) {
        try (OutputStream propertiesCreator = new FileOutputStream(PROPERTIES_FILE_PATH + fileName)) {
            properties.store(propertiesCreator, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeTask(Task task) {
        String fileName = task.getTitle();
        Properties properties = new Properties();
        properties.setProperty("title", task.getTitle());
        properties.setProperty("description", task.getDescription());
        properties.setProperty("responsible", task.getResponsible());
        try {
            properties.setProperty("dueDate", task.getDueDate().toString());
        } catch (Exception e) {

        }
        properties.setProperty("status", task.getStatus().toString());

        // stringarray in csv
        // String comments = "";
        // for (int i = 0; i < task.getComments().size(); i++) {
        //     comments = comments + task.getComment(i) + "/";
        //     System.out.println(task.getComment(i));
        // }
        String comments = String.join("/", task.getComments());
        properties.setProperty("comments", comments);
        // ...
        System.out.println("comments=" + comments);
        writeProperties(properties, fileName);
    }

    public Task readTask(String fileName) {
        Properties properties = new Properties();
        try (InputStream propertiesFiles = new FileInputStream(PROPERTIES_FILE_PATH + fileName)) {
            properties.load(propertiesFiles);
            String title = properties.getProperty("title", "noTitle");
            String description = properties.getProperty("description", "no Description");
            Task task = new Task(title, description);
            task.setDueDate(LocalDate.parse(properties.getProperty("dueDate", "2999-12-31")));
            task.setStatus(Status.valueOf(properties.getProperty("status", "NEW")));
            String comments = properties.getProperty("comments", null);
            task.setComments(new ArrayList<>(Arrays.asList(comments.split("/"))));
            // ...
            return task;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, Task> readTasks() {
        Map<String, Task> tasks = new HashMap<>();
        File dir = new File(PROPERTIES_FILE_PATH);
        String[] files = dir.list();
        for (String file : files) {
            Task task = readTask(file);
            tasks.put(task.getTitle(), task);
        }

        return tasks;
    }

    public static void writeTasks() {
        Iterator<Task> iterator = TaskController.getTaskList().values().iterator();
        while (iterator.hasNext()) {
            Task task = iterator.next();
            writeTask(task);
        }
    }

    public static void writeProperties(Properties properties, String fileName) {
        if (!TaskController.getTaskList().isEmpty()) {
            try (OutputStream propertiesFiles = new FileOutputStream(PROPERTIES_FILE_PATH + fileName + ".properties")) {
                properties.store(propertiesFiles, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}