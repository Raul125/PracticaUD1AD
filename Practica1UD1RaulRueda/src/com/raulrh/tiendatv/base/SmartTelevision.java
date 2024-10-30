package com.raulrh.tiendatv.base;

import com.raulrh.tiendatv.base.enums.OperatingSystem;

public class SmartTelevision extends Television {
    private OperatingSystem operatingSystem;
    private boolean internetConnection;

    public SmartTelevision(Object[] values) {
        super(values);
        this.operatingSystem = (OperatingSystem) values[7];
        this.internetConnection = (boolean) values[8];
    }

    public SmartTelevision() {

    }

    public OperatingSystem getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(OperatingSystem operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public boolean isInternetConnection() {
        return internetConnection;
    }

    public void setInternetConnection(boolean internetConnection) {
        this.internetConnection = internetConnection;
    }
}
