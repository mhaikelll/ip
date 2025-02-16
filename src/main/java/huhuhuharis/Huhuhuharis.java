package huhuhuharis;

import java.time.LocalDateTime;

/**
 * Represents the main class for the Huhuhuharis program. It handles the overall execution of the program.
 */
public class Huhuhuharis {
    private Ui ui;
    private static TaskList taskList;
    private Storage storage;

    /**
     * Constructs a Huhuhuharis instance with the input file path.
     */
    public Huhuhuharis() {
        ui = new Ui();
        storage = new Storage("./data/huhuhuharis.txt");
        taskList = new TaskList(Storage.loadTasks());
        Storage.saveTasks(taskList.getTasks());
    }

    /**
     * Runs the program and continuously receives input until the user enters "bye".
     * Processes the commands based on the user input.
     */
    public void run() {
        ui.showWelcomeMessage();
        while (true) {
            String input = ui.getUserInput();
            String reply = chatResponse(input);
            ui.showTaskList(reply);
            if (input.equals("bye")) {
                break;
            }
        }
        Storage.saveTasks(taskList.getTasks());
    }

    /**
     * Processes user input and generates responses.
     *
     * @param input The user input to process.
     * @return The response given to the user.
     */
    public String chatResponse(String input) {
        try {
            if (input.equals("bye")) {
                return "Huhubye. Huhuhuharis hopes to see you again.";
            } else if (input.equals("list")) {
                return taskList.fullList();
            } else if (input.startsWith("find")) {
                return findTask(input);
            } else if (input.startsWith("mark")) {
                return markTask(input);
            } else if (input.startsWith("unmark")) {
                return unmarkTask(input);
            } else if (input.startsWith("event")) {
                return handleEvent(input);
            } else if (input.startsWith("deadline")) {
                return handleDeadline(input);
            } else if (input.startsWith("todo")) {
                return handleTodo(input);
            } else if (input.startsWith("delete")) {
                return deleteTask(input);
            } else if (input.startsWith("priority")) {
                return handlePriority(input);
            } else {
                throw new HuhuhuharisException("Invalid command input.");
            }
        } catch (HuhuhuharisException err) {
            return err.getMessage();
        }
    }

    /**
     * Handles the creation of a new Event task.
     *
     * @param input The user's input command.
     * @return The response message after the Event task is added.
     */
    public static String handleEvent(String input) throws HuhuhuharisException {
        assert input != null : "Input should not be null";
        if (!input.contains(" /from ") || !input.contains(" /to ")) {
            throw new HuhuhuharisException("Invalid format. Use: event [description] /from [yyyy-MM-dd HHmm] /to [yyyy-MM-dd HHmm]");
        }
        String description = input.split(" /from ", 2)[0].replace("event", "");
        if (description.isEmpty()) {
            throw new HuhuhuharisException("Empty event description.");
        }
        String[] times = input.split(" /from ", 2)[1].split(" /to ", 2);
        if (times.length < 2) {
            throw new HuhuhuharisException("Invalid format. Use: event [description] /from [yyyy-MM-dd HHmm] /to [yyyy-MM-dd HHmm]");
        }
        assert times.length == 2 : "Event should contain both /from and /to";
        LocalDateTime from = Parser.strToDateTime(times[0]);
        LocalDateTime to = Parser.strToDateTime(times[1]);
        assert from != null && to != null : "Event times should not be null";
        taskList.addTask(new Event(description, from, to));
        Storage.saveTasks(taskList.getTasks());
        return "Huhuyes! Huhuhuharis added this task:\n" + taskList.getTask(taskList.getListCount() - 1) + "\nNow you have " + taskList.getListCount() + " tasks in the list.";
    }

    /**
     * Handles the creation of a new Deadline task.
     *
     * @param input The user's input command.
     * @return The response message after the Deadline task is added.
     */
    public static String handleDeadline(String input) throws HuhuhuharisException {
        assert input != null : "Input should not be null";
        if (!input.contains(" /by ")) {
            throw new HuhuhuharisException("Invalid format. Use: deadline [description] /by [yyyy-MM-dd HHmm]");
        }
        String description = input.split(" /by ", 2)[0].replace("deadline", "");
        if (description.isEmpty()) {
            throw new HuhuhuharisException("Empty deadline description.");
        }
        String str = input.split(" /by ", 2)[1];
        LocalDateTime by = Parser.strToDateTime(str);
        assert by != null : "Deadline time should not be null";
        taskList.addTask(new Deadline(description, by));
        Storage.saveTasks(taskList.getTasks());
        return "Huhuyes! Huhuhuharis added this task:\n" + taskList.getTask(taskList.getListCount() - 1) + "\nNow you have " + taskList.getListCount() + " tasks in the list.";
    }

