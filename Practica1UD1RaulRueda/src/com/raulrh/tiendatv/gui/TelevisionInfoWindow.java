package com.raulrh.tiendatv.gui;

import com.raulrh.tiendatv.base.Television;
import com.raulrh.tiendatv.util.Util;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A GUI window that displays detailed information about a specified television.
 * This window is intended to show the attributes of a Television object in a
 * scrollable, user-friendly format.
 */
public class TelevisionInfoWindow extends JFrame {

    /**
     * Constructs a TelevisionInfoWindow with the specified television information.
     *
     * @param television the Television object whose details are displayed in this window.
     */
    public TelevisionInfoWindow(Television television) {
        setTitle("Detalles del Televisor");
        setSize(300, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Información de la Televisión");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        addFields(television, infoPanel);

        JScrollPane scrollPane = new JScrollPane(infoPanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);
    }

    /**
     * Populates the infoPanel with fields of the specified Television object.
     * Each field of the Television (and its superclass) is displayed in a label
     * with its corresponding value.
     *
     * @param television the Television object whose fields are displayed.
     * @param infoPanel  the JPanel to which each field and its value are added.
     */
    private void addFields(Television television, JPanel infoPanel) {
        List<Field> fields = new ArrayList<>(Arrays.asList(television.getClass().getSuperclass().getDeclaredFields()));
        fields.addAll(Arrays.asList(television.getClass().getDeclaredFields()));

        for (Field field : fields) {
            field.setAccessible(true);

            try {
                Object value = field.get(television);

                JPanel fieldPanel = new JPanel(new BorderLayout());
                fieldPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

                JLabel fieldLabel = new JLabel(Util.transformString(field.getName()) + ": ");
                JLabel valueLabel = new JLabel(value != null ? value.toString() : "N/A");

                fieldPanel.add(fieldLabel, BorderLayout.WEST);
                fieldPanel.add(valueLabel, BorderLayout.CENTER);
                infoPanel.add(fieldPanel);

            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}