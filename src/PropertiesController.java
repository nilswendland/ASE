
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.io.File;

public class PropertiesController {
    private static final String PROPERTIES_FILE_PATH = "src/main/resources/";

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
        properties.setProperty("responsible", task.getResponsible());
        properties.setProperty("dueDate", task.getDueDate().toString());
        properties.setProperty("status", task.getStatus().toString());
        // ...
        writeProperties(properties, fileName);
    }

    public Task readTask(String fileName) {
        Properties properties = new Properties();
        try (InputStream propertiesFiles = new FileInputStream(PROPERTIES_FILE_PATH + fileName)) {
            String title = properties.getProperty("title");
            String description = properties.getProperty("description");
            Task task = new Task(title, description);
            task.setDueDate(LocalDate.parse(properties.getProperty("dueDate")));
            task.setStatus(Status.valueOf(properties.getProperty("status")));

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
        TaskController.getTaskList().forEach((title, task) -> writeTask(task));
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

    // load properties file
    /*
     * public void loadPropertiesFile(String fileName) {
     * try {
     * properties.load(new FileInputStream(PROPERTIES_FILE_PATH + fileName));
     * } catch (IOException e) {
     * e.printStackTrace();
     * }
     * }
     */
}