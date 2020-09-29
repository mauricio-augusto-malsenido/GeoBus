package com.mym.geobus.Entidades;

public class Recorrido {
    private int idRecorrido;
    private String nombre;
    private String descripcion;
    private int idLineaColectivo;

    public Recorrido(int idRecorrido, String nombre, String descripcion, int idLineaColectivo) {
        this.idRecorrido = idRecorrido;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idLineaColectivo = idLineaColectivo;
    }

    public int getIdRecorrido() {
        return idRecorrido;
    }

    public void setIdRecorrido(int idRecorrido) {
        this.idRecorrido = idRecorrido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdLineaColectivo() {
        return idLineaColectivo;
    }

    public void setIdLineaColectivo(int idLineaColectivo) {
        this.idLineaColectivo = idLineaColectivo;
    }
}
