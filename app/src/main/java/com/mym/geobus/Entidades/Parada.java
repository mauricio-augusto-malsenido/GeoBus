package com.mym.geobus.Entidades;

public class Parada {
    private int idParada;
    private double latitud;
    private double longitud;
    private int idRecorrido;
    private int idLineaColectivo;

    public Parada(int idParada, double latitud, double longitud, int idRecorrido, int idLineaColectivo) {
        this.idParada = idParada;
        this.latitud = latitud;
        this.longitud = longitud;
        this.idRecorrido = idRecorrido;
        this.idLineaColectivo = idLineaColectivo;
    }

    public int getIdParada() {
        return idParada;
    }

    public void setIdParada(int idParada) {
        this.idParada = idParada;
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

    public int getIdLineaColectivo() {
        return idLineaColectivo;
    }

    public void setIdLineaColectivo(int idLineaColectivo) {
        this.idLineaColectivo = idLineaColectivo;
    }
}
