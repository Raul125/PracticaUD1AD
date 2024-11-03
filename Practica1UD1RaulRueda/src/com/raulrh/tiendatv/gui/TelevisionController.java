package com.raulrh.tiendatv.gui;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.github.lgooddatepicker.components.DatePicker;
import com.raulrh.tiendatv.base.CurvedTelevision;
import com.raulrh.tiendatv.base.GamingTelevision;
import com.raulrh.tiendatv.base.SmartTelevision;
import com.raulrh.tiendatv.base.Television;
import com.raulrh.tiendatv.util.Preferences;
import com.raulrh.tiendatv.util.Util;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

/**
 * The controller class for managing user interactions and actions in the
 * television management system. It connects the GUI components (Window)
 * with the data model (TelevisionModel) and handles events like adding,
 * editing, deleting, importing, and exporting television data.
 */
public class TelevisionController {

    private final Window vista;
    private final TelevisionModel modelo;
    private static final Preferences preferences = Preferences.getInstance();
    private final ReflectionFields reflectionFields;
    private Class<?> selectedClass = CurvedTelevision.class;
    private Television televisionToEdit;

    /**
     * Constructs a TelevisionController with the specified window and model.
     *
     * @param vista  the main application window (View)
     * @param modelo the data model for managing televisions
     */
    public TelevisionController(Window vista, TelevisionModel modelo) {
        this.vista = vista;
        this.modelo = modelo;

        reflectionFields = new ReflectionFields(vista);
        reflectionFields.createFields(selectedClass);
        vista.toggleMode.setText(preferences.isDarkMode() ? "Modo Claro" : "Modo Oscuro");

        initListeners();
    }

