package com.mym.geobus.Entidades;

public class Transmision {
    private int idTransmision;
    private String fecha;
    private String horaComienzo;
    private String horaFin;
    private int puntajeObtenido;
    private int idUsuario;
    private int idColectivo;

    public Transmision(int idTransmision, String fecha, String horaComienzo, String horaFin, int puntajeObtenido, int idUsuario, int idColectivo) {
        this.idTransmision = idTransmision;
        this.fecha = fecha;
        this.horaComienzo = horaComienzo;
        this.horaFin = horaFin;
        this.puntajeObtenido = puntajeObtenido;
        this.idUsuario = idUsuario;
        this.idColectivo = idColectivo;
    }

    public int getIdTransmision() {
        return idTransmision;
    }

    public void setIdTransmision(int idTransmision) {
        this.idTransmision = idTransmision;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHoraComienzo() {
        return horaComienzo;
    }

    public void setHoraComienzo(String horaComienzo) {
        this.horaComienzo = horaComienzo;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public int getPuntajeObtenido() {
        return puntajeObtenido;
    }

    public void setPuntajeObtenido(int puntajeObtenido) {
        this.puntajeObtenido = puntajeObtenido;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdColectivo() {
        return idColectivo;
    }

    public void setIdColectivo(int idColectivo) {
        this.idColectivo = idColectivo;
    }
}
