package com.raulrh.tiendatv;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.raulrh.tiendatv.gui.TelevisionController;
import com.raulrh.tiendatv.gui.TelevisionModel;
import com.raulrh.tiendatv.gui.Window;
import com.raulrh.tiendatv.util.Preferences;

/**
 * The type Main.
 */
public class Main {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
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