package com.raulrh.tiendatv.base;

/**
 * The type Curved television.
 */
public class CurvedTelevision extends Television {
    private int gradosCurvatura;

    /**
     * Instantiates a new Curved television.
     *
     * @param values the values
     */
    public CurvedTelevision(Object[] values) {
        super(values);
        this.gradosCurvatura = (Integer) values[7];
    }

    /**
     * Instantiates a new Curved television.
     */
    public CurvedTelevision() {

    }

    /**
     * Gets grados curvatura.
     *
     * @return the grados curvatura
     */
    public int getGradosCurvatura() {
        return gradosCurvatura;
    }

    /**
     * Sets grados curvatura.
     *
     * @param gradosCurvatura the grados curvatura
     */
    public void setGradosCurvatura(int gradosCurvatura) {
        this.gradosCurvatura = gradosCurvatura;
    }

    @Override
    public String toString() {
        return "Televisión curva " + super.toString();
    }
}
