package com.mym.geobus.BaseDeDatosLocal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import com.mym.geobus.Entidades.UsuarioParada;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class UsuarioParadaDAO extends GeoBusOpenHelper {

    private SQLiteDatabase db; // Variable mediante la cual vamos a hacer referencia a nuestra base de datos

    // Operaciones para la tabla UsuarioParada
    private String insertarUsuarioParada = "INSERT INTO UsuarioParada (idUsuario,idParada,fecha,hora) VALUES (?,?,?,?)";
    private String eliminarUsuarioParada = "DELETE FROM UsuarioParada WHERE idUsuario = ? AND idParada = ?";
    private String actualizarUsuarioParada = "UPDATE UsuarioParada SET fecha = ?, hora = ? WHERE idUsuario = ? AND idParada = ?";
    private String obtenerUsuarioParada = "SELECT * FROM UsuarioParada WHERE idUsuario = ? AND idParada = ?";
    private String obtenerUsuariosParadasPorUsuario = "SELECT * FROM UsuarioParada WHERE idUsuario = ?";
    private String obtenerUsuariosParadas = "SELECT * FROM UsuarioParada";

    public UsuarioParadaDAO(@Nullable Context context) {
        super(context);
    }

    public void crearUsuarioParada(UsuarioParada usuarioParada)
    {
        db = this.getWritableDatabase();
        String[] params = new String[]{String.valueOf(usuarioParada.getIdUsuario()),String.valueOf(usuarioParada.getIdParada()),String.valueOf(usuarioParada.getFecha()),String.valueOf(usuarioParada.getHora())};
        db.execSQL(insertarUsuarioParada,params);
        db.close();
    }

    public void eliminarUsuarioParada(int idUsuario, int idParada)
    {
        db = this.getWritableDatabase();
        String[] params = new String[]{String.valueOf(idUsuario),String.valueOf(idParada)};
        db.execSQL(eliminarUsuarioParada,params);
        db.close();
    }

    public void modificarUsuarioParada(UsuarioParada usuarioParada)
    {
        db = this.getWritableDatabase();
        String[] params = new String[]{String.valueOf(usuarioParada.getFecha()),String.valueOf(usuarioParada.getHora()),String.valueOf(usuarioParada.getIdUsuario()),String.valueOf(usuarioParada.getIdParada())};
        db.execSQL(actualizarUsuarioParada,params);
        db.close();
    }

    public UsuarioParada obtenerUsuarioParadaPorId(int idU, int idP)
    {
        db = this.getReadableDatabase();
        UsuarioParada usuarioParada = null;
        String[] params = new String[]{String.valueOf(idU),String.valueOf(idP)};

        Cursor c = db.rawQuery(obtenerUsuarioParada,params);
        if (c.moveToFirst())
        {
            int idUsuario = c.getInt(c.getColumnIndex("idUsuario"));
            int idParada = c.getInt(c.getColumnIndex("idParada"));
            String fecha = c.getString(c.getColumnIndex("fecha"));
            String hora = c.getString(c.getColumnIndex("hora"));
            usuarioParada = new UsuarioParada(idUsuario,idParada,fecha,hora);
        }
        db.close();
        return usuarioParada;
    }

    public List<UsuarioParada> obtenerTodosLosUsuariosParadasPorUsuario(int idU)
    {
        db = this.getReadableDatabase();
        List<UsuarioParada> usuariosParadas = new ArrayList<>();
        String[] params = new String[]{String.valueOf(idU)};

        Cursor c = db.rawQuery(obtenerUsuariosParadasPorUsuario,params);
        if (c.moveToFirst())
        {
            do {
                int idUsuario = c.getInt(c.getColumnIndex("idUsuario"));
                int idParada = c.getInt(c.getColumnIndex("idParada"));
                String fecha = c.getString(c.getColumnIndex("fecha"));
                String hora = c.getString(c.getColumnIndex("hora"));
                UsuarioParada usuarioParada = new UsuarioParada(idUsuario,idParada,fecha,hora);
                usuariosParadas.add(usuarioParada);
            }while (c.moveToNext());
        }
        db.close();
        return usuariosParadas;
    }

    public List<UsuarioParada> obtenerTodosLosUsuariosParadas()
    {
        db = this.getReadableDatabase();
        List<UsuarioParada> usuariosParadas = new ArrayList<>();

        Cursor c = db.rawQuery(obtenerUsuariosParadas,null);
        if (c.moveToFirst())
        {
            do {
                int idUsuario = c.getInt(c.getColumnIndex("idUsuario"));
                int idParada = c.getInt(c.getColumnIndex("idParada"));
                String fecha = c.getString(c.getColumnIndex("fecha"));
                String hora = c.getString(c.getColumnIndex("hora"));
                UsuarioParada usuarioParada = new UsuarioParada(idUsuario,idParada,fecha,hora);
                usuariosParadas.add(usuarioParada);
            }while (c.moveToNext());
        }
        db.close();
        return usuariosParadas;
    }
}
