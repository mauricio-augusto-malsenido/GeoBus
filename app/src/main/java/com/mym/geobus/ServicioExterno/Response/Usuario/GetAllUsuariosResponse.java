package com.mym.geobus.ServicioExterno.Response.Usuario;

import com.mym.geobus.Entidades.Usuario;

import java.util.List;

public class GetAllUsuariosResponse {
    private String respuesta;
    private List<Usuario> usuarios;

    public GetAllUsuariosResponse(String respuesta, List<Usuario> usuarios) {
        this.respuesta = respuesta;
        this.usuarios = usuarios;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
