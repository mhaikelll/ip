import java.util.Scanner;

public class Huhuhuharis {
    private static Task[] todoList = new Task[100];
    private static int listCount = 0;
    public static void main(String[] args) {
        System.out.println("--------------------------------------------------------");
        System.out.println("Hello! I'm Huhuhuharis");
        System.out.println("What can I do for you?");
        System.out.println("--------------------------------------------------------");
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
                return handleToDo(input);
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
            fullList += (i + 1) + "." + todoList[i] + "\n";
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
        todoList[listCount] = new Event(description, from, to);
        listCount++;
        return "Got it. I've added this task:\n" + todoList[listCount - 1] + "\nNow you have " + listCount + " tasks in the list.";
    }

    public static String handleDeadline(String input) throws HuhuhuharisException {
        String description = input.split(" /by ", 2)[0].replace("deadline", "");
        if (description.isEmpty()) {
            throw new HuhuhuharisException("Empty Deadline Description!");
        }
        String by = input.split(" /by ", 2)[1];
        todoList[listCount] = new Deadline(description, by);
        listCount++;
        return "Got it. I've added this task:\n" + todoList[listCount - 1] + "\nNow you have " + listCount + " tasks in the list.";
    }

    public static String handleToDo(String input) throws HuhuhuharisException {
        String description = input.replace("todo", "");
        if (description.isEmpty()) {
            throw new HuhuhuharisException("Empty ToDo Description!");
        }
        todoList[listCount] = new ToDo(description);
        listCount++;
        return "Got it. I've added this task:\n" + todoList[listCount - 1] + "\nNow you have " + listCount + " tasks in the list.";
    }

    public static String markTask(String input) {
        int taskId = Integer.parseInt(input.split(" ")[1]) - 1;
        todoList[taskId].mark();
        return "Nice! I've marked this task as done:\n" + todoList[taskId];
    }

    public static String unmarkTask(String input) {
        int taskId = Integer.parseInt(input.split(" ")[1]) - 1;
        todoList[taskId].unmark();
        return "OK, I've marked this task as not done yet:\n" + todoList[taskId];
    }
}


