package com.mym.geobus.Entidades;

public class UsuarioParada {
    private int idUsuario;
    private int idParada;
    private String fecha;
    private String hora;

    public UsuarioParada(int idUsuario, int idParada, String fecha, String hora) {
        this.idUsuario = idUsuario;
        this.idParada = idParada;
        this.fecha = fecha;
        this.hora = hora;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdParada() {
        return idParada;
    }

    public void setIdParada(int idParada) {
        this.idParada = idParada;
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
}
