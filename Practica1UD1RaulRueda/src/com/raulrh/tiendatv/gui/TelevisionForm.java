package com.raulrh.tiendatv.gui;

import com.raulrh.tiendatv.base.SmartTelevision;
import com.raulrh.tiendatv.base.Television;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;

import java.util.*;
import java.util.List;

public class TelevisionForm extends JFrame {
    private final List<JTextField> fieldList = new ArrayList<>();

    public TelevisionForm() {
        setTitle("Television Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 2));

        // Usar reflexión para crear campos
        createFields();

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> submitForm());

        add(submitButton);
        pack();
        setVisible(true);
    }

    private void createFields() {
        // Obtener todos los campos de la clase Television
        ArrayList<Field> televisionFields = new ArrayList<>(Arrays.asList(Television.class.getDeclaredFields()));
        televisionFields.addAll(Arrays.asList(SmartTelevision.class.getDeclaredFields()));
        for (Field field : televisionFields) {
            JLabel label = new JLabel(field.getName());
            JTextField textField = new JTextField(20);
            fieldList.add(textField);

            add(label);
            add(textField);
        }
    }

    private void submitForm() {
        try {
            // Crear un arreglo para los valores de los campos
            /*String[] values = new String[Television.class.getDeclaredFields().length];
            for (JTextField field : fieldList) {
                String text = field.getText();
                values[fieldList.indexOf(field)] = text;
            }

            // Crear el objeto Television usando los valores recogidos
            Television tv = new Television(values);

            // Aquí puedes manejar el objeto Television como desees
            System.out.println("Television created: " + tv.getBrand());*/
            System.out.println("Television created");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}