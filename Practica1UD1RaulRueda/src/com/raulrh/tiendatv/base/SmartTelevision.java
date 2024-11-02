package com.raulrh.tiendatv.base;

import com.raulrh.tiendatv.base.enums.SistemaOperativo;

public class SmartTelevision extends Television {
    private SistemaOperativo sistemaOperativo;
    private boolean conexionInternet;

    public SmartTelevision(Object[] values) {
        super(values);
        this.sistemaOperativo = (SistemaOperativo) values[7];
        this.conexionInternet = (boolean) values[8];
    }

    public SmartTelevision() {

    }

    public SistemaOperativo getOperatingSystem() {
        return sistemaOperativo;
    }

    public void setOperatingSystem(SistemaOperativo sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
    }

    public boolean isConexionInternet() {
        return conexionInternet;
    }

    public void setConexionInternet(boolean conexionInternet) {
        this.conexionInternet = conexionInternet;
    }

    @Override
    public String toString() {
        return "Smart tv " + super.toString();
    }
}
