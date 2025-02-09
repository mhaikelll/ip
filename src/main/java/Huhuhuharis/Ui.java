package Huhuhuharis;

import java.util.Scanner;

public class Ui {

    public Ui() {}

    public void showWelcomeMessage() {
        System.out.println("--------------------------------------------------------");
        System.out.println("Hello! I'm Huhuhuharis");
        System.out.println("What can I do for you?");
        System.out.println("--------------------------------------------------------");
    }

    public String stringWelcomeMessage() {
        return "Hello! I'm Huhuhuharis. What can I do for you?";
    }

    public void showTaskList(String fullList) {
        System.out.println("--------------------------------------------------------");
        System.out.println(fullList);
        System.out.println("--------------------------------------------------------");
    }

    /**
     * Retrieves user input through the console by entering a line of text and subsequently returning it.
     *
     * @return String input entered through the console.
     */
    public String getUserInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}