    /**
     * Handles the creation of a new Todo task.
     *
     * @param input The user's input command.
     * @return The response message after the Todo task is added.
     */
    public static String handleTodo(String input) throws HuhuhuharisException {
        assert input != null : "Input should not be null";
        String description = input.replace("todo", "");
        if (description.isEmpty()) {
            throw new HuhuhuharisException("Empty todo description.");
        }
        taskList.addTask(new Todo(description));
        Storage.saveTasks(taskList.getTasks());
        return "Huhuyes! Huhuhuharis added this task:\n" + taskList.getTask(taskList.getListCount() - 1) + "\nNow you have " + taskList.getListCount() + " tasks in the list.";
    }

    public static String handlePriority(String input) throws HuhuhuharisException {
        assert input != null : "Input should not be null";
        String[] parts = input.split(" ", 3);
        if (parts.length < 3) {
            return "Invalid priority command. Use: priority [task no.] [High/Medium/Low]";
        }
        int taskId = Integer.parseInt(parts[1]) - 1;
        if (taskId < 0 || taskId >= taskList.getListCount()) {
            throw new HuhuhuharisException("Task number out of range.");
        }
        String priority = parts[2];
        try {
            taskList.getTask(taskId).setPriority(priority);
            Storage.saveTasks(taskList.getTasks());
            return "Huhuimportant! Priority set for task:\n" + taskList.getTask(taskId);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    /**
     * Handles the deletion of a task.
     *
     * @param input The user's input command.
     * @return The response message after the given task is deleted.
     */
    public static String deleteTask(String input) throws HuhuhuharisException {
        assert input != null : "Input should not be null";
        String[] split = input.split(" ");
        if (split.length < 2) {
            throw new HuhuhuharisException("Invalid format. Use: [delete] [task no.]");
        }
        assert split.length >= 2 : "Delete command should split into at least two parts";
        int taskId = Integer.parseInt(split[1]) - 1;
        if (taskId < 0 || taskId >= taskList.getListCount()) {
            throw new HuhuhuharisException("Task number out of range.");
        }
        Task deletedTask = taskList.removeTask(taskId);
        Storage.saveTasks(taskList.getTasks());
        return "Huhuokay. Huhuhuharis removed this task:\n" + deletedTask + "\nNow you have " + taskList.getListCount() + " tasks in the list.";
    }

    /**
     * Handles the finding of a task based on the specified keyword given.
     *
     * @param input The user's input command.
     * @return The response message with the given tasks.
     */
    public static String findTask(String input) {
        assert input != null : "Input should not be null";
        String keyword = input.split(" ", 2)[1];
        StringBuilder result = new StringBuilder();
        result.append("Huhuhuharis found these matching tasks in your list:\n");
        boolean isFound = false;
        for (int i = 0; i < taskList.getListCount(); i++) {
            Task task = taskList.getTask(i);
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                isFound = true;
                result.append(i + 1).append(".").append(task).append("\n");
            }
        }
        if (!isFound) {
            result.append("Huhuno! Huhuhuharis found no tasks matching the keyword: ").append(keyword);
        }
        return result.toString();
    }

    /**
     * Handles the marking of a task as done.
     *
     * @param input The user's input command.
     * @return The response message after the given task is marked as done.
     */
    public static String markTask(String input) throws HuhuhuharisException {
        assert input != null : "Input should not be null";
        String[] split = input.split(" ");
        if (split.length < 2) {
            throw new HuhuhuharisException("Invalid format. Use: [unmark] [task no.]");
        }
        assert split.length >= 2 : "Mark command should split into at least two parts";
        int taskId = Integer.parseInt(split[1]) - 1;
        if (taskId < 0 || taskId >= taskList.getListCount()) {
            throw new HuhuhuharisException("Task number out of range.");
        }
        taskList.markDone(taskId);
        Storage.saveTasks(taskList.getTasks());
        return "Huhuyes! Huhuhuharis marked this task as done:\n" + taskList.getTask(taskId);
    }

    /**
     * Handles the unmarking of a task.
     *
     * @param input The user's input command.
     * @return The response message after the given task is unmarked.
     */
    public static String unmarkTask(String input) throws HuhuhuharisException {
        assert input != null : "Input should not be null";
        String[] split = input.split(" ");
        if (split.length < 2) {
            throw new HuhuhuharisException("Invalid format. Use: [unmark] [task no.]");
        }
        assert split.length >= 2 : "Unmark command should split into at least two parts";
        int taskId = Integer.parseInt(split[1]) - 1;
        if (taskId < 0 || taskId >= taskList.getListCount()) {
            throw new HuhuhuharisException("Task number out of range.");
        }
        taskList.unmarkTask(taskId);
        Storage.saveTasks(taskList.getTasks());
        return "Huhuno! Huhuhuharis marked this task as not done yet:\n" + taskList.getTask(taskId);
    }

    /**
     * Begins the Huhuhuharis task management program.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) throws HuhuhuharisException {
        new Huhuhuharis().run();
    }
}


