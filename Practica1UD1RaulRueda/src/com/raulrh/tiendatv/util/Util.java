package com.raulrh.tiendatv.util;

import javax.swing.*;

/**
 * The type Util.
 */
public class Util {
    /**
     * Show error.
     *
     * @param mensaje the mensaje
     */
    public static void showError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Show confirm int.
     *
     * @param mensaje the mensaje
     * @param titulo  the titulo
     * @return the int
     */
    public static int showConfirm(String mensaje, String titulo) {
        Object[] options = { "Si", "No" };
        return JOptionPane.showOptionDialog(null, mensaje, titulo,
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                options, options[0]);
    }

    /**
     * Transform string string.
     *
     * @param input the input
     * @return the string
     */
    public static String transformString(String input) {
        String[] words = input.split("(?=[A-Z])");
        String result = String.join(" ", words);
        return result.substring(0, 1).toUpperCase() + result.substring(1);
    }
}