package com.raulrh.tiendatv.base;

import java.sql.Date;
import java.time.LocalDate;

public class CurvedTelevision extends Television {
    private int curvatureDegree;

    public CurvedTelevision(String brand, String model, int screenSize, double price, int refreshRate, LocalDate launchDate, String screenType, int curvatureDegree) {
        super(brand, model, screenSize, price, refreshRate, launchDate, screenType);
        this.curvatureDegree = curvatureDegree;
    }

    public CurvedTelevision(Object[] values) {
        super(values);
        this.curvatureDegree = Integer.parseInt((String) values[7]);
    }

    public int getCurvatureDegree() {
        return curvatureDegree;
    }

    public void setCurvatureDegree(int curvatureDegree) {
        this.curvatureDegree = curvatureDegree;
    }
}
