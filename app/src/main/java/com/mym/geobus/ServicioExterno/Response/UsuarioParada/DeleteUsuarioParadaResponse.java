package com.mym.geobus.ServicioExterno.Response.UsuarioParada;

import com.mym.geobus.Entidades.UsuarioParada;

public class DeleteUsuarioParadaResponse {
    private String respuesta;
    private UsuarioParada usuarioParada;

    public DeleteUsuarioParadaResponse(String respuesta, UsuarioParada usuarioParada) {
        this.respuesta = respuesta;
        this.usuarioParada = usuarioParada;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public UsuarioParada getUsuarioParada() {
        return usuarioParada;
    }

    public void setUsuarioParada(UsuarioParada usuarioParada) {
        this.usuarioParada = usuarioParada;
    }
}
