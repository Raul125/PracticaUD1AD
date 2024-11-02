package com.raulrh.tiendatv.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Preferences {
    private static Preferences instance;
    private static final String FILE_PATH = "preferences.json";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private boolean darkMode;

    public Preferences() {

    }

    public boolean isDarkMode() {
        return darkMode;
    }

    public void setDarkMode(boolean darkMode) {
        this.darkMode = darkMode;
    }

    public static Preferences getInstance() {
        if (instance == null) {
            instance = loadPreferences();
        }

        return instance;
    }

    private static Preferences loadPreferences() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new Preferences();
        }

        try (FileReader reader = new FileReader(file)) {
            return GSON.fromJson(reader, Preferences.class);
        } catch (IOException e) {
            return new Preferences();
        }
    }

    public static void savePreferences() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            GSON.toJson(getInstance(), writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
