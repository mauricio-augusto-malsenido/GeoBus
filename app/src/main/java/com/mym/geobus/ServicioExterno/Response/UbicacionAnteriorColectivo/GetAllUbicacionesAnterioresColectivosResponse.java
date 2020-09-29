package com.mym.geobus.ServicioExterno.Response.UbicacionAnteriorColectivo;

import com.mym.geobus.Entidades.UbicacionAnteriorColectivo;

import java.util.List;

public class GetAllUbicacionesAnterioresColectivosResponse {
    private String respuesta;
    private List<UbicacionAnteriorColectivo> ubicacionesAnterioresColectivos;

    public GetAllUbicacionesAnterioresColectivosResponse(String respuesta, List<UbicacionAnteriorColectivo> ubicacionesAnterioresColectivos) {
        this.respuesta = respuesta;
        this.ubicacionesAnterioresColectivos = ubicacionesAnterioresColectivos;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public List<UbicacionAnteriorColectivo> getUbicacionesAnterioresColectivos() {
        return ubicacionesAnterioresColectivos;
    }

    public void setUbicacionesAnterioresColectivos(List<UbicacionAnteriorColectivo> ubicacionesAnterioresColectivos) {
        this.ubicacionesAnterioresColectivos = ubicacionesAnterioresColectivos;
    }
}
