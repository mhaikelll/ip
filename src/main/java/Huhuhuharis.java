import java.util.ArrayList;
import java.util.Scanner;

public class Huhuhuharis {
    private static final ArrayList<Task> todoList = new ArrayList<>();
    private static int listCount = 0;
    public static void main(String[] args) {
        System.out.println("--------------------------------------------------------");
        System.out.println("Hello! I'm Huhuhuharis");
        System.out.println("What can I do for you?");
        System.out.println("--------------------------------------------------------");
        todoList.addAll(Storage.loadTasks());
        listCount = todoList.size();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            String reply = chatResponse(input);
            System.out.println("--------------------------------------------------------");
            System.out.println(reply);
            System.out.println("--------------------------------------------------------");
            if (input.equals("bye")) {
                break;
            }
        }
        Storage.saveTasks(todoList);
        scanner.close();
    }

    public static String chatResponse(String input) {
        try {
            if (input.equals("bye")) {
                return "Bye. Hope to see you again.";
            } else if (input.equals("list")) {
                return fullList();
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

    public static String fullList() {
        String fullList = "Here are the tasks in your list:\n";
        for (int i = 0; i < listCount; i++) {
            fullList += (i + 1) + "." + todoList.get(i) + "\n";
        }
        return fullList;
    }

    public static String handleEvent(String input) throws HuhuhuharisException {
        String description = input.split(" /from ", 2)[0].replace("event", "");
        if (description.isEmpty()) {
            throw new HuhuhuharisException("Empty Event Description!");
        }
        String from = input.split(" /from ", 2)[1].split(" /to ", 2)[0];
        String to = input.split(" /from ", 2)[1].split(" /to ", 2)[1];
        todoList.add(new Event(description, from, to));
        listCount++;
        Storage.saveTasks(todoList);
        return "Got it. I've added this task:\n" + todoList.get(listCount - 1) + "\nNow you have " + listCount + " tasks in the list.";
    }

    public static String handleDeadline(String input) throws HuhuhuharisException {
        String description = input.split(" /by ", 2)[0].replace("deadline", "");
        if (description.isEmpty()) {
            throw new HuhuhuharisException("Empty Deadline Description!");
        }
        String by = input.split(" /by ", 2)[1];
        todoList.add(new Deadline(description, by));
        listCount++;
        Storage.saveTasks(todoList);
        return "Got it. I've added this task:\n" + todoList.get(listCount - 1) + "\nNow you have " + listCount + " tasks in the list.";
    }

    public static String handleTodo(String input) throws HuhuhuharisException {
        String description = input.replace("todo", "");
        if (description.isEmpty()) {
            throw new HuhuhuharisException("Empty ToDo Description!");
        }
        todoList.add(new Todo(description));
        listCount++;
        Storage.saveTasks(todoList);
        return "Got it. I've added this task:\n" + todoList.get(listCount - 1) + "\nNow you have " + listCount + " tasks in the list.";
    }

    public static String deleteTask(String input) {
        int taskId = Integer.parseInt(input.split(" ")[1]) - 1;
        Task deletedTask = todoList.remove(taskId);
        listCount--;
        Storage.saveTasks(todoList);
        return "Noted. I've removed this task:\n" + deletedTask + "\nNow you have " + listCount + " tasks in the list.";
    }

    public static String markTask(String input) {
        int taskId = Integer.parseInt(input.split(" ")[1]) - 1;
        todoList.get(taskId).mark();
        Storage.saveTasks(todoList);
        return "Nice! I've marked this task as done:\n" + todoList.get(taskId);
    }

    public static String unmarkTask(String input) {
        int taskId = Integer.parseInt(input.split(" ")[1]) - 1;
        todoList.get(taskId).unmark();
        Storage.saveTasks(todoList);
        return "OK, I've marked this task as not done yet:\n" + todoList.get(taskId);
    }
}


