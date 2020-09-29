package com.mym.geobus.ServicioExterno.Response.PuntoGeografico;

import com.mym.geobus.Entidades.PuntoGeografico;

public class GetPuntoGeograficoResponse {
    private String respuesta;
    private PuntoGeografico puntoGeografico;

    public GetPuntoGeograficoResponse(String respuesta, PuntoGeografico puntoGeografico) {
        this.respuesta = respuesta;
        this.puntoGeografico = puntoGeografico;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public PuntoGeografico getPuntoGeografico() {
        return puntoGeografico;
    }

    public void setPuntoGeografico(PuntoGeografico puntoGeografico) {
        this.puntoGeografico = puntoGeografico;
    }
}
