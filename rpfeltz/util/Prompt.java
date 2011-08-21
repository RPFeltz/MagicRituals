package rpfeltz.util;

import java.io.Console;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * Prompt: API for getting input from the console.
 * @author rpfeltz
 */
public class Prompt {
    /**
     * Set containing "y" and "n" (caps insensitive) as allowed input for the get method.
     */
    public static Set<String> YesNo = new HashSet<String>(Arrays.asList("y|n|-ignoreCase".split("\\|")));
    private static final Console console = System.console();
    
    /**
     * Get user input from the console.
     * @return Input from the user.
     */
    public static String get() {
        return console.readLine();
    }
    
    /**
     * Get user input from the console with a question.
     * @param question Question to ask.
     * @return Input from the user.
     */
    public static String get(String question) {
        return console.readLine(question);
    }
    
    /**
     * Get user input from the console with a question and a list of allowed inputs.
     * If the input doesn't match any of the allowed inputs, it will ask again.
     * @param question Question to ask.
     * @param allowed Allowed inputs. Must be lowercase if ignoreCase is used. Seperated by the | character.
     * Flags:
     * <ul>
     * <li>-ignoreCase Make the filter case insensitive.
     * </ul>
     * @return Input from the user.
     */
    public static String get(String question, Set<String> allowed) {
        boolean ignoreCase = allowed.contains("-ignoreCase");
        String input = get(question);
        if (ignoreCase) {
            input.toLowerCase(Locale.ENGLISH);
            allowed.remove("-ignoreCase");
        }
        while (!allowed.contains(input)) {
            input = get("Invalid Input: ".concat(question));
            if (ignoreCase)
                input.toLowerCase(Locale.ENGLISH);
        }
        return input;
    }
}
