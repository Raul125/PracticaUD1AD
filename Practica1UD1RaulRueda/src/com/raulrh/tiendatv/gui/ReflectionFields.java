package com.raulrh.tiendatv.gui;

import com.github.lgooddatepicker.components.DatePicker;
import com.raulrh.tiendatv.base.Television;
import com.raulrh.tiendatv.util.Util;

import javax.swing.*;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The ReflectionFields class dynamically generates GUI components based on
 * the fields of a given class, enabling the application to adapt to different
 * Television types and their attributes. It reflects over the Television
 * class and its subclasses to create corresponding input fields.
 */
public class ReflectionFields {
    private final Window window;
    public final List<JComponent> fieldList;

    /**
     * Constructs a ReflectionFields object for a specified application window.
     *
     * @param window the main application window containing the fields panel
     */
    public ReflectionFields(Window window) {
        this.window = window;
        fieldList = new ArrayList<>();
    }

    /**
     * Creates input fields for each attribute of the specified class.
     * Removes all existing fields from the panel, then dynamically adds
     * new fields based on the attributes of the base Television class
     * and the selected subclass.
     *
     * @param selectedClass the class for which input fields are created
     */
    public void createFields(Class<?> selectedClass) {
        window.fieldsPanel.removeAll();
        fieldList.clear();

        List<Field> televisionFields = new ArrayList<>(Arrays.asList(Television.class.getDeclaredFields()));
        televisionFields.addAll(Arrays.asList(selectedClass.getDeclaredFields()));

        for (Field field : televisionFields) {
            JLabel label = new JLabel(Util.transformString(field.getName()));
            JComponent component = createComponentForFieldType(field.getType());

            fieldList.add(component);
            window.fieldsPanel.add(label);
            window.fieldsPanel.add(component);
        }

        window.fieldsPanel.revalidate();
        window.fieldsPanel.repaint();
    }

    /**
     * Creates a GUI component appropriate for the specified field type.
     * Handles different data types such as LocalDate, String, boolean,
     * enums, and numbers to assign corresponding input elements.
     *
     * @param type the data type of the field
     * @return a JComponent suitable for the specified type
     */
    private JComponent createComponentForFieldType(Class<?> type) {
        if (type == LocalDate.class) {
            return new DatePicker();
        } else if (type == String.class) {
            return new JTextField(1);
        } else if (type == boolean.class) {
            return new JCheckBox();
        } else if (type.isEnum()) {
            return new JComboBox<>(type.getEnumConstants());
        } else if (type == double.class) {
            return new JSpinner(new SpinnerNumberModel(0.0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0.1));
        } else {
            return new JSpinner(new SpinnerNumberModel());
        }
    }
}