package com.raulrh.tiendatv.gui;

import com.github.lgooddatepicker.components.DatePicker;
import com.raulrh.tiendatv.base.CurvedTelevision;
import com.raulrh.tiendatv.base.GamingTelevision;
import com.raulrh.tiendatv.base.SmartTelevision;
import com.raulrh.tiendatv.base.Television;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Window {
    private JPanel mainPanel;
    private JPanel contentPanel;
    private JPanel fieldsPanel;
    private JRadioButton curvedTVRadioButton;
    private JRadioButton gamingTVRadioButton;
    private JRadioButton smartTVRadioButton;
    private JComboBox comboBox1;
    private JFrame frame;

    private Class selectedClass = CurvedTelevision.class;

    public Window() {
        frame = new JFrame("Window");
        frame.setTitle("Television Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(mainPanel);

        curvedTVRadioButton.setSelected(true);
        fieldsPanel.setLayout(new GridLayout(0, 2));

        setupRadioButtons();
        createFields();

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> submitForm());

        fieldsPanel.add(submitButton);

        frame.setSize(650, 350);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private final List<JComponent> fieldList = new ArrayList<>();

    private void createFields() {
        fieldsPanel.removeAll();
        fieldList.clear();
        ArrayList<Field> televisionFields = new ArrayList<>(Arrays.asList(Television.class.getDeclaredFields()));
        televisionFields.addAll(Arrays.asList(selectedClass.getDeclaredFields()));
        for (Field field : televisionFields) {
            JLabel label = new JLabel(transformString(field.getName()));
            Class<?> type = field.getType();
            JComponent component;
            if (type == LocalDate.class) {
                component = new DatePicker();
            } else {
                component = new JTextField(1);
            }

            fieldList.add(component);

            fieldsPanel.add(label);
            fieldsPanel.add(component);
        }
    }

    public static String transformString(String input) {
        String[] words = input.split("(?=[A-Z])");
        String result = String.join(" ", words);
        return result.substring(0, 1).toUpperCase() + result.substring(1);
    }

    private void submitForm() {
        try {
            Object[] values = new Object[fieldList.size()];
            for (JComponent field : fieldList) {
                Object value;
                if (field instanceof JTextField) {
                    value = ((JTextField) field).getText();
                } else {
                    value = ((DatePicker) field).getDate();
                }

                values[fieldList.indexOf(field)] = value;
            }

            Television tv = new Television(values);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setupRadioButtons() {
        ActionListener radioButtonListener = e -> {
            if (e.getSource() == curvedTVRadioButton) {
                selectedClass = CurvedTelevision.class;
            } else if (e.getSource() == gamingTVRadioButton) {
                selectedClass = GamingTelevision.class;
            } else if (e.getSource() == smartTVRadioButton) {
                selectedClass = SmartTelevision.class;
            }

            createFields();
            fieldsPanel.updateUI();
        };

        // Asignar el mismo listener a cada radio button
        curvedTVRadioButton.addActionListener(radioButtonListener);
        gamingTVRadioButton.addActionListener(radioButtonListener);
        smartTVRadioButton.addActionListener(radioButtonListener);
    }
}
