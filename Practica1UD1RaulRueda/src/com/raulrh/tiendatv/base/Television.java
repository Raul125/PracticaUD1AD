package com.raulrh.tiendatv.base;

import com.raulrh.tiendatv.base.enums.TipoPantalla;
import com.raulrh.tiendatv.util.LocalDateAdapter;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDate;

/**
 * The Television class represents a television with various attributes such as
 * brand, model, screen size, price, refresh rate, launch date, and screen type.
 * It provides constructors, getters, and setters for managing the television's
 * properties. This class is also suitable for XML serialization using JAXB.
 */
public class Television {

    /** The brand of the television. */
    protected String marca;

    /** The model of the television. */
    protected String modelo;

    /** The screen size of the television in inches. */
    protected int medidasPantalla;

    /** The price of the television. */
    protected double precio;

    /** The refresh rate of the television in Hz. */
    protected int tasaRefresco;

    /** The launch date of the television. */
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    protected LocalDate fechaLanzamiento;

    /** The screen type of the television. */
    protected TipoPantalla tipoPantalla;

    /**
     * Constructs a Television object with the specified values.
     *
     * @param values an array of values to initialize the Television object.
     *               The array should contain:
     *               [0] String marca,
     *               [1] String modelo,
     *               [2] Integer medidasPantalla,
     *               [3] Double precio,
     *               [4] Integer tasaRefresco,
     *               [5] LocalDate fechaLanzamiento,
     *               [6] TipoPantalla tipoPantalla
     */
    public Television(Object[] values) {
        this.marca = (String) values[0];
        this.modelo = (String) values[1];
        this.medidasPantalla = (Integer) values[2];
        this.precio = (Double) values[3];
        this.tasaRefresco = (Integer) values[4];
        this.fechaLanzamiento =  (LocalDate) values[5];
        this.tipoPantalla = (TipoPantalla) values[6];
    }

    /** Default constructor required for JAXB. */
    public Television() {
    }

    /**
     * Gets the brand of the television.
     *
     * @return the brand of the television
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Sets the brand of the television.
     *
     * @param marca the brand to set
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Gets the model of the television.
     *
     * @return the model of the television
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Sets the model of the television.
     *
     * @param modelo the model to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * Gets the screen size of the television.
     *
     * @return the screen size in inches
     */
    public int getMedidasPantalla() {
        return medidasPantalla;
    }

    /**
     * Sets the screen size of the television.
     *
     * @param medidasPantalla the screen size to set in inches
     */
    public void setMedidasPantalla(int medidasPantalla) {
        this.medidasPantalla = medidasPantalla;
    }

    /**
     * Gets the price of the television.
     *
     * @return the price of the television
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Sets the price of the television.
     *
     * @param precio the price to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Gets the refresh rate of the television.
     *
     * @return the refresh rate in Hz
     */
    public int getTasaRefresco() {
        return tasaRefresco;
    }

    /**
     * Sets the refresh rate of the television.
     *
     * @param tasaRefresco the refresh rate to set in Hz
     */
    public void setTasaRefresco(int tasaRefresco) {
        this.tasaRefresco = tasaRefresco;
    }

    /**
     * Gets the launch date of the television.
     *
     * @return the launch date
     */
    @XmlTransient
    public LocalDate getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    /**
     * Sets the launch date of the television.
     *
     * @param fechaLanzamiento the launch date to set
     */
    public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    /**
     * Gets the screen type of the television.
     *
     * @return the screen type
     */
    public TipoPantalla getScreenType() {
        return tipoPantalla;
    }

    /**
     * Sets the screen type of the television.
     *
     * @param tipoPantalla the screen type to set
     */
    public void setScreenType(TipoPantalla tipoPantalla) {
        this.tipoPantalla = tipoPantalla;
    }

    /**
     * Returns a string representation of the Television object.
     *
     * @return a string containing the brand and model of the television
     */
    @Override
    public String toString() {
        return " - Marca: " + marca + ", Modelo: " + modelo;
    }
}