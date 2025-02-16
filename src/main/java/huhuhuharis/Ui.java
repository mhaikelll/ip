package huhuhuharis;

import java.util.Scanner;

public class Ui {

    public Ui() {}

    public void showWelcomeMessage() {
        System.out.println("--------------------------------------------------------");
        System.out.println("Huhuhello! I'm Huhuhuharis");
        System.out.println("What can Huhuhuharis do for you today?");
        System.out.println("--------------------------------------------------------");
    }

    public String stringWelcomeMessage() {
        return "Huhuhello! I'm Huhuhuharis. What can Huhuhuharis do for you today?";
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

