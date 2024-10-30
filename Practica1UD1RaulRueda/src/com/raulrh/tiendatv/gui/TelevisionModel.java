package com.raulrh.tiendatv.gui;

import com.raulrh.tiendatv.base.Television;

import java.util.ArrayList;

public class TelevisionModel {
   private final Window window;
   private final ArrayList<Television> televisions;

   public TelevisionModel(Window window)
   {
      this.window = window;
      this.televisions = new ArrayList<>();
   }

   public void addTelevision(Television television) {
      televisions.add(television);
      window.defaultListModel.addElement(television);
   }
}