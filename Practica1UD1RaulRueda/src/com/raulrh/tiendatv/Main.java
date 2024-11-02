package com.raulrh.tiendatv;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.raulrh.tiendatv.base.Preferences;
import com.raulrh.tiendatv.gui.Window;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    private static final String FILE_PATH = "preferences.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static Preferences preferences;

    public static void main(String[] args) {
        preferences = loadPreferences();
        if (preferences.isDarkMode()) {
            FlatDarculaLaf.setup();
        } else {
            FlatIntelliJLaf.setup();
        }

        Window vista = new Window();
    }

    public static Preferences loadPreferences() {
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            // Si el archivo no existe, creamos uno con valores por defecto
            Preferences defaultPreferences = new Preferences();
            savePreferences(defaultPreferences);
            return defaultPreferences;
        }

        // Si el archivo existe, lo leemos
        try (FileReader reader = new FileReader(file)) {
            return gson.fromJson(reader, Preferences.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new Preferences(); // En caso de error, devolvemos preferencias por defecto
        }
    }

    public static void savePreferences(Preferences preferences) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(preferences, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}