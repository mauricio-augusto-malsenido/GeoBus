package com.mym.geobus.ServicioExterno.Response.Usuario;

import com.mym.geobus.Entidades.Usuario;

public class DeleteUsuarioResponse {
    private String respuesta;
    private Usuario usuario;

    public DeleteUsuarioResponse(String respuesta, Usuario usuario) {
        this.respuesta = respuesta;
        this.usuario = usuario;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
