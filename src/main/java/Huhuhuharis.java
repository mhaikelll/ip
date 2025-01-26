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
        if (input.equals("bye")) {
            return "Bye. Hope to see you again.";
        } else if (input.equals("list")) {
            return fullList();
        } else if (input.startsWith("mark")) {
            return markTask(input);
        } else if (input.startsWith("unmark")) {
            return unmarkTask(input);
        } else {
            return addToList(input);
        }
    }

    public static String fullList() {
        String fullList = "Here are the tasks in your list:\n";
        for (int i = 0; i < listCount; i++) {
            fullList += (i + 1) + "." + todoList[i] + "\n";
        }
        return fullList;
    }

    public static String addToList(String input) { // change
        if (input.startsWith("event")) {
            String description = input.split(" /from ", 2)[0].replace("event", "");
            String from = input.split(" /from ", 2)[1].split(" /to ", 2)[0];
            String to = input.split(" /from ", 2)[1].split(" /to ", 2)[1];
            todoList[listCount] = new Event(description, from, to);
        } else if (input.startsWith("deadline")) {
            String description = input.split(" /by ", 2)[0].replace("deadline", "");
            String by = input.split(" /by ", 2)[1];
            todoList[listCount] = new Deadline(description, by);
        } else {
            String description = input.replace("todo", "");
            todoList[listCount] = new ToDo(description);
        }
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


