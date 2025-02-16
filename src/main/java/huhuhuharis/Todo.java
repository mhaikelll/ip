package huhuhuharis;

public class Todo extends Task {
    /**
     * Constructs a Todo task with description.
     *
     * @param description The description of the event.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the todo task reconstructed to allow for file storage.
     *
     * @return String representation of the todo task for file storage.
     */
    @Override
    public String saveToFile() {
        return "T | " + (isDone ? "1" : "0") + " | " + description + " | " + priority;
    }
}
