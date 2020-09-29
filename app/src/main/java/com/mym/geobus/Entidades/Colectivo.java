package com.mym.geobus.Entidades;

public class Colectivo {
    private int idColectivo;
    private float velocidad;
    private double latitudActual;
    private double longitudActual;
    private int idRecorrido;
    private int idLineaColectivo;

    public Colectivo(int idColectivo, float velocidad, double latitudActual, double longitudActual, int idRecorrido, int idLineaColectivo) {
        this.idColectivo = idColectivo;
        this.velocidad = velocidad;
        this.latitudActual = latitudActual;
        this.longitudActual = longitudActual;
        this.idRecorrido = idRecorrido;
        this.idLineaColectivo = idLineaColectivo;
    }

    public int getIdColectivo() {
        return idColectivo;
    }

    public void setIdColectivo(int idColectivo) {
        this.idColectivo = idColectivo;
    }

    public float getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(float velocidad) {
        this.velocidad = velocidad;
    }

    public double getLatitudActual() {
        return latitudActual;
    }

    public void setLatitudActual(double latitudActual) {
        this.latitudActual = latitudActual;
    }

    public double getLongitudActual() {
        return longitudActual;
    }

    public void setLongitudActual(double longitudActual) {
        this.longitudActual = longitudActual;
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
