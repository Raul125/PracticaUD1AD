package com.raulrh.tiendatv;

import com.raulrh.tiendatv.gui.TelevisionController;
import com.raulrh.tiendatv.gui.TelevisionModel;
import com.raulrh.tiendatv.gui.Window;

public class Main {
    public static void main(String[] args) {
        Window vista = new Window();
        TelevisionModel televisionModel = new TelevisionModel();
        TelevisionController televisionController = new TelevisionController(vista, televisionModel);
    }
}