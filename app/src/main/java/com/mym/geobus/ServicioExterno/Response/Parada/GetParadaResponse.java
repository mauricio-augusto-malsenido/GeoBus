package com.mym.geobus.ServicioExterno.Response.Parada;

import com.mym.geobus.Entidades.Parada;

public class GetParadaResponse {
    private String respuesta;
    private Parada parada;

    public GetParadaResponse(String respuesta, Parada parada) {
        this.respuesta = respuesta;
        this.parada = parada;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public Parada getParada() {
        return parada;
    }

    public void setParada(Parada parada) {
        this.parada = parada;
    }
}
