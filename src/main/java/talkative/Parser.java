package talkative;

/**
 * Represents the commands that are used within the Talkative Bot to separate tasks for processing.
 */
public class Parser {
    public static String getCommandWord(String input) {
        assert input != null : "Input should not be null";
        assert !input.trim().isEmpty() : "Input should not be empty";
        return input.split(" ")[0];
    }
}
