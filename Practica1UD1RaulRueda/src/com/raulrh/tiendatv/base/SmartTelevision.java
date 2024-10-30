package com.raulrh.tiendatv.base;

import java.sql.Date;
import java.time.LocalDate;

public class SmartTelevision extends Television {
    private String operatingSystem;
    private boolean internetConnection;

    public SmartTelevision(String brand, String model, int screenSize, double price, int refreshRate, LocalDate launchDate,
                           String screenType, String operatingSystem, boolean internetConnection) {
        super(brand, model, screenSize, price, refreshRate, launchDate, screenType);
        this.operatingSystem = operatingSystem;
        this.internetConnection = internetConnection;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public boolean isInternetConnection() {
        return internetConnection;
    }

    public void setInternetConnection(boolean internetConnection) {
        this.internetConnection = internetConnection;
    }
}
