package com.raulrh.tiendatv.base;

import java.sql.Date;
import java.time.LocalDate;

public class GamingTelevision extends Television {
    private double inputLag; // Measured in milliseconds
    private boolean supportsGsync; // Variable Refresh Rate

    public GamingTelevision(Object[] values) {
        super(values);
        this.inputLag = (Integer) values[7];
        this.supportsGsync = (boolean) values[8];
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
}
