package com.raulrh.tiendatv.base;

public class CurvedTelevision extends Television {
    private int gradosCurvatura;

    public CurvedTelevision(Object[] values) {
        super(values);
        this.gradosCurvatura = (Integer) values[7];
    }

    public CurvedTelevision() {

    }

    public int getGradosCurvatura() {
        return gradosCurvatura;
    }

    public void setGradosCurvatura(int gradosCurvatura) {
        this.gradosCurvatura = gradosCurvatura;
    }

    @Override
    public String toString() {
        return "Televisión curva " + super.toString();
    }
}
