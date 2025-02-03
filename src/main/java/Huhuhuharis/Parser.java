package Huhuhuharis;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public static String dateTimeToStr(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
    }

    public static LocalDateTime strToDateTime(String str) throws DateTimeParseException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return LocalDateTime.parse(str, formatter);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("Invalid date-time. Please use yyyy-MM-dd HHmm.", str, e.getErrorIndex());
        }
    }
}
