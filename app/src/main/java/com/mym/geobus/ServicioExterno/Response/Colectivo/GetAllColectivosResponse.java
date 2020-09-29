package com.mym.geobus.ServicioExterno.Response.Colectivo;

import com.mym.geobus.Entidades.Colectivo;

import java.util.List;

public class GetAllColectivosResponse {
    private String respuesta;
    private List<Colectivo> colectivos;

    public GetAllColectivosResponse(String respuesta, List<Colectivo> colectivos) {
        this.respuesta = respuesta;
        this.colectivos = colectivos;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public List<Colectivo> getColectivos() {
        return colectivos;
    }

    public void setColectivos(List<Colectivo> colectivos) {
        this.colectivos = colectivos;
    }
}
