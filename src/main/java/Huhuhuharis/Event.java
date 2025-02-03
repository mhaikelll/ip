package Huhuhuharis;
import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + Parser.dateTimeToStr(from) + " to: " + Parser.dateTimeToStr(to) + ")";
    }

    @Override
    public String saveToFile() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + Parser.dateTimeToStr(from) + " | " + Parser.dateTimeToStr(to);
    }
}
