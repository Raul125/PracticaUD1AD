package com.raulrh.tiendatv.gui;

import com.raulrh.tiendatv.base.Television;
import com.raulrh.tiendatv.base.TelevisionList;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jnafilechooser.api.JnaFileChooser;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TelevisionModel {
   private final Window window;
   private final ArrayList<Television> televisions;

   public TelevisionModel(Window window) {
      this.window = window;
      this.televisions = new ArrayList<>();
   }

   public ArrayList<Television> getTelevisions() {
      return televisions;
   }

   public void addTelevision(Television television) {
      televisions.add(television);
      window.defaultListModel.addElement(television);
   }

   public void removeTelevision(Television television) {
      televisions.remove(television);
      window.defaultListModel.removeElement(television);
   }

   public Television createTelevisionInstance(Class<?> selectedClass, Object[] values) throws ReflectiveOperationException {
      Object[] constructorArgs = {values};
      return (Television) selectedClass.getConstructor(Object[].class).newInstance(constructorArgs);
   }

   public void updateTelevisionInstance(Television television, Object[] values) throws ReflectiveOperationException {
      List<Field> fields = new ArrayList<>(Arrays.asList(television.getClass().getSuperclass().getDeclaredFields()));
      fields.addAll(Arrays.asList(television.getClass().getDeclaredFields()));
      for (int i = 0; i < fields.size(); i++) {
         fields.get(i).setAccessible(true);
         fields.get(i).set(television, values[i]);
      }
   }

   public boolean existsTelevision(Television television) {
      return televisions.stream().anyMatch(tv -> tv.getMarca().equalsIgnoreCase(television.getMarca())
              && tv.getModelo().equalsIgnoreCase(television.getModelo()));
   }

   public void saveToXML() {
      try {
         JnaFileChooser fc = new JnaFileChooser();
         fc.addFilter("Xml", "xml");
         if (fc.showSaveDialog(null)) {
            File file = fc.getSelectedFile();
            TelevisionList televisionList = new TelevisionList(televisions);
            JAXBContext context = JAXBContext.newInstance(TelevisionList.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(televisionList, file);
         }
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }

   public void loadFromXML() {
      try {
         JnaFileChooser fc = new JnaFileChooser();
         fc.addFilter("Xml", "xml");
         if (fc.showOpenDialog(null)) {
            File file = fc.getSelectedFile();
            TelevisionList televisionList = deserializeFromXML(file);
            for (Television television : televisionList.getTelevisions()) {
               addTelevision(television);
            }
         }
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }

   private TelevisionList deserializeFromXML(File file) throws JAXBException {
      JAXBContext context = JAXBContext.newInstance(TelevisionList.class);
      Unmarshaller unmarshaller = context.createUnmarshaller();
      return (TelevisionList) unmarshaller.unmarshal(file);
   }
}