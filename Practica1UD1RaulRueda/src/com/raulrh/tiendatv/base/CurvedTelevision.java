package com.raulrh.tiendatv.base;

import java.sql.Date;

public class CurvedTelevision extends Television {
    private int curvatureDegree;

    public CurvedTelevision(String brand, String model, int screenSize, double price, int refreshRate, Date launchDate, String screenType, int curvatureDegree) {
        super(brand, model, screenSize, price, refreshRate, launchDate, screenType);
        this.curvatureDegree = curvatureDegree;
    }

    public int getCurvatureDegree() {
        return curvatureDegree;
    }

    public void setCurvatureDegree(int curvatureDegree) {
        this.curvatureDegree = curvatureDegree;
    }
}
