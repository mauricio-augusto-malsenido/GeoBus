package com.mym.geobus.ServicioExterno.Response.Recorrido;

import com.mym.geobus.Entidades.Recorrido;

import java.util.List;

public class GetAllRecorridosResponse {
    private String respuesta;
    private List<Recorrido> recorridos;

    public GetAllRecorridosResponse(String respuesta, List<Recorrido> recorridos) {
        this.respuesta = respuesta;
        this.recorridos = recorridos;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public List<Recorrido> getRecorridos() {
        return recorridos;
    }

    public void setRecorridos(List<Recorrido> recorridos) {
        this.recorridos = recorridos;
    }
}
