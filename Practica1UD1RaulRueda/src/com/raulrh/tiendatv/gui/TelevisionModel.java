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

/**
 * The type Television model.
 */
public class TelevisionModel {
   private final Window window;
   private final ArrayList<Television> televisions;

   /**
    * Instantiates a new Television model.
    *
    * @param window the window
    */
   public TelevisionModel(Window window) {
      this.window = window;
      this.televisions = new ArrayList<>();
   }

   /**
    * Gets televisions.
    *
    * @return the televisions
    */
   public ArrayList<Television> getTelevisions() {
      return televisions;
   }

   /**
    * Add television.
    *
    * @param television the television
    */
   public void addTelevision(Television television) {
      televisions.add(television);
      window.defaultListModel.addElement(television);
   }

   /**
    * Remove television.
    *
    * @param television the television
    */
   public void removeTelevision(Television television) {
      televisions.remove(television);
      window.defaultListModel.removeElement(television);
   }

   /**
    * Create television instance television.
    *
    * @param selectedClass the selected class
    * @param values        the values
    * @return the television
    * @throws ReflectiveOperationException the reflective operation exception
    */
   public Television createTelevisionInstance(Class<?> selectedClass, Object[] values) throws ReflectiveOperationException {
      Object[] constructorArgs = {values};
      return (Television) selectedClass.getConstructor(Object[].class).newInstance(constructorArgs);
   }

   /**
    * Update television instance.
    *
    * @param television the television
    * @param values     the values
    * @throws ReflectiveOperationException the reflective operation exception
    */
   public void updateTelevisionInstance(Television television, Object[] values) throws ReflectiveOperationException {
      List<Field> fields = new ArrayList<>(Arrays.asList(television.getClass().getSuperclass().getDeclaredFields()));
      fields.addAll(Arrays.asList(television.getClass().getDeclaredFields()));
      for (int i = 0; i < fields.size(); i++) {
         fields.get(i).setAccessible(true);
         fields.get(i).set(television, values[i]);
      }
   }

   /**
    * Exists television boolean.
    *
    * @param television the television
    * @return the boolean
    */
   public boolean existsTelevision(Television television) {
      return televisions.stream().anyMatch(tv -> tv.getMarca().equalsIgnoreCase(television.getMarca())
              && tv.getModelo().equalsIgnoreCase(television.getModelo()));
   }

   /**
    * Save to xml.
    */
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

   /**
    * Load from xml.
    */
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