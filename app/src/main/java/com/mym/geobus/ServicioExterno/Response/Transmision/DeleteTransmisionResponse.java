package com.mym.geobus.ServicioExterno.Response.Transmision;

import com.mym.geobus.Entidades.Transmision;

public class DeleteTransmisionResponse {
    private String respuesta;
    private Transmision transmision;

    public DeleteTransmisionResponse(String respuesta, Transmision transmision) {
        this.respuesta = respuesta;
        this.transmision = transmision;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public Transmision getTransmision() {
        return transmision;
    }

    public void setTransmision(Transmision transmision) {
        this.transmision = transmision;
    }
}
