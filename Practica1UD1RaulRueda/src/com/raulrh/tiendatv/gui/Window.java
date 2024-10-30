package com.raulrh.tiendatv.gui;

import com.github.lgooddatepicker.components.DatePicker;
import com.raulrh.tiendatv.base.*;
import com.raulrh.tiendatv.util.Util;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;

public class Window {
    public JPanel mainPanel;
    public JPanel topPanel;
    public JPanel fieldsPanel;
    private JPanel bottomPanel;

    public JRadioButton curvedTVRadioButton;
    public JRadioButton gamingTVRadioButton;
    public JRadioButton smartTVRadioButton;

    private JButton addButton;
    private JList<Television> tvJlist;
    private JButton importarButton;
    private JButton exportarButton;
    public ButtonGroup televisionType;
    public JFrame frame;

    public Class<?> selectedClass = CurvedTelevision.class;

    private final ReflectionFields reflectionFields;
    private final TelevisionModel televisionModel;
    public final DefaultListModel<Television> defaultListModel;

    public Window() {
        this.frame = initializeFrame();
        this.reflectionFields = new ReflectionFields(this);
        this.televisionModel = new TelevisionModel(this);
        this.defaultListModel = new DefaultListModel<>();

        setupUI();
        initializeRadioButtonListeners();
        initializeActionListeners();
    }

    private JFrame initializeFrame() {
        JFrame frame = new JFrame("Televisiones");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        return frame;
    }

    private void setupUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        frame.setContentPane(mainPanel);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        fieldsPanel.setLayout(new GridLayout(0, 2, 10, 10));
        reflectionFields.createFields();

        tvJlist.setModel(defaultListModel);
        curvedTVRadioButton.setSelected(true);
    }

    private void initializeRadioButtonListeners() {
        ActionListener radioButtonListener = e -> {
            Object source = e.getSource();
            if (source == curvedTVRadioButton) {
                selectedClass = CurvedTelevision.class;
            } else if (source == gamingTVRadioButton) {
                selectedClass = GamingTelevision.class;
            } else if (source == smartTVRadioButton) {
                selectedClass = SmartTelevision.class;
            }

            reflectionFields.createFields();
        };

        curvedTVRadioButton.addActionListener(radioButtonListener);
        gamingTVRadioButton.addActionListener(radioButtonListener);
        smartTVRadioButton.addActionListener(radioButtonListener);
    }

    private void initializeActionListeners() {
        addButton.addActionListener(e -> submitForm());
        importarButton.addActionListener(e -> loadFromXML("televisiones.xml"));
        exportarButton.addActionListener(e -> saveToXML());
    }

    private void submitForm() {
        try {
            Object[] values = new Object[reflectionFields.fieldList.size()];
            for (int i = 0; i < reflectionFields.fieldList.size(); i++) {
                JComponent field = reflectionFields.fieldList.get(i);
                values[i] = extractFieldValue(field);
            }

            reflectionFields.clearFields();
            Television television = createTelevisionInstance(values);
            televisionModel.addTelevision(television);
        } catch (Exception e) {
            Util.mensajeError(e.getMessage());
        }
    }

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

    private Television createTelevisionInstance(Object[] values) throws ReflectiveOperationException {
        Object[] constructorArgs = {values};
        return (Television) selectedClass.getConstructor(Object[].class).newInstance(constructorArgs);
    }

    public void saveToXML() {
        try {
            JFileChooser selectorFichero = Util.crearSelectorFichero(null,"Archivos XML","xml");
            int result = selectorFichero.showSaveDialog(null);
            if (result==JFileChooser.APPROVE_OPTION) {
                TelevisionList televisionList = new TelevisionList(televisionModel.getTelevisions());
                JAXBContext context = JAXBContext.newInstance(TelevisionList.class);
                Marshaller marshaller = context.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                marshaller.marshal(televisionList, selectorFichero.getSelectedFile());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void loadFromXML(String filePath) {
        try {
            TelevisionList televisionList = deserializeFromXML(filePath);
            for (Television television : televisionList.getTelevisions()) {
                televisionModel.addTelevision(television);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private TelevisionList deserializeFromXML(String filePath) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(TelevisionList.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (TelevisionList) unmarshaller.unmarshal(new File(filePath));
    }
}