    /**
     * Initializes all action listeners for various GUI components, setting up
     * the necessary event handling for adding, editing, deleting, importing,
     * exporting, and displaying television data.
     */
    private void initListeners() {
        vista.frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                int result = Util.showConfirm("¿Desea cerrar la aplicación?", "Salir");
                if (result == JOptionPane.OK_OPTION) {
                    Preferences.savePreferences();
                    System.exit(0);
                }
            }
        });

        ActionListener radioButtonListener = (e -> {
            Object source = e.getSource();
            if (source == vista.curvedTVRadioButton) {
                selectedClass = CurvedTelevision.class;
            } else if (source == vista.gamingTVRadioButton) {
                selectedClass = GamingTelevision.class;
            } else if (source == vista.smartTVRadioButton) {
                selectedClass = SmartTelevision.class;
            }

            reflectionFields.createFields(selectedClass);
        });

        vista.curvedTVRadioButton.addActionListener(radioButtonListener);
        vista.gamingTVRadioButton.addActionListener(radioButtonListener);
        vista.smartTVRadioButton.addActionListener(radioButtonListener);

        vista.addButton.addActionListener(e -> addNewTelevision());
        vista.eliminarButton.addActionListener(e -> {
            Television television = vista.tvJlist.getSelectedValue();
            if (television != null) {
                modelo.removeTelevision(television);
            }
        });

        vista.editarButton.addActionListener(e -> {
            if (vista.editarButton.getText().equals("Editar")) {
                televisionToEdit = vista.tvJlist.getSelectedValue();
                if (televisionToEdit != null) {
                    reflectionFields.createFields(televisionToEdit.getClass());
                    loadTelevisionData(televisionToEdit);
                    vista.editarButton.setText("Guardar");
                    changeButtonsState(false);
                }
            } else {
                addNewTelevision();
                vista.editarButton.setText("Editar");
                changeButtonsState(true);
                reflectionFields.createFields(selectedClass);
            }
        });

        vista.importarButton.addActionListener(e -> modelo.loadFromXML());
        vista.exportarButton.addActionListener(e -> modelo.saveToXML());
        vista.toggleMode.addActionListener(e -> toggleDarkMode());

        vista.tvJlist.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = vista.tvJlist.locationToIndex(e.getPoint());
                    if (index != -1) {
                        Television selectedObject = vista.tvJlist.getModel().getElementAt(index);
                        TelevisionInfoWindow infoWindow = new TelevisionInfoWindow(selectedObject);
                        infoWindow.setVisible(true);
                    }
                }
            }
        });
    }

    /**
     * Adds a new television based on the current field values in the GUI.
     * Also handles editing of existing televisions if currently in edit mode.
     */
    private void addNewTelevision() {
        try {
            Object[] values = new Object[reflectionFields.fieldList.size()];
            for (int i = 0; i < reflectionFields.fieldList.size(); i++) {
                JComponent field = reflectionFields.fieldList.get(i);
                values[i] = extractFieldValue(field);
            }

            if (televisionToEdit == null) {
                Television television = modelo.createTelevisionInstance(selectedClass, values);
                if (!validateFields(television)) {
                    return;
                }

                if (modelo.existsTelevision(television)) {
                    Util.showError("Ya existe una televisión con la misma marca y modelo");
                    return;
                }

                modelo.addTelevision(television);
            } else {
                if (!validateFields(televisionToEdit)) {
                    return;
                }

                modelo.updateTelevisionInstance(televisionToEdit, values);
                vista.tvJlist.repaint();
                televisionToEdit = null;
            }

            reflectionFields.createFields(selectedClass);
        } catch (Exception e) {
            Util.showError(e.getMessage());
        }
    }

    /**
     * Loads the data from a specified Television object into the fields on the GUI.
     *
     * @param television the Television object whose data is loaded
     */
    private void loadTelevisionData(Television television) {
        List<Field> fields = new ArrayList<>(Arrays.asList(television.getClass().getSuperclass().getDeclaredFields()));
        fields.addAll(Arrays.asList(television.getClass().getDeclaredFields()));
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(television);
                JComponent fieldComponent = reflectionFields.fieldList.get(fields.indexOf(field));
                setFieldComponentValue(fieldComponent, value);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Extracts the value from a specified field component in the GUI.
     *
     * @param field the JComponent from which to extract the value
     * @return the extracted value
     */
    private Object extractFieldValue(JComponent field) {
        return switch (field) {
            case JTextField jTextField -> jTextField.getText();
            case DatePicker datePicker -> datePicker.getDate();
            case JCheckBox jCheckBox -> jCheckBox.isSelected();
            case JSpinner jSpinner -> jSpinner.getValue();
            case JComboBox<?> jComboBox -> jComboBox.getSelectedItem();
            default -> throw new IllegalStateException("Unexpected value: " + field);
        };
    }

    /**
     * Toggles the dark/light mode for the GUI.
     */
    private void toggleDarkMode() {
        boolean isDarkMode = preferences.isDarkMode();
        if (isDarkMode) {
            FlatIntelliJLaf.setup();
            vista.toggleMode.setText("Modo Oscuro");
        } else {
            FlatDarculaLaf.setup();
            vista.toggleMode.setText("Modo Claro");
        }

        FlatLaf.updateUI();
        preferences.setDarkMode(!isDarkMode);
    }

    /**
     * Changes the enabled state of specific buttons to the specified state.
     *
     * @param state the desired enabled state
     */
    private void changeButtonsState(boolean state) {
        vista.addButton.setEnabled(state);
        vista.eliminarButton.setEnabled(state);
        vista.importarButton.setEnabled(state);
        vista.exportarButton.setEnabled(state);
        Enumeration<AbstractButton> grupoButtons = vista.televisionType.getElements();
        while (grupoButtons.hasMoreElements()) {
            grupoButtons.nextElement().setEnabled(state);
        }
    }

    /**
     * Validates essential fields in the television to ensure none are empty or null.
     *
     * @param television the television object to validate
     * @return true if validation passes; false otherwise
     */
    private boolean validateFields(Television television) {
        if (television.getMarca().isEmpty() || television.getModelo().isEmpty() ||
                television.getMedidasPantalla() == 0 || television.getPrecio() == 0 ||
                television.getTasaRefresco() == 0 || television.getFechaLanzamiento() == null) {
            Util.showError("Todos los campos son obligatorios");
            return false;
        }

        return true;
    }

    /**
     * Sets a value in a GUI component based on its type.
     *
     * @param fieldComponent the GUI component
     * @param value the value to set in the component
     */
    private void setFieldComponentValue(JComponent fieldComponent, Object value) {
        if (fieldComponent instanceof JTextField textField) {
            textField.setText(value != null ? value.toString() : "");
        } else if (fieldComponent instanceof JCheckBox checkBox) {
            checkBox.setSelected(value != null && (boolean) value);
        } else if (fieldComponent instanceof JSpinner spinner) {
            spinner.setValue(value != null ? value : 0);
        } else if (fieldComponent instanceof DatePicker datePicker) {
            datePicker.setDate((LocalDate) value);
        } else if (fieldComponent instanceof JComboBox<?> comboBox) {
            comboBox.setSelectedItem(value);
        }
    }
}