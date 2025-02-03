import java.time.LocalDateTime;

public class Huhuhuharis {
    private Ui ui;
    private static TaskList taskList;
    private Storage storage;

    public Huhuhuharis(String path) throws HuhuhuharisException {
        ui = new Ui();
        storage = new Storage("./data/huhuhuharis.txt");
        taskList = new TaskList(Storage.loadTasks());
    }

    public void run() {
        ui.showWelcomeMessage();
        while (true) {
            String input = ui.getUserInput();
            String reply = chatResponse(input);
            ui.showTaskList(reply);
            if (input.equals("bye")) {
                ui.showExitMessage();
                break;
            }
        }
        Storage.saveTasks(taskList.getTasks());
    }

    public static String chatResponse(String input) {
        try {
            if (input.equals("bye")) {
                return "Bye. Hope to see you again.";
            } else if (input.equals("list")) {
                return taskList.fullList();
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
            } else {
                throw new HuhuhuharisException("Invalid command input!");
            }
        } catch (HuhuhuharisException err) {
            return err.getMessage();
        }
    }

    public static String handleEvent(String input) throws HuhuhuharisException {
        String description = input.split(" /from ", 2)[0].replace("event", "");
        if (description.isEmpty()) {
            throw new HuhuhuharisException("Empty Event Description!");
        }
        String str1 = input.split(" /from ", 2)[1].split(" /to ", 2)[0];
        String str2 = input.split(" /from ", 2)[1].split(" /to ", 2)[1];
        LocalDateTime from = Parser.strToDateTime(str1);
        LocalDateTime to = Parser.strToDateTime(str2);
        taskList.addTask(new Event(description, from, to));
        Storage.saveTasks(taskList.getTasks());
        return "Got it. I've added this task:\n" + taskList.getTask(taskList.getListCount() - 1) + "\nNow you have " + taskList.getListCount() + " tasks in the list.";
    }

    public static String handleDeadline(String input) throws HuhuhuharisException {
        String description = input.split(" /by ", 2)[0].replace("deadline", "");
        if (description.isEmpty()) {
            throw new HuhuhuharisException("Empty Deadline Description!");
        }
        String str = input.split(" /by ", 2)[1];
        LocalDateTime by = Parser.strToDateTime(str);
        taskList.addTask(new Deadline(description, by));
        Storage.saveTasks(taskList.getTasks());
        return "Got it. I've added this task:\n" + taskList.getTask(taskList.getListCount() - 1) + "\nNow you have " + taskList.getListCount() + " tasks in the list.";
    }

    public static String handleTodo(String input) throws HuhuhuharisException {
        String description = input.replace("todo", "");
        if (description.isEmpty()) {
            throw new HuhuhuharisException("Empty ToDo Description!");
        }
        taskList.addTask(new Todo(description));
        Storage.saveTasks(taskList.getTasks());
        return "Got it. I've added this task:\n" + taskList.getTask(taskList.getListCount() - 1) + "\nNow you have " + taskList.getListCount() + " tasks in the list.";
    }

    public static String deleteTask(String input) {
        int taskId = Integer.parseInt(input.split(" ")[1]) - 1;
        Task deletedTask = taskList.removeTask(taskId);
        Storage.saveTasks(taskList.getTasks());
        return "Noted. I've removed this task:\n" + deletedTask + "\nNow you have " + taskList.getListCount() + " tasks in the list.";
    }

    public static String markTask(String input) {
        int taskId = Integer.parseInt(input.split(" ")[1]) - 1;
        taskList.markDone(taskId);
        Storage.saveTasks(taskList.getTasks());
        return "Nice! I've marked this task as done:\n" + taskList.getTask(taskId);
    }

    public static String unmarkTask(String input) {
        int taskId = Integer.parseInt(input.split(" ")[1]) - 1;
        taskList.unmarkTask(taskId);
        Storage.saveTasks(taskList.getTasks());
        return "OK, I've marked this task as not done yet:\n" + taskList.getTask(taskId);
    }

    public static void main(String[] args) throws HuhuhuharisException {
        new Huhuhuharis("./data/huhuhuharis.txt").run();
    }
}


