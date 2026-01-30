package talkative;

public class Parser {
    public static String getCommandWord(String input) {
        return input.split(" ")[0];
    }
}
