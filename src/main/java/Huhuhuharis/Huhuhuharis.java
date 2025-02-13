package Huhuhuharis;

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
        taskList.addAll(Storage.loadTasks());
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
                return "Bye. Hope to see you again.";
            } else if (input.equals("suck my dick")) {
                return "Yes, I would absolutely love that.";
            } else if (input.equals("can you stop being so annoying")) {
                return "No, I'm the biggest most annoying loser to ever exist.";
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
                throw new HuhuhuharisException("Invalid command input!");
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
        String description = input.split(" /from ", 2)[0].replace("event", "");
        if (description.isEmpty()) {
            throw new HuhuhuharisException("Empty Huhuhuharis.Huhuhuharis.Event Description!");
        }
        String str1 = input.split(" /from ", 2)[1].split(" /to ", 2)[0];
        String str2 = input.split(" /from ", 2)[1].split(" /to ", 2)[1];
        LocalDateTime from = Parser.strToDateTime(str1);
        LocalDateTime to = Parser.strToDateTime(str2);
        taskList.addTask(new Event(description, from, to));
        Storage.saveTasks(taskList.getTasks());
        return "Got it. I've added this task:\n" + taskList.getTask(taskList.getListCount() - 1) + "\nNow you have " + taskList.getListCount() + " tasks in the list.";
    }

    /**
     * Handles the creation of a new Deadline task.
     *
     * @param input The user's input command.
     * @return The response message after the Deadline task is added.
     */
    public static String handleDeadline(String input) throws HuhuhuharisException {
        String description = input.split(" /by ", 2)[0].replace("deadline", "");
        if (description.isEmpty()) {
            throw new HuhuhuharisException("Empty Huhuhuharis.Huhuhuharis.Deadline Description!");
        }
        String str = input.split(" /by ", 2)[1];
        LocalDateTime by = Parser.strToDateTime(str);
        taskList.addTask(new Deadline(description, by));
        Storage.saveTasks(taskList.getTasks());
        return "Got it. I've added this task:\n" + taskList.getTask(taskList.getListCount() - 1) + "\nNow you have " + taskList.getListCount() + " tasks in the list.";
    }

    /**
     * Handles the creation of a new Todo task.
     *
     * @param input The user's input command.
     * @return The response message after the Todo task is added.
     */
    public static String handleTodo(String input) throws HuhuhuharisException {
        String description = input.replace("todo", "");
        if (description.isEmpty()) {
            throw new HuhuhuharisException("Empty ToDo Description!");
        }
        taskList.addTask(new Todo(description));
        Storage.saveTasks(taskList.getTasks());
        return "Got it. I've added this task:\n" + taskList.getTask(taskList.getListCount() - 1) + "\nNow you have " + taskList.getListCount() + " tasks in the list.";
    }

    public static String handlePriority(String input) {
        String[] parts = input.split(" ", 3);
        if (parts.length < 3) {
            return "Invalid priority command. Use: priority [task number] [High/Medium/Low]";
        }
        int taskId = Integer.parseInt(parts[1]) - 1;;
        String priority = parts[2];
        try {
            taskList.getTask(taskId).setPriority(priority);
            Storage.saveTasks(taskList.getTasks());
            return "Priority set for task:\n" + taskList.getTask(taskId);
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
    public static String deleteTask(String input) {
        int taskId = Integer.parseInt(input.split(" ")[1]) - 1;
        Task deletedTask = taskList.removeTask(taskId);
        Storage.saveTasks(taskList.getTasks());
        return "Noted. I've removed this task:\n" + deletedTask + "\nNow you have " + taskList.getListCount() + " tasks in the list.";
    }

    /**
     * Handles the finding of a task based on the specified keyword given.
     *
     * @param input The user's input command.
     * @return The response message with the given tasks.
     */
    public static String findTask(String input) {
        String keyword = input.split(" ", 2)[1];
        StringBuilder result = new StringBuilder();
        result.append("Here are the matching tasks in your list:\n");
        boolean isFound = false;
        for (int i = 0; i < taskList.getListCount(); i++) {
            Task task = taskList.getTask(i);
            if (task.getDescription().contains(keyword)) {
                isFound = true;
                result.append(i + 1).append(".").append(task).append("\n");
            }
        }
        if (!isFound) {
            result.append("No tasks found matching the keyword: ").append(keyword);
        }
        return result.toString();
    }

    /**
     * Handles the marking of a task as done.
     *
     * @param input The user's input command.
     * @return The response message after the given task is marked as done.
     */
    public static String markTask(String input) {
        int taskId = Integer.parseInt(input.split(" ")[1]) - 1;
        taskList.markDone(taskId);
        Storage.saveTasks(taskList.getTasks());
        return "Nice! I've marked this task as done:\n" + taskList.getTask(taskId);
    }

    /**
     * Handles the unmarking of a task.
     *
     * @param input The user's input command.
     * @return The response message after the given task is unmarked.
     */
    public static String unmarkTask(String input) {
        int taskId = Integer.parseInt(input.split(" ")[1]) - 1;
        taskList.unmarkTask(taskId);
        Storage.saveTasks(taskList.getTasks());
        return "OK, I've marked this task as not done yet:\n" + taskList.getTask(taskId);
    }

    /**
     * Main entry point for the program.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) throws HuhuhuharisException {
        new Huhuhuharis().run();
    }
}


