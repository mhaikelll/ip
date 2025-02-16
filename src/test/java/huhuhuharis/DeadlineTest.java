package huhuhuharis;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testToString() {
        assertEquals("[D][ ] Return Book (by: Jan 11 2025 11:59 PM)", new Deadline("Return Book", LocalDateTime.of(2025, 1, 11, 23, 59)).toString());
    }
}
