package com.mym.geobus.ServicioExterno.Response.UbicacionAnteriorUsuario;

import com.mym.geobus.Entidades.UbicacionAnteriorUsuario;

import java.util.List;

public class GetAllUbicacionesAnterioresUsuariosResponse {
    private String respuesta;
    private List<UbicacionAnteriorUsuario> ubicacionesAnterioresUsuarios;

    public GetAllUbicacionesAnterioresUsuariosResponse(String respuesta, List<UbicacionAnteriorUsuario> ubicacionesAnterioresUsuarios) {
        this.respuesta = respuesta;
        this.ubicacionesAnterioresUsuarios = ubicacionesAnterioresUsuarios;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public List<UbicacionAnteriorUsuario> getUbicacionesAnterioresUsuarios() {
        return ubicacionesAnterioresUsuarios;
    }

    public void setUbicacionesAnterioresUsuarios(List<UbicacionAnteriorUsuario> ubicacionesAnterioresUsuarios) {
        this.ubicacionesAnterioresUsuarios = ubicacionesAnterioresUsuarios;
    }
}
