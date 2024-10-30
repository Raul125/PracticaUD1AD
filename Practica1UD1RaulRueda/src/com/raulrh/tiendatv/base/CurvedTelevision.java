package com.raulrh.tiendatv.base;

public class CurvedTelevision extends Television {
    private int curvatureDegree;

    public CurvedTelevision(Object[] values) {
        super(values);
        this.curvatureDegree = (Integer) values[7];
    }

    public int getCurvatureDegree() {
        return curvatureDegree;
    }

    public void setCurvatureDegree(int curvatureDegree) {
        this.curvatureDegree = curvatureDegree;
    }
}
