package com.raulrh.tiendatv.gui;

import com.raulrh.tiendatv.base.Television;
import com.raulrh.tiendatv.util.Util;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;

class TelevisionInfoWindow extends JFrame {
    public TelevisionInfoWindow(Television television) {
        setTitle("Detalles del Televisor");
        setSize(300, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Información del Televisor");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        addFields(television, television.getClass(), infoPanel);

        JScrollPane scrollPane = new JScrollPane(infoPanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);
    }

    private void addFields(Television television, Class<?> clazz, JPanel infoPanel) {
        showFields(television, clazz.getSuperclass(), infoPanel);
        showFields(television, clazz, infoPanel);
    }

    private void showFields(Television television, Class<?> clazz, JPanel infoPanel) {
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);

            try {
                Object value = field.get(television);

                // Panel para cada campo con margen y estilo de fuente
                JPanel fieldPanel = new JPanel(new BorderLayout());
                fieldPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

                JLabel fieldLabel = new JLabel(Util.transformString(field.getName()) + ": ");
                fieldLabel.setFont(new Font("Arial", Font.BOLD, 12));
                fieldLabel.setForeground(new Color(100, 100, 100));

                JLabel valueLabel = new JLabel(value != null ? value.toString() : "N/A");
                valueLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                valueLabel.setForeground(new Color(50, 50, 50));

                fieldPanel.add(fieldLabel, BorderLayout.WEST);
                fieldPanel.add(valueLabel, BorderLayout.CENTER);
                infoPanel.add(fieldPanel);

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}