package com.raulrh.tiendatv.gui;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.github.lgooddatepicker.components.DatePicker;
import com.raulrh.tiendatv.base.*;
import com.raulrh.tiendatv.util.Util;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jnafilechooser.api.JnaFileChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
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
        importarButton.addActionListener(e -> loadFromXML());
        exportarButton.addActionListener(e -> saveToXML());

        tvJlist.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {  // Detectar doble clic
                    int index = tvJlist.locationToIndex(e.getPoint());
                    if (index != -1) {
                        Television selectedObject = tvJlist.getModel().getElementAt(index);
                        TelevisionInfoWindow infoWindow = new TelevisionInfoWindow(selectedObject);
                        infoWindow.setVisible(true);
                    }
                }
            }
        });
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
            Util.showError(e.getMessage());
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
            JnaFileChooser fc = new JnaFileChooser();
            fc.addFilter("Xml", "xml");
            if (fc.showSaveDialog(frame)) {
                File file = fc.getSelectedFile();
                TelevisionList televisionList = new TelevisionList(televisionModel.getTelevisions());
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
            if (fc.showOpenDialog(frame)) {
                File file = fc.getSelectedFile();
                TelevisionList televisionList = deserializeFromXML(file);
                for (Television television : televisionList.getTelevisions()) {
                    televisionModel.addTelevision(television);
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
