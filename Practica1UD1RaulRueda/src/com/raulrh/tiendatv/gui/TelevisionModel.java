package com.raulrh.tiendatv.gui;

import com.raulrh.tiendatv.base.Television;

import java.util.ArrayList;

public class TelevisionModel {
   private final ArrayList<Television> televisions;

   public TelevisionModel() {
      this.televisions = new ArrayList<>();
   }

   public void addTelevision(Television television) {
      televisions.add(television);
   }
}