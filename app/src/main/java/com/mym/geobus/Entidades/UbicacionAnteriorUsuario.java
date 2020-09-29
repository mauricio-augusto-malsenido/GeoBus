package com.mym.geobus.Entidades;

public class UbicacionAnteriorUsuario {
    private int idUbicacionAnteriorUsuario;
    private String fecha;
    private String hora;
    private double latitudAnterior;
    private double longitudAnterior;
    private int idUsuario;

    public UbicacionAnteriorUsuario(int idUbicacionAnteriorUsuario, String fecha, String hora, double latitudAnterior, double longitudAnterior, int idUsuario) {
        this.idUbicacionAnteriorUsuario = idUbicacionAnteriorUsuario;
        this.fecha = fecha;
        this.hora = hora;
        this.latitudAnterior = latitudAnterior;
        this.longitudAnterior = longitudAnterior;
        this.idUsuario = idUsuario;
    }

    public int getIdUbicacionAnteriorUsuario() {
        return idUbicacionAnteriorUsuario;
    }

    public void setIdUbicacionAnteriorUsuario(int idUbicacionAnteriorUsuario) {
        this.idUbicacionAnteriorUsuario = idUbicacionAnteriorUsuario;
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

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
