package com.mym.geobus.ServicioExterno.Response.Colectivo;

import com.mym.geobus.Entidades.Colectivo;

public class PutColectivoResponse {
    private String respuesta;
    private Colectivo colectivo;

    public PutColectivoResponse(String respuesta, Colectivo colectivo) {
        this.respuesta = respuesta;
        this.colectivo = colectivo;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public Colectivo getColectivo() {
        return colectivo;
    }

    public void setColectivo(Colectivo colectivo) {
        this.colectivo = colectivo;
    }
}
