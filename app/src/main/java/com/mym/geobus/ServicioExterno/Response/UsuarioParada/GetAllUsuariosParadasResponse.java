package com.mym.geobus.ServicioExterno.Response.UsuarioParada;

import com.mym.geobus.Entidades.UsuarioParada;

import java.util.List;

public class GetAllUsuariosParadasResponse {
    private String respuesta;
    private List<UsuarioParada> usuariosParadas;

    public GetAllUsuariosParadasResponse(String respuesta, List<UsuarioParada> usuariosParadas) {
        this.respuesta = respuesta;
        this.usuariosParadas = usuariosParadas;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public List<UsuarioParada> getUsuariosParadas() {
        return usuariosParadas;
    }

    public void setUsuariosParadas(List<UsuarioParada> usuariosParadas) {
        this.usuariosParadas = usuariosParadas;
    }
}
