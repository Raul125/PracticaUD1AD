package com.raulrh.tiendatv.util;

import javax.swing.*;

/**
 * Utility class for common UI-related operations, such as displaying error messages,
 * showing confirmation dialogs, and transforming strings.
 */
public class Util {

    /**
     * Displays an error message in a dialog.
     *
     * @param message The error message to display.
     */
    public static void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Shows a confirmation dialog with "Yes" and "No" options.
     *
     * @param message The message to display in the dialog.
     * @param title   The title of the dialog window.
     * @return An integer representing the user's choice:
     *         0 for "Yes" and 1 for "No".
     */
    public static int showConfirm(String message, String title) {
        Object[] options = { "Si", "No" };
        return JOptionPane.showOptionDialog(null, message, title,
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                options, options[0]);
    }

    /**
     * Transforms a camelCase or PascalCase string to a space-separated,
     * capitalized form.
     *
     * @param input The input string to transform.
     * @return A new string with spaces before each uppercase letter,
     *         with the first letter capitalized.
     */
    public static String transformString(String input) {
        String[] words = input.split("(?=[A-Z])");
        String result = String.join(" ", words);
        return result.substring(0, 1).toUpperCase() + result.substring(1);
    }
}