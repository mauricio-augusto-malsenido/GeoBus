package com.mym.geobus.ServicioExterno.Response.Recorrido;

import com.mym.geobus.Entidades.Recorrido;

public class GetRecorridoResponse {
    private String respuesta;
    private Recorrido recorrido;

    public GetRecorridoResponse(String respuesta, Recorrido recorrido) {
        this.respuesta = respuesta;
        this.recorrido = recorrido;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public Recorrido getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(Recorrido recorrido) {
        this.recorrido = recorrido;
    }
}
