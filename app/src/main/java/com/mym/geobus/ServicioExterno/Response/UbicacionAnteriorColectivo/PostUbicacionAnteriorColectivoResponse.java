package com.mym.geobus.ServicioExterno.Response.UbicacionAnteriorColectivo;

import com.mym.geobus.Entidades.UbicacionAnteriorColectivo;

public class PostUbicacionAnteriorColectivoResponse {
    private String respuesta;
    private UbicacionAnteriorColectivo ubicacionAnteriorColectivo;

    public PostUbicacionAnteriorColectivoResponse(String respuesta, UbicacionAnteriorColectivo ubicacionAnteriorColectivo) {
        this.respuesta = respuesta;
        this.ubicacionAnteriorColectivo = ubicacionAnteriorColectivo;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public UbicacionAnteriorColectivo getUbicacionAnteriorColectivo() {
        return ubicacionAnteriorColectivo;
    }

    public void setUbicacionAnteriorColectivo(UbicacionAnteriorColectivo ubicacionAnteriorColectivo) {
        this.ubicacionAnteriorColectivo = ubicacionAnteriorColectivo;
    }
}
