package com.mym.geobus.ServicioExterno.Response.LineaColectivo;

import com.mym.geobus.Entidades.LineaColectivo;

import java.util.List;

public class GetAllLineasColectivoResponse {
    private String respuesta;
    private List<LineaColectivo> lineasColectivo;

    public GetAllLineasColectivoResponse(String respuesta, List<LineaColectivo> lineasColectivo) {
        this.respuesta = respuesta;
        this.lineasColectivo = lineasColectivo;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public List<LineaColectivo> getLineasColectivo() {
        return lineasColectivo;
    }

    public void setLineasColectivo(List<LineaColectivo> lineasColectivo) {
        this.lineasColectivo = lineasColectivo;
    }
}
