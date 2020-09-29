package com.mym.geobus.Entidades;

public class LineaColectivo {
    private int idLineaColectivo;
    private String nombre;

    public LineaColectivo(int idLineaColectivo, String nombre) {
        this.idLineaColectivo = idLineaColectivo;
        this.nombre = nombre;
    }

    public int getIdLineaColectivo() {
        return idLineaColectivo;
    }

    public void setIdLineaColectivo(int idLineaColectivo) {
        this.idLineaColectivo = idLineaColectivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
