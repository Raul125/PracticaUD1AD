package com.raulrh.tiendatv.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The type Preferences.
 */
public class Preferences {
    private static Preferences instance;
    private static final String FILE_NAME = "preferences.json";
    private static final String FOLDER_NAME = "TiendaTV";
    private static final String APPDATA_PATH = System.getenv("APPDATA");
    private static final String FILE_PATH = APPDATA_PATH + File.separator + FOLDER_NAME + File.separator + FILE_NAME;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private boolean darkMode;

    /**
     * Instantiates a new Preferences.
     */
    public Preferences() {

    }

    /**
     * Is dark mode boolean.
     *
     * @return the boolean
     */
    public boolean isDarkMode() {
        return darkMode;
    }

    /**
     * Sets dark mode.
     *
     * @param darkMode the dark mode
     */
    public void setDarkMode(boolean darkMode) {
        this.darkMode = darkMode;
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
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

    /**
     * Save preferences.
     */
    public static void savePreferences() {
        File folder = new File(APPDATA_PATH + File.separator + FOLDER_NAME);
        if (!folder.exists() && !folder.mkdir()) {
            throw new RuntimeException("No se pudo crear la carpeta de configuración");
        }

        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            GSON.toJson(getInstance(), writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
