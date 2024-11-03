package com.raulrh.tiendatv.base;

/**
 * The GamingTelevision class represents a gaming television that extends the
 * Television class with additional attributes specific to gaming performance,
 * such as input lag and support for variable refresh rates (G-Sync).
 */
public class GamingTelevision extends Television {

    /** The input lag of the gaming television, measured in milliseconds. */
    private double inputLag;

    /** Indicates if the gaming television supports G-Sync (Variable Refresh Rate). */
    private boolean soportaGsync;

    /**
     * Constructs a GamingTelevision object with the specified values.
     *
     * @param values an array of values to initialize the GamingTelevision object.
     *               The array should contain:
     *               [0] String marca,
     *               [1] String modelo,
     *               [2] Integer medidasPantalla,
     *               [3] Double precio,
     *               [4] Integer tasaRefresco,
     *               [5] LocalDate fechaLanzamiento,
     *               [6] TipoPantalla tipoPantalla,
     *               [7] Double inputLag,
     *               [8] Boolean soportaGsync
     */
    public GamingTelevision(Object[] values) {
        super(values);
        this.inputLag = (Double) values[7];
        this.soportaGsync = (boolean) values[8];
    }

    /** Default constructor required for JAXB. */
    public GamingTelevision() {

    }

    /**
     * Gets the input lag of the gaming television.
     *
     * @return the input lag in milliseconds
     */
    public double getInputLag() {
        return inputLag;
    }

    /**
     * Sets the input lag of the gaming television.
     *
     * @param inputLag the input lag to set, in milliseconds
     */
    public void setInputLag(double inputLag) {
        this.inputLag = inputLag;
    }

    /**
     * Checks if the gaming television supports G-Sync (Variable Refresh Rate).
     *
     * @return true if it supports G-Sync, false otherwise
     */
    public boolean isSoportaGsync() {
        return soportaGsync;
    }

    /**
     * Sets the support for G-Sync of the gaming television.
     *
     * @param soportaGsync true to indicate support for G-Sync, false otherwise
     */
    public void setSoportaGsync(boolean soportaGsync) {
        this.soportaGsync = soportaGsync;
    }

    /**
     * Returns a string representation of the GamingTelevision object.
     *
     * @return a string containing information about the gaming television
     */
    @Override
    public String toString() {
        return "Televisión gaming " + super.toString();
    }
}