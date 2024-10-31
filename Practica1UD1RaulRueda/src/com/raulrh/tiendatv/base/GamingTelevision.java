package com.raulrh.tiendatv.base;

public class GamingTelevision extends Television {
    private double inputLag; // Measured in milliseconds
    private boolean soportaGsync; // Variable Refresh Rate

    public GamingTelevision(Object[] values) {
        super(values);
        this.inputLag = (Double) values[7];
        this.soportaGsync = (boolean) values[8];
    }

    public GamingTelevision() {

    }

    public double getInputLag() {
        return inputLag;
    }

    public void setInputLag(double inputLag) {
        this.inputLag = inputLag;
    }

    public boolean isSoportaGsync() {
        return soportaGsync;
    }

    public void setSoportaGsync(boolean soportaGsync) {
        this.soportaGsync = soportaGsync;
    }

    @Override
    public String toString() {
        return "Televisión gaming " + super.toString();
    }
}
