package com.raulrh.tiendatv.gui;

import com.raulrh.tiendatv.base.Television;
import com.raulrh.tiendatv.util.Util;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TelevisionInfoWindow extends JFrame {
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
                fieldLabel.setFont(new Font("Arial", Font.BOLD, 11));

                JLabel valueLabel = new JLabel(value != null ? value.toString() : "N/A");
                valueLabel.setFont(new Font("Arial", Font.PLAIN, 11));

                fieldPanel.add(fieldLabel, BorderLayout.WEST);
                fieldPanel.add(valueLabel, BorderLayout.CENTER);
                infoPanel.add(fieldPanel);

            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}