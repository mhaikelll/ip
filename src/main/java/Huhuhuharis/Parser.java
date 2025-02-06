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
            str = str.replaceAll("am", "AM").replaceAll("pm", "PM");
            System.out.println("Trying to parse date-time: " + str);
            DateTimeFormatter f1 = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
            return LocalDateTime.parse(str, f1);
        } catch (DateTimeParseException e1) {
            try {
                DateTimeFormatter f2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                return LocalDateTime.parse(str, f2);
            } catch (DateTimeParseException e2) {
                System.out.println("Date-time format failed: " + str);
                throw new DateTimeParseException("Invalid date-time format. Please use yyyy-MM-dd HHmm or MMM dd yyyy hh:mm a.", str, e2.getErrorIndex());
            }
        }
    }
}
