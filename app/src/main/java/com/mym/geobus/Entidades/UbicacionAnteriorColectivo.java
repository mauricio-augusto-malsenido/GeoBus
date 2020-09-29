package com.mym.geobus.Entidades;

public class UbicacionAnteriorColectivo {
    private int idUbicacionAnteriorColectivo;
    private String fecha;
    private String hora;
    private double latitudAnterior;
    private double longitudAnterior;
    private int idColectivo;
    private int idRecorrido;
    private int idLineaColectivo;

    public UbicacionAnteriorColectivo(int idUbicacionAnteriorColectivo, String fecha, String hora, double latitudAnterior, double longitudAnterior, int idColectivo, int idRecorrido, int idLineaColectivo) {
        this.idUbicacionAnteriorColectivo = idUbicacionAnteriorColectivo;
        this.fecha = fecha;
        this.hora = hora;
        this.latitudAnterior = latitudAnterior;
        this.longitudAnterior = longitudAnterior;
        this.idColectivo = idColectivo;
        this.idRecorrido = idRecorrido;
        this.idLineaColectivo = idLineaColectivo;
    }

    public int getIdUbicacionAnteriorColectivo() {
        return idUbicacionAnteriorColectivo;
    }

    public void setIdUbicacionAnteriorColectivo(int idUbicacionAnteriorColectivo) {
        this.idUbicacionAnteriorColectivo = idUbicacionAnteriorColectivo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public double getLatitudAnterior() {
        return latitudAnterior;
    }

    public void setLatitudAnterior(double latitudAnterior) {
        this.latitudAnterior = latitudAnterior;
    }

    public double getLongitudAnterior() {
        return longitudAnterior;
    }

    public void setLongitudAnterior(double longitudAnterior) {
        this.longitudAnterior = longitudAnterior;
    }

    public int getIdColectivo() {
        return idColectivo;
    }

    public void setIdColectivo(int idColectivo) {
        this.idColectivo = idColectivo;
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
