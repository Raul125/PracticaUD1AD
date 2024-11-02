package com.raulrh.tiendatv.base;

import com.raulrh.tiendatv.base.enums.TipoPantalla;
import com.raulrh.tiendatv.util.LocalDateAdapter;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDate;

/**
 * The type Television.
 */
public class Television {
    /**
     * The Marca.
     */
    protected String marca;
    /**
     * The Modelo.
     */
    protected String modelo;
    /**
     * The Medidas pantalla.
     */
    protected int medidasPantalla;
    /**
     * The Precio.
     */
    protected double precio;
    /**
     * The Tasa refresco.
     */
    protected int tasaRefresco;

    /**
     * The Fecha lanzamiento.
     */
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    protected LocalDate fechaLanzamiento;

    /**
     * The Tipo pantalla.
     */
    protected TipoPantalla tipoPantalla;

    /**
     * Instantiates a new Television.
     *
     * @param values the values
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

    /**
     * Instantiates a new Television.
     */
    public Television() {
    }

    /**
     * Gets marca.
     *
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Sets marca.
     *
     * @param marca the marca
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Gets modelo.
     *
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Sets modelo.
     *
     * @param modelo the modelo
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * Gets medidas pantalla.
     *
     * @return the medidas pantalla
     */
    public int getMedidasPantalla() {
        return medidasPantalla;
    }

    /**
     * Sets medidas pantalla.
     *
     * @param medidasPantalla the medidas pantalla
     */
    public void setMedidasPantalla(int medidasPantalla) {
        this.medidasPantalla = medidasPantalla;
    }

    /**
     * Gets precio.
     *
     * @return the precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Sets precio.
     *
     * @param precio the precio
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Gets tasa refresco.
     *
     * @return the tasa refresco
     */
    public int getTasaRefresco() {
        return tasaRefresco;
    }

    /**
     * Sets tasa refresco.
     *
     * @param tasaRefresco the tasa refresco
     */
    public void setTasaRefresco(int tasaRefresco) {
        this.tasaRefresco = tasaRefresco;
    }

    /**
     * Gets fecha lanzamiento.
     *
     * @return the fecha lanzamiento
     */
    @XmlTransient
    public LocalDate getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    /**
     * Sets fecha lanzamiento.
     *
     * @param fechaLanzamiento the fecha lanzamiento
     */
    public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    /**
     * Gets screen type.
     *
     * @return the screen type
     */
    public TipoPantalla getScreenType() {
        return tipoPantalla;
    }

    /**
     * Sets screen type.
     *
     * @param tipoPantalla the tipo pantalla
     */
    public void setScreenType(TipoPantalla tipoPantalla) {
        this.tipoPantalla = tipoPantalla;
    }

    @Override
    public String toString() {
        return " - Marca: " + marca + ", Modelo: " + modelo;
    }
}