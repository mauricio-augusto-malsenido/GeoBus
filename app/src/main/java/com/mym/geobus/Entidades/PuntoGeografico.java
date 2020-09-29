package com.mym.geobus.Entidades;

public class PuntoGeografico {
    private int idPuntoGeografico;
    private double latitud;
    private double longitud;
    private int idRecorrido;

    public PuntoGeografico(int idPuntoGeografico, double latitud, double longitud, int idRecorrido) {
        this.idPuntoGeografico = idPuntoGeografico;
        this.latitud = latitud;
        this.longitud = longitud;
        this.idRecorrido = idRecorrido;
    }

    public int getIdPuntoGeografico() {
        return idPuntoGeografico;
    }

    public void setIdPuntoGeografico(int idPuntoGeografico) {
        this.idPuntoGeografico = idPuntoGeografico;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public int getIdRecorrido() {
        return idRecorrido;
    }

    public void setIdRecorrido(int idRecorrido) {
        this.idRecorrido = idRecorrido;
    }
}
