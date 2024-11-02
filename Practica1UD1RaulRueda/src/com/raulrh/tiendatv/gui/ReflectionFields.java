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

public class ReflectionFields {
    private final Window window;
    public final List<JComponent> fieldList;

    public ReflectionFields(Window window) {
        this.window = window;
        fieldList = new ArrayList<>();
    }

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

    public void clearFields() {
        for (JComponent field : fieldList) {
            if (field instanceof JTextField) {
                ((JTextField) field).setText("");
            } else if (field instanceof JCheckBox) {
                ((JCheckBox) field).setSelected(false);
            } else if (field instanceof JSpinner) {
                ((JSpinner) field).setValue(0);
            } else if (field instanceof DatePicker) {
                ((DatePicker) field).setDate(null);
            }
        }
    }
}
