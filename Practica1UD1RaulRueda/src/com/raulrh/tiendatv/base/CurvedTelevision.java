package com.raulrh.tiendatv.base;

/**
 * The CurvedTelevision class represents a curved television that extends the
 * Television class with an additional attribute for curvature.
 */
public class CurvedTelevision extends Television {

    /** The degree of curvature of the curved television. */
    private int gradosCurvatura;

    /**
     * Constructs a CurvedTelevision object with the specified values.
     *
     * @param values an array of values to initialize the CurvedTelevision object.
     *               The array should contain:
     *               [0] String marca,
     *               [1] String modelo,
     *               [2] Integer medidasPantalla,
     *               [3] Double precio,
     *               [4] Integer tasaRefresco,
     *               [5] LocalDate fechaLanzamiento,
     *               [6] TipoPantalla tipoPantalla,
     *               [7] Integer gradosCurvatura
     */
    public CurvedTelevision(Object[] values) {
        super(values);
        this.gradosCurvatura = (Integer) values[7];
    }

    /** Default constructor required for JAXB. */
    public CurvedTelevision() {

    }

    /**
     * Gets the degree of curvature of the curved television.
     *
     * @return the degree of curvature
     */
    public int getGradosCurvatura() {
        return gradosCurvatura;
    }

    /**
     * Sets the degree of curvature of the curved television.
     *
     * @param gradosCurvatura the degree of curvature to set
     */
    public void setGradosCurvatura(int gradosCurvatura) {
        this.gradosCurvatura = gradosCurvatura;
    }

    /**
     * Returns a string representation of the CurvedTelevision object.
     *
     * @return a string containing information about the curved television
     */
    @Override
    public String toString() {
        return "Televisión curva " + super.toString();
    }
}