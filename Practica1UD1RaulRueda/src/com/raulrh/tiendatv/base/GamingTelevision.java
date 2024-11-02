package com.raulrh.tiendatv.base;

/**
 * The type Gaming television.
 */
public class GamingTelevision extends Television {
    private double inputLag; // Measured in milliseconds
    private boolean soportaGsync; // Variable Refresh Rate

    /**
     * Instantiates a new Gaming television.
     *
     * @param values the values
     */
    public GamingTelevision(Object[] values) {
        super(values);
        this.inputLag = (Double) values[7];
        this.soportaGsync = (boolean) values[8];
    }

    /**
     * Instantiates a new Gaming television.
     */
    public GamingTelevision() {

    }

    /**
     * Gets input lag.
     *
     * @return the input lag
     */
    public double getInputLag() {
        return inputLag;
    }

    /**
     * Sets input lag.
     *
     * @param inputLag the input lag
     */
    public void setInputLag(double inputLag) {
        this.inputLag = inputLag;
    }

    /**
     * Is soporta gsync boolean.
     *
     * @return the boolean
     */
    public boolean isSoportaGsync() {
        return soportaGsync;
    }

    /**
     * Sets soporta gsync.
     *
     * @param soportaGsync the soporta gsync
     */
    public void setSoportaGsync(boolean soportaGsync) {
        this.soportaGsync = soportaGsync;
    }

    @Override
    public String toString() {
        return "Televisión gaming " + super.toString();
    }
}
