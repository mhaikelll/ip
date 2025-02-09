package Huhuhuharis;

import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event task with description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + Parser.dateTimeToStr(from) + " to: " + Parser.dateTimeToStr(to) + ")";
    }

    /**
     * Returns a string representation of the event task reconstructed to allow for file storage.
     *
     * @return String representation of the event task for file storage.
     */
    @Override
    public String saveToFile() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + Parser.dateTimeToStr(from) + " | " + Parser.dateTimeToStr(to);
    }
}
