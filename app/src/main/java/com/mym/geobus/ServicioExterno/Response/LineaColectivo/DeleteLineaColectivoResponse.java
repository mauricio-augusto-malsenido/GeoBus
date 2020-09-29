package com.mym.geobus.ServicioExterno.Response.LineaColectivo;

import com.mym.geobus.Entidades.LineaColectivo;

public class DeleteLineaColectivoResponse {
    private String respuesta;
    private LineaColectivo lineaColectivo;

    public DeleteLineaColectivoResponse(String respuesta, LineaColectivo lineaColectivo) {
        this.respuesta = respuesta;
        this.lineaColectivo = lineaColectivo;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public LineaColectivo getLineaColectivo() {
        return lineaColectivo;
    }

    public void setLineaColectivo(LineaColectivo lineaColectivo) {
        this.lineaColectivo = lineaColectivo;
    }
}
