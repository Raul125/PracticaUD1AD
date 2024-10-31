package com.raulrh.tiendatv.base;

public class GamingTelevision extends Television {
    private double inputLag; // Measured in milliseconds
    private boolean supportsGsync; // Variable Refresh Rate

    public GamingTelevision(Object[] values) {
        super(values);
        this.inputLag = (Double) values[7];
        this.supportsGsync = (boolean) values[8];
    }

    public GamingTelevision() {

    }

    public double getInputLag() {
        return inputLag;
    }

    public void setInputLag(double inputLag) {
        this.inputLag = inputLag;
    }

    public boolean isSupportsGsync() {
        return supportsGsync;
    }

    public void setSupportsGsync(boolean supportsGsync) {
        this.supportsGsync = supportsGsync;
    }

    @Override
    public String toString() {
        return "Televisión gaming " + super.toString();
    }
}
