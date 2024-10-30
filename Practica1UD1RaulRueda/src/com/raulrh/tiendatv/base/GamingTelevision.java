package com.raulrh.tiendatv.base;

import java.sql.Date;
import java.time.LocalDate;

public class GamingTelevision extends Television {
    private int inputLag; // Measured in milliseconds
    private boolean supportsGsync; // Variable Refresh Rate

    public GamingTelevision(String brand, String model, int screenSize, double price, int refreshRate, LocalDate launchDate,
                            String screenType, int inputLag, boolean supportsGsync) {
        super(brand, model, screenSize, price, refreshRate, launchDate, screenType);
        this.inputLag = inputLag;
        this.supportsGsync = supportsGsync;
    }

    public int getInputLag() {
        return inputLag;
    }

    public void setInputLag(int inputLag) {
        this.inputLag = inputLag;
    }

    public boolean isSupportsGsync() {
        return supportsGsync;
    }

    public void setSupportsGsync(boolean supportsGsync) {
        this.supportsGsync = supportsGsync;
    }
}
