package huhuhuharis;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String path;

    /**
     * Constructs a Storage object for task storage filing.
     *
     * @param path The file path where tasks are saved or loaded from.
     */
    public Storage(String path) {
        this.path = path;
    }
    private static final String FILE_PATH = "./data/huhuhuharis.txt";

    /**
     * Handles the saving of tasks to a file in a specific format for future retrieval.
     *
     * @param tasks The tasks to be saved to the file.
     */
    public static void saveTasks(ArrayList<Task> tasks) {
        try {
            File file = new File("./data");
            if (!file.exists()) {
                file.mkdir();
            }
            FileWriter writer = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                writer.write(task.saveToFile() + " | " + task.getPriority() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Handles the loading and reconstructing of tasks from the saved file.
     *
     * @return Tasks loaded from the file.
     */
    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                System.out.println("No task data found.");
                return tasks;
            }
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(" \\| ");
                Task task = null;
                String priority = parts[parts.length - 1];
                switch (parts[0]) {
                    case "T":
                        task = new Todo(parts[2]);
                        break;
                    case "D":
                        LocalDateTime by = Parser.strToDateTime(parts[3]);
                        task = new Deadline(parts[2], by);
                        break;
                    case "E":
                        LocalDateTime from = Parser.strToDateTime(parts[3]);
                        LocalDateTime to = Parser.strToDateTime(parts[4]);
                        task = new Event(parts[2], from, to);
                        break;
                }
                if (task != null) {
                    if (parts[1].equals("1")) {
                        task.mark();
                    }
                    task.setPriority(priority);
                    tasks.add(task);
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }
}
