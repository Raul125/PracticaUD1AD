package com.raulrh.tiendatv.gui;

import com.github.lgooddatepicker.components.DatePicker;
import com.raulrh.tiendatv.base.CurvedTelevision;
import com.raulrh.tiendatv.base.GamingTelevision;
import com.raulrh.tiendatv.base.SmartTelevision;
import com.raulrh.tiendatv.base.Television;
import com.raulrh.tiendatv.util.Util;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class Window {
    public JPanel mainPanel;
    public JPanel topPanel;
    public JPanel fieldsPanel;
    public JRadioButton curvedTVRadioButton;
    public JRadioButton gamingTVRadioButton;
    public JRadioButton smartTVRadioButton;
    private JPanel bottomPanel;
    private JButton addButton;
    private JList tvJlist;
    private JButton importarButton;
    private JButton exportarButton;
    public ButtonGroup televisionType;
    public JFrame frame;

    public Class<?> selectedClass = CurvedTelevision.class;
    private final ReflectionFields reflectionFields;
    private final TelevisionModel televisionModel;
    public final DefaultListModel<Television> defaultListModel;

    public Window() {
        frame = new JFrame("Televisiones");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(mainPanel);

        Border border = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        mainPanel.setBorder(border);

        reflectionFields = new ReflectionFields(this);
        fieldsPanel.setLayout(new GridLayout(0, 2, 10, 10));
        reflectionFields.createFields();

        televisionModel = new TelevisionModel(this);
        defaultListModel = new DefaultListModel<>();
        tvJlist.setModel(defaultListModel);

        curvedTVRadioButton.setSelected(true);
        setupRadioButtons();

        addButton.addActionListener(e -> submitForm());

        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void submitForm() {
        try {
            Object[] values = new Object[reflectionFields.fieldList.size()];
            for (JComponent field : reflectionFields.fieldList) {
                Object value = switch (field) {
                    case JTextField jTextField -> jTextField.getText();
                    case DatePicker datePicker -> datePicker.getDate();
                    case JCheckBox jCheckBox -> jCheckBox.isSelected();
                    case JSpinner jSpinner -> jSpinner.getValue();
                    case JComboBox<?> jComboBox -> jComboBox.getSelectedItem();
                    default -> throw new IllegalStateException("Unexpected value: " + field);
                };

                values[reflectionFields.fieldList.indexOf(field)] = value;
            }

            reflectionFields.clearFields();
            Object[] finalValues = {values};
            Television television = (Television) selectedClass.getConstructors()[0].newInstance(finalValues);
            televisionModel.addTelevision(television);
        } catch (Exception e) {
            Util.mensajeError(e.getMessage());
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

            reflectionFields.createFields();
        };

        curvedTVRadioButton.addActionListener(radioButtonListener);
        gamingTVRadioButton.addActionListener(radioButtonListener);
        smartTVRadioButton.addActionListener(radioButtonListener);
    }
}
