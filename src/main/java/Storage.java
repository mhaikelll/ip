import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String path;

    public Storage(String path) {
        this.path = path;
    }
    private static final String FILE_PATH = "./data/huhuhuharis.txt";

    public static void saveTasks(ArrayList<Task> tasks) {
        try {
            File file = new File("./data");
            if (!file.exists()) {
                file.mkdir();
            }
            FileWriter writer = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                writer.write(task.saveToFile() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

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
                if (task != null && parts[1].equals("1")) {
                    task.mark();
                }
                tasks.add(task);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }
}
