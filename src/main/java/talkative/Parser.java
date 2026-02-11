package talkative;

/**
 * Represents the commands that are used within the Talkative Bot to separate tasks for processing.
 */
public class Parser {
    public static String getCommandWord(String input) {
        return input.split(" ")[0];
    }
}
