package com.mym.geobus.Autenticacion;

import android.content.Context;
import android.content.SharedPreferences;

import com.mym.geobus.Entidades.Usuario;

public class AdministradorDeSesion {
    private SharedPreferences preferences;

    public AdministradorDeSesion(Context contexto) {
        preferences = contexto.getSharedPreferences("login", Context.MODE_PRIVATE);
    }

    public void registrarCuenta(int idUsuario, String nombre, String email, String proveedor)
    {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("login",true);
        editor.putString("proveedor",proveedor);
        editor.putInt("idUsuario",idUsuario);
        editor.putString("nombre",nombre);
        editor.putString("email",email);
        editor.apply();
    }

    public Usuario obtenerUsuario()
    {
        int idUsuario = preferences.getInt("idUsuario",0);
        String nombre = preferences.getString("nombre","");
        String email = preferences.getString("email","");
        return new Usuario(idUsuario,nombre,email);
    }

    public Boolean verificarUsuarioAutenticado()
    {
        return preferences.getBoolean("login",false);
    }

    public String obtenerProveedor()
    {
        return preferences.getString("proveedor","NADA");
    }

    public void cerrarSesion()
    {
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }
}
