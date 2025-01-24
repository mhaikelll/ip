import java.util.Scanner;

public class Huhuhuharis {
    public static void main(String[] args) {
        System.out.println("--------------------------------------------------------");
        System.out.println("Hello! I'm Huhuhuharis");
        System.out.println("What can I do for you?");
        System.out.println("--------------------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            String echo = chatResponse(input);
            System.out.println("--------------------------------------------------------");
            System.out.println(echo);
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
        } else {
            return input;
        }
    }
}
