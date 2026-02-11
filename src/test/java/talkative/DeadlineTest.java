package talkative;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void constructor_validDate_success() throws TalkativeException {
        Deadline d = new Deadline("return book", "2025-10-15");

        assertTrue(d.toString().contains("Oct"));
    }

    @Test
    public void constructor_invalidDate_throwsException() {
        assertThrows(Exception.class, () -> {
            new Deadline("return book", "not-a-date");
        });
    }
}

