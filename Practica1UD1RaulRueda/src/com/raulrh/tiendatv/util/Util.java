package com.raulrh.tiendatv.util;

import javax.swing.*;

public class Util {
    public static void mensajeError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error" ,JOptionPane.ERROR_MESSAGE);
    }

    public static String transformString(String input) {
        String[] words = input.split("(?=[A-Z])");
        String result = String.join(" ", words);
        return result.substring(0, 1).toUpperCase() + result.substring(1);
    }
}