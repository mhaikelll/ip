package Huhuhuharis;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    /**
     * Converts a LocalDateTime object to a formatted String.
     *
     * @param dateTime The LocalDateTime to convert.
     * @return A formatted String representation of the LocalDateTime object.
     */
    public static String dateTimeToStr(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
    }

    /**
     * Converts a String to a LocalDateTime object.
     *
     * @param str The String to convert.
     * @return A LocalDateTime object parsed from the String.
     */
    public static LocalDateTime strToDateTime(String str) throws DateTimeParseException {
        try {
            str = str.replaceAll("am", "AM").replaceAll("pm", "PM");
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

