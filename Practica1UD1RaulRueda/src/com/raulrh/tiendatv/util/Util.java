package com.raulrh.tiendatv.util;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class Util {
    public static void mensajeError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error" ,JOptionPane.ERROR_MESSAGE);
    }

    public static String transformString(String input) {
        String[] words = input.split("(?=[A-Z])");
        String result = String.join(" ", words);
        return result.substring(0, 1).toUpperCase() + result.substring(1);
    }

    public static JFileChooser crearSelectorFichero(File rutaDefecto, String tipoArchivos, String extension) {
        JFileChooser selectorFichero = new JFileChooser();
        if (rutaDefecto!=null) {
            selectorFichero.setCurrentDirectory(rutaDefecto);
        }

        if (extension!=null) {
            FileNameExtensionFilter filtro = new FileNameExtensionFilter(tipoArchivos, extension);
            selectorFichero.setFileFilter(filtro);
        }

        return selectorFichero;
    }
}