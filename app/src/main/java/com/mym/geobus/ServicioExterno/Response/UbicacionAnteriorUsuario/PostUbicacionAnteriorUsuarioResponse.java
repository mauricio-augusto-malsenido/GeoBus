package com.mym.geobus.ServicioExterno.Response.UbicacionAnteriorUsuario;

import com.mym.geobus.Entidades.UbicacionAnteriorUsuario;

public class PostUbicacionAnteriorUsuarioResponse {
    private String respuesta;
    private UbicacionAnteriorUsuario ubicacionAnteriorUsuario;

    public PostUbicacionAnteriorUsuarioResponse(String respuesta, UbicacionAnteriorUsuario ubicacionAnteriorUsuario) {
        this.respuesta = respuesta;
        this.ubicacionAnteriorUsuario = ubicacionAnteriorUsuario;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public UbicacionAnteriorUsuario getUbicacionAnteriorUsuario() {
        return ubicacionAnteriorUsuario;
    }

    public void setUbicacionAnteriorUsuario(UbicacionAnteriorUsuario ubicacionAnteriorUsuario) {
        this.ubicacionAnteriorUsuario = ubicacionAnteriorUsuario;
    }
}
