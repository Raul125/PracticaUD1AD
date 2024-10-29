package com.raulrh.tiendatv.gui;

import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;

public class Window {
    private JPanel panel1;
    private JRadioButton cocheRadioButton;
    private JRadioButton motoRadioButton;
    private JTextField matriculaTxt;
    private JTextField marcaTxt;
    private JTextField modeloTxt;
    private DatePicker dPicker;
    private JLabel plazasKmsLbl;
    private JTextField plazasKmsTxt;
    private JButton nuevoBtn;
    private JButton exportarBtn;
    private JButton importarBtn;
    private JList list1;

    public JFrame frame;

    //elemento que creo yo
    public DefaultListModel<Vehiculo> dlmVehiculo;

    public Window() {
        frame = new JFrame("Tienda Televisores");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        initComponets();
    }

    private void initComponets() {
        dlmVehiculo = new DefaultListModel<Vehiculo>();
        list1.setModel(dlmVehiculo);
    }
}