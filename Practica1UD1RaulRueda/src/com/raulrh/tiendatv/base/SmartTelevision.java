package com.raulrh.tiendatv.base;

import com.raulrh.tiendatv.base.enums.SistemaOperativo;

/**
 * The SmartTelevision class represents a smart television that extends the
 * Television class with additional attributes specific to smart functionality,
 * such as the operating system and internet connectivity.
 */
public class SmartTelevision extends Television {

    /** The operating system of the smart television. */
    private SistemaOperativo sistemaOperativo;

    /** Indicates if the smart television has internet connectivity. */
    private boolean conexionInternet;

    /**
     * Constructs a SmartTelevision object with the specified values.
     *
     * @param values an array of values to initialize the SmartTelevision object.
     *               The array should contain:
     *               [0] String marca,
     *               [1] String modelo,
     *               [2] Integer medidasPantalla,
     *               [3] Double precio,
     *               [4] Integer tasaRefresco,
     *               [5] LocalDate fechaLanzamiento,
     *               [6] TipoPantalla tipoPantalla,
     *               [7] SistemaOperativo sistemaOperativo,
     *               [8] Boolean conexionInternet
     */
    public SmartTelevision(Object[] values) {
        super(values);
        this.sistemaOperativo = (SistemaOperativo) values[7];
        this.conexionInternet = (boolean) values[8];
    }

    /** Default constructor required for JAXB. */
    public SmartTelevision() {
    }

    /**
     * Gets the operating system of the smart television.
     *
     * @return the operating system
     */
    public SistemaOperativo getOperatingSystem() {
        return sistemaOperativo;
    }

    /**
     * Sets the operating system of the smart television.
     *
     * @param sistemaOperativo the operating system to set
     */
    public void setOperatingSystem(SistemaOperativo sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
    }

    /**
     * Checks if the smart television has internet connectivity.
     *
     * @return true if it has internet connectivity, false otherwise
     */
    public boolean isConexionInternet() {
        return conexionInternet;
    }

    /**
     * Sets the internet connectivity of the smart television.
     *
     * @param conexionInternet true to indicate connectivity, false otherwise
     */
    public void setConexionInternet(boolean conexionInternet) {
        this.conexionInternet = conexionInternet;
    }

    /**
     * Returns a string representation of the SmartTelevision object.
     *
     * @return a string containing information about the smart television
     */
    @Override
    public String toString() {
        return "Smart tv " + super.toString();
    }
}