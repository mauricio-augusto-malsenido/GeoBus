package com.mym.geobus.BaseDeDatosLocal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import com.mym.geobus.Entidades.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends GeoBusOpenHelper{

    private SQLiteDatabase db; // Variable mediante la cual vamos a hacer referencia a nuestra base de datos

    // Operaciones para la tabla Usuario
    private String insertarUsuario = "INSERT INTO Usuario (idUsuario,nombre,puntajeActual,latitudActual,longitudActual,email) VALUES (?,?,?,?,?,?)";
    private String eliminarUsuario = "DELETE FROM Usuario WHERE idUsuario = ?";
    private String actualizarUsuario = "UPDATE Usuario SET nombre = ?, puntajeActual = ?, latitudActual = ?, longitudActual = ?, email = ? WHERE idUsuario = ?";
    private String obtenerUsuario = "SELECT * FROM Usuario WHERE idUsuario = ?";
    private String obtenerUsuarios = "SELECT * FROM Usuario";

    public UsuarioDAO(@Nullable Context context) {
        super(context);
    }

    public void crearUsuario(Usuario usuario)
    {
        db = this.getWritableDatabase();
        String[] params = new String[] {String.valueOf(usuario.getIdUsuario()),usuario.getNombre(),String.valueOf(usuario.getPuntajeActual()),String.valueOf(usuario.getLatitudActual()),String.valueOf(usuario.getLongitudActual()),usuario.getEmail()};
        db.execSQL(insertarUsuario,params);
        db.close();
    }

    public void eliminarUsuario(int idUsuario)
    {
        db = this.getWritableDatabase();
        String[] params = new String[]{String.valueOf(idUsuario)};
        db.execSQL(eliminarUsuario,params);
        db.close();
    }

    public void modificarUsuario(Usuario usuario)
    {
        db = this.getWritableDatabase();
        String[] params = new String[]{usuario.getNombre(),String.valueOf(usuario.getPuntajeActual()),String.valueOf(usuario.getLatitudActual()),String.valueOf(usuario.getLongitudActual()),usuario.getEmail(),String.valueOf(usuario.getIdUsuario())};
        db.execSQL(actualizarUsuario,params);
        db.close();
    }

    public Usuario obtenerUsuarioPorId(int idU)
    {
        db = this.getReadableDatabase();
        Usuario usuario = null;
        String[] params = new String[]{String.valueOf(idU)};

        Cursor c = db.rawQuery(obtenerUsuario,params);
        if (c.moveToFirst())
        {
            int idUsuario = c.getInt(c.getColumnIndex("idUsuario"));
            String nombre = c.getString(c.getColumnIndex("nombre"));
            int puntajeActual = c.getInt(c.getColumnIndex("puntajeActual"));
            double latitudActual = c.getDouble(c.getColumnIndex("latitudActual"));
            double longitudActual = c.getDouble(c.getColumnIndex("longitudActual"));
            String email = c.getString(c.getColumnIndex("email"));
            usuario = new Usuario(idUsuario,nombre,puntajeActual,latitudActual,longitudActual,email);
        }
        db.close();
        return usuario;
    }

    public List<Usuario> obtenerTodosLosUsuarios()
    {
        db = this.getReadableDatabase();
        List<Usuario> usuarios = new ArrayList<>();

        Cursor c = db.rawQuery(obtenerUsuarios,null);
        if (c.moveToFirst())
        {
            do {
                int idUsuario = c.getInt(c.getColumnIndex("idUsuario"));
                String nombre = c.getString(c.getColumnIndex("nombre"));
                int puntajeActual = c.getInt(c.getColumnIndex("puntajeActual"));
                double latitudActual = c.getDouble(c.getColumnIndex("latitudActual"));
                double longitudActual = c.getDouble(c.getColumnIndex("longitudActual"));
                String email = c.getString(c.getColumnIndex("email"));
                Usuario usuario = new Usuario(idUsuario,nombre,puntajeActual,latitudActual,longitudActual,email);
                usuarios.add(usuario);
            }while (c.moveToNext());
        }
        db.close();
        return usuarios;
    }
}
