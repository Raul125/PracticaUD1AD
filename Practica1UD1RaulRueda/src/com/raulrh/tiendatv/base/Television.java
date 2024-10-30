package com.raulrh.tiendatv.base;

import java.time.LocalDate;

public class Television {
    protected String brand;
    protected String model;
    protected int screenSize;
    protected double price;
    protected int refreshRate;
    protected LocalDate launchDate;
    protected String screenType;

    public Television(String brand, String model, int screenSize, double price, int refreshRate, LocalDate launchDate, String screenType) {
        this.brand = brand;
        this.model = model;
        this.screenSize = screenSize;
        this.price = price;
        this.refreshRate = refreshRate;
        this.launchDate = launchDate;
        this.screenType = screenType;
    }

    public Television(Object[] values) {
        this.brand = (String) values[0];
        this.model = (String) values[1];
        this.screenSize = Integer.parseInt((String) values[2]);
        this.price = Double.parseDouble((String) values[3]);
        this.refreshRate = Integer.parseInt((String) values[4]);
        this.launchDate =  (LocalDate) values[5];
        this.screenType = (String) values[6];
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

    public LocalDate getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(LocalDate launchDate) {
        this.launchDate = launchDate;
    }

    public String getScreenType() {
        return screenType;
    }

    public void setScreenType(String screenType) {
        this.screenType = screenType;
    }
}