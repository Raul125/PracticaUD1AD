package com.raulrh.tiendatv;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.raulrh.tiendatv.gui.Window;
import com.raulrh.tiendatv.util.Preferences;

public class Main {
    public static void main(String[] args) {
        Preferences preferences = Preferences.getInstance();
        if (preferences.isDarkMode()) {
            FlatDarculaLaf.setup();
        } else {
            FlatIntelliJLaf.setup();
        }

        Window vista = new Window();
    }
}