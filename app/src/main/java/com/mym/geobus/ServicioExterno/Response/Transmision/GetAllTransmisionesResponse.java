package com.mym.geobus.ServicioExterno.Response.Transmision;

import com.mym.geobus.Entidades.Transmision;

import java.util.List;

public class GetAllTransmisionesResponse {
    private String respuesta;
    private List<Transmision> transmisiones;

    public GetAllTransmisionesResponse(String respuesta, List<Transmision> transmisiones) {
        this.respuesta = respuesta;
        this.transmisiones = transmisiones;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public List<Transmision> getTransmisiones() {
        return transmisiones;
    }

    public void setTransmisiones(List<Transmision> transmisiones) {
        this.transmisiones = transmisiones;
    }
}
