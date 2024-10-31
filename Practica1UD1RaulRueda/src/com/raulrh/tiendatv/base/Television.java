package com.raulrh.tiendatv.base;

import com.raulrh.tiendatv.base.enums.TipoPantalla;
import com.raulrh.tiendatv.util.LocalDateAdapter;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDate;

public class Television {
    protected String marca;
    protected String modelo;
    protected int medidasPantalla;
    protected double precio;
    protected int tasaRefresco;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    protected LocalDate fechaLanzamiento;

    protected TipoPantalla tipoPantalla;

    public Television(Object[] values) {
        this.marca = (String) values[0];
        this.modelo = (String) values[1];
        this.medidasPantalla = (Integer) values[2];
        this.precio = (Double) values[3];
        this.tasaRefresco = (Integer) values[4];
        this.fechaLanzamiento =  (LocalDate) values[5];
        this.tipoPantalla = (TipoPantalla) values[6];
    }

    public Television() {
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getMedidasPantalla() {
        return medidasPantalla;
    }

    public void setMedidasPantalla(int medidasPantalla) {
        this.medidasPantalla = medidasPantalla;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getTasaRefresco() {
        return tasaRefresco;
    }

    public void setTasaRefresco(int tasaRefresco) {
        this.tasaRefresco = tasaRefresco;
    }

    @XmlTransient
    public LocalDate getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public TipoPantalla getScreenType() {
        return tipoPantalla;
    }

    public void setScreenType(TipoPantalla tipoPantalla) {
        this.tipoPantalla = tipoPantalla;
    }

    @Override
    public String toString() {
        return " - Marca: " + marca + ", Modelo: " + modelo;
    }
}