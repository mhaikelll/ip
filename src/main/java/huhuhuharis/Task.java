package huhuhuharis;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String priority;

    /**
     * Constructs a Task with the specified description, initially unmarked.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.priority = "Low";
    }

    public String getDescription() {
        return description;
    }

    /**
     * Handles the marking of a task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Handles the unmarking of a task.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns the status icon of the current task.
     *
     * @return The status icon of the task ("X" for done, " " for not done).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void setPriority(String priority) {
        if (!priority.equalsIgnoreCase("High") &&
                !priority.equalsIgnoreCase("Medium") &&
                !priority.equalsIgnoreCase("Low")) {
            throw new IllegalArgumentException("Priority must be 'High', 'Medium', or 'Low'");
        }
        this.priority = priority.substring(0, 1).toUpperCase() + priority.substring(1).toLowerCase();
    }

    public String getPriority() {
        return priority;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description + " (Priority: " + priority + ")";
    }

    /**
     * Abstract method to save task data to the file.
     *
     * @return String representation of the task for file storage.
     */
    public abstract String saveToFile();
}
