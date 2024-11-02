package com.raulrh.tiendatv.gui;

import com.raulrh.tiendatv.base.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

/**
 * The type Window.
 */
public class Window {
    public JPanel mainPanel;
    public JPanel topPanel;
    public JPanel fieldsPanel;
    public JPanel bottomPanel;

    public JRadioButton curvedTVRadioButton;
    public JRadioButton gamingTVRadioButton;
    public JRadioButton smartTVRadioButton;
    public ButtonGroup televisionType;

    public JButton addButton;
    public JButton importarButton;
    public JButton exportarButton;
    public JButton toggleMode;

    public JLabel titleLabel;
    public JButton eliminarButton;
    public JButton editarButton;

    public JList<Television> tvJlist;

    public JFrame frame;

    public final DefaultListModel<Television> defaultListModel;

    /**
     * Instantiates a new Window.
     */
    public Window() {
        this.frame = initializeFrame();
        this.defaultListModel = new DefaultListModel<>();

        setupUI();
    }

    private JFrame initializeFrame() {
        JFrame frame = new JFrame("Televisiones");
        try {
            frame.setIconImage(ImageIO.read(Objects.requireNonNull(getClass().getResource("/television.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        return frame;
    }

    private void setupUI() {
        titleLabel.putClientProperty("FlatLaf.styleClass", "h1");

        frame.setContentPane(mainPanel);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        fieldsPanel.setLayout(new GridLayout(0, 2, 10, 10));

        tvJlist.setModel(defaultListModel);
        curvedTVRadioButton.setSelected(true);
    }
}
