package com.mym.geobus.ServicioExterno.Response.Parada;

import com.mym.geobus.Entidades.Parada;

import java.util.List;

public class GetAllParadasResponse {
    private String respuesta;
    private List<Parada> paradas;

    public GetAllParadasResponse(String respuesta, List<Parada> paradas) {
        this.respuesta = respuesta;
        this.paradas = paradas;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public List<Parada> getParadas() {
        return paradas;
    }

    public void setParadas(List<Parada> paradas) {
        this.paradas = paradas;
    }
}
