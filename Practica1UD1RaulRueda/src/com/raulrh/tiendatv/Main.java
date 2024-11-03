package com.raulrh.tiendatv;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.raulrh.tiendatv.gui.TelevisionController;
import com.raulrh.tiendatv.gui.TelevisionModel;
import com.raulrh.tiendatv.gui.Window;
import com.raulrh.tiendatv.util.Preferences;

/**
 * The main class to start the application.
 */
public class Main {
    /**
     * The main method to start the application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Preferences preferences = Preferences.getInstance();
        if (preferences.isDarkMode()) {
            FlatDarculaLaf.setup();
        } else {
            FlatIntelliJLaf.setup();
        }

        Window view = new Window();
        TelevisionModel model = new TelevisionModel(view);
        TelevisionController controller = new TelevisionController(view, model);
    }
}