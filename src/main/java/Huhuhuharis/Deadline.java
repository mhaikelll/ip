package Huhuhuharis;

import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a Deadline task with description and due time.
     *
     * @param description The description of the event.
     * @param by The due time of the event.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Parser.dateTimeToStr(by) + ")";
    }

    /**
     * Returns a string representation of the deadline task reconstructed to allow for file storage.
     *
     * @return String representation of the deadline task for file storage.
     */
    @Override
    public String saveToFile() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + Parser.dateTimeToStr(by) + " | " + priority;
    }
}
