import java.util.Scanner;

public class Ui {

    public Ui() {}

    public void showWelcomeMessage() {
        System.out.println("--------------------------------------------------------");
        System.out.println("Hello! I'm Huhuhuharis");
        System.out.println("What can I do for you?");
        System.out.println("--------------------------------------------------------");
    }

    public void showExitMessage() {
        System.out.println("--------------------------------------------------------");
        System.out.println("Bye. Hope to see you again.");
        System.out.println("--------------------------------------------------------");
    }

    public void showTaskList(String fullList) {
        System.out.println("--------------------------------------------------------");
        System.out.println(fullList);
        System.out.println("--------------------------------------------------------");
    }

    public String getUserInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}

