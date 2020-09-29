package com.mym.geobus.ServicioExterno.Response.PuntoGeografico;

import com.mym.geobus.Entidades.PuntoGeografico;

import java.util.List;

public class GetAllPuntosGeograficosResponse {
    private String respuesta;
    private List<PuntoGeografico> puntosGeograficos;

    public GetAllPuntosGeograficosResponse(String respuesta, List<PuntoGeografico> puntosGeograficos) {
        this.respuesta = respuesta;
        this.puntosGeograficos = puntosGeograficos;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public List<PuntoGeografico> getPuntosGeograficos() {
        return puntosGeograficos;
    }

    public void setPuntosGeograficos(List<PuntoGeografico> puntosGeograficos) {
        this.puntosGeograficos = puntosGeograficos;
    }
}
