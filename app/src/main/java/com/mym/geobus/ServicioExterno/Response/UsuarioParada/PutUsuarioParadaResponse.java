package com.mym.geobus.ServicioExterno.Response.UsuarioParada;

import com.mym.geobus.Entidades.UsuarioParada;

public class PutUsuarioParadaResponse {
    private String respuesta;
    private UsuarioParada usuarioParada;

    public PutUsuarioParadaResponse(String respuesta, UsuarioParada usuarioParada) {
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
