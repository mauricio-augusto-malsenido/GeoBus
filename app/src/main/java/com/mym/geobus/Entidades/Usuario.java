package com.mym.geobus.Entidades;

public class Usuario {
    private int idUsuario;
    private String nombre;
    private int puntajeActual;
    private double latitudActual;
    private double longitudActual;
    private String email;

    public Usuario(int idUsuario, String nombre, String email) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.email = email;
    }

    public Usuario(String nombre, int puntajeActual) {
        this.nombre = nombre;
        this.puntajeActual = puntajeActual;
    }

    public Usuario(int idUsuario, String nombre, int puntajeActual, double latitudActual, double longitudActual, String email) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.puntajeActual = puntajeActual;
        this.latitudActual = latitudActual;
        this.longitudActual = longitudActual;
        this.email = email;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntajeActual() {
        return puntajeActual;
    }

    public void setPuntajeActual(int puntajeActual) {
        this.puntajeActual = puntajeActual;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
