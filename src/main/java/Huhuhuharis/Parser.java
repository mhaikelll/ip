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
            return LocalDateTime.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e1) {
            try {
                return LocalDateTime.parse(str, DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
            } catch (DateTimeParseException e2) {
                throw new DateTimeParseException("Invalid date-time format. Please use yyyy-MM-dd HHmm or MMM dd yyyy hh:mm a.", str, e2.getErrorIndex());
            }
        }
    }
}
