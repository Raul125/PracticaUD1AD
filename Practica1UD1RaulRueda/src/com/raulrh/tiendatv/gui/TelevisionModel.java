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
 * Model class for managing a list of televisions. Handles adding, removing,
 * updating, and XML serialization/deserialization of television objects.
 */
public class TelevisionModel {
   private final Window window;
   private final ArrayList<Television> televisions;

   /**
    * Constructs a TelevisionModel with a specified Window instance for GUI updates.
    *
    * @param window the Window instance associated with this model.
    */
   public TelevisionModel(Window window) {
      this.window = window;
      this.televisions = new ArrayList<>();
   }

   /**
    * Returns the list of televisions.
    *
    * @return the ArrayList of Television objects.
    */
   public ArrayList<Television> getTelevisions() {
      return televisions;
   }

   /**
    * Adds a television to the list and updates the GUI.
    *
    * @param television the Television object to add.
    */
   public void addTelevision(Television television) {
      televisions.add(television);
      window.defaultListModel.addElement(television);
   }

   /**
    * Removes a television from the list and updates the GUI.
    *
    * @param television the Television object to remove.
    */
   public void removeTelevision(Television television) {
      televisions.remove(television);
      window.defaultListModel.removeElement(television);
   }

   /**
    * Creates an instance of a Television subclass with specified values.
    *
    * @param selectedClass the class of Television to instantiate.
    * @param values        the values to set in the Television instance.
    * @return a new instance of the specified Television subclass.
    * @throws ReflectiveOperationException if there is an error creating the instance.
    */
   public Television createTelevisionInstance(Class<?> selectedClass, Object[] values) throws ReflectiveOperationException {
      Object[] constructorArgs = {values};
      return (Television) selectedClass.getConstructor(Object[].class).newInstance(constructorArgs);
   }

   /**
    * Updates an existing Television instance with new values.
    *
    * @param television the Television instance to update.
    * @param values     the new values to set in the Television.
    * @throws ReflectiveOperationException if there is an error updating the instance.
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
    * Checks if a television with the same brand and model already exists in the list.
    *
    * @param television the Television to check for.
    * @return {@code true} if a matching Television exists; {@code false} otherwise.
    */
   public boolean existsTelevision(Television television) {
      return televisions.stream().anyMatch(tv -> tv.getMarca().equalsIgnoreCase(television.getMarca())
              && tv.getModelo().equalsIgnoreCase(television.getModelo()));
   }

   /**
    * Saves the list of televisions to an XML file using JAXB.
    * Opens a file chooser to select the file location.
    *
    * @throws RuntimeException if there is an error saving the file.
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
    * Loads a list of televisions from an XML file using JAXB.
    * Opens a file chooser to select the file.
    *
    * @throws RuntimeException if there is an error loading the file.
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

   /**
    * Deserializes a TelevisionList from an XML file.
    *
    * @param file the XML file to deserialize.
    * @return the TelevisionList loaded from the XML file.
    * @throws JAXBException if there is an error during deserialization.
    */
   private TelevisionList deserializeFromXML(File file) throws JAXBException {
      JAXBContext context = JAXBContext.newInstance(TelevisionList.class);
      Unmarshaller unmarshaller = context.createUnmarshaller();
      return (TelevisionList) unmarshaller.unmarshal(file);
   }
}