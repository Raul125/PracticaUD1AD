package com.raulrh.tiendatv.base;

import com.raulrh.tiendatv.base.enums.SistemaOperativo;

/**
 * The type Smart television.
 */
public class SmartTelevision extends Television {
    private SistemaOperativo sistemaOperativo;
    private boolean conexionInternet;

    /**
     * Instantiates a new Smart television.
     *
     * @param values the values
     */
    public SmartTelevision(Object[] values) {
        super(values);
        this.sistemaOperativo = (SistemaOperativo) values[7];
        this.conexionInternet = (boolean) values[8];
    }

    /**
     * Instantiates a new Smart television.
     */
    public SmartTelevision() {

    }

    /**
     * Gets operating system.
     *
     * @return the operating system
     */
    public SistemaOperativo getOperatingSystem() {
        return sistemaOperativo;
    }

    /**
     * Sets operating system.
     *
     * @param sistemaOperativo the sistema operativo
     */
    public void setOperatingSystem(SistemaOperativo sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
    }

    /**
     * Is conexion internet boolean.
     *
     * @return the boolean
     */
    public boolean isConexionInternet() {
        return conexionInternet;
    }

    /**
     * Sets conexion internet.
     *
     * @param conexionInternet the conexion internet
     */
    public void setConexionInternet(boolean conexionInternet) {
        this.conexionInternet = conexionInternet;
    }

    @Override
    public String toString() {
        return "Smart tv " + super.toString();
    }
}
