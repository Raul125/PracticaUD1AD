package com.raulrh.tiendatv.base;

import com.raulrh.tiendatv.base.enums.ScreenType;
import com.raulrh.tiendatv.util.LocalDateAdapter;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDate;

public class Television {
    protected String brand;
    protected String model;
    protected int screenSize;
    protected double price;
    protected int refreshRate;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    protected LocalDate launchDate;

    protected ScreenType screenType;

    public Television(Object[] values) {
        this.brand = (String) values[0];
        this.model = (String) values[1];
        this.screenSize = (Integer) values[2];
        this.price = (Double) values[3];
        this.refreshRate = (Integer) values[4];
        this.launchDate =  (LocalDate) values[5];
        this.screenType = (ScreenType) values[6];
    }

    public Television() {
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(int screenSize) {
        this.screenSize = screenSize;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRefreshRate() {
        return refreshRate;
    }

    public void setRefreshRate(int refreshRate) {
        this.refreshRate = refreshRate;
    }

    @XmlTransient
    public LocalDate getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(LocalDate launchDate) {
        this.launchDate = launchDate;
    }

    public ScreenType getScreenType() {
        return screenType;
    }

    public void setScreenType(ScreenType screenType) {
        this.screenType = screenType;
    }

    @Override
    public String toString() {
        return " - Marca: " + brand + ", Modelo: " + model;
    }
}