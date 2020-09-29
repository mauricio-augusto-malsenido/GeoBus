package com.mym.geobus.BaseDeDatosLocal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import com.mym.geobus.Entidades.PuntoGeografico;

import java.util.ArrayList;
import java.util.List;

public class PuntoGeograficoDAO extends GeoBusOpenHelper {

    private SQLiteDatabase db; // Variable mediante la cual vamos a hacer referencia a nuestra base de datos

    // Operaciones para la tabla PuntoGeografico
    private String insertarPuntoGeografico = "INSERT INTO PuntoGeografico (idPuntoGeografico,latitud,longitud,idRecorrido) VALUES (?,?,?,?)";
    private String eliminarPuntoGeografico = "DELETE FROM PuntoGeografico WHERE idPuntoGeografico = ?";
    private String actualizarPuntoGeografico = "UPDATE PuntoGeografico SET latitud = ?, longitud = ?, idRecorrido = ? WHERE idPuntoGeografico = ?";
    private String obtenerPuntoGeograficoPorId = "SELECT * FROM PuntoGeografico WHERE idPuntoGeografico = ?";
    private String obtenerPuntoGeograficoPorPosicionyRecorrido = "SELECT * FROM PuntoGeografico WHERE latitud = ? AND longitud = ? AND idRecorrido = ?";
    private String obtenerPuntosGeograficosPorRecorrido = "SELECT * FROM PuntoGeografico WHERE idRecorrido = ?";
    private String obtenerPuntosGeograficos = "SELECT * FROM PuntoGeografico";

    public PuntoGeograficoDAO(@Nullable Context context) {
        super(context);
    }

    public void crearPuntoGeografico(PuntoGeografico puntoGeografico)
    {
        db = this.getWritableDatabase();
        String[] params = new String[]{String.valueOf(puntoGeografico.getIdPuntoGeografico()),String.valueOf(puntoGeografico.getLatitud()),String.valueOf(puntoGeografico.getLongitud()),String.valueOf(puntoGeografico.getIdRecorrido())};
        db.execSQL(insertarPuntoGeografico,params);
        db.close();
    }

    public void eliminarPuntoGeografico(int idPuntoGeografico)
    {
        db = this.getWritableDatabase();
        String[] params = new String[]{String.valueOf(idPuntoGeografico)};
        db.execSQL(eliminarPuntoGeografico,params);
        db.close();
    }

    public void modificarPuntoGeografico(PuntoGeografico puntoGeografico)
    {
        db = this.getWritableDatabase();
        String[] params = new String[]{String.valueOf(puntoGeografico.getLatitud()),String.valueOf(puntoGeografico.getLongitud()),String.valueOf(puntoGeografico.getIdRecorrido()),String.valueOf(puntoGeografico.getIdPuntoGeografico())};
        db.execSQL(actualizarPuntoGeografico,params);
        db.close();
    }

    public PuntoGeografico obtenerPuntoGeograficoPorId(int idPG)
    {
        db = this.getReadableDatabase();
        PuntoGeografico puntoGeografico = null;
        String[] params = new String[]{String.valueOf(idPG)};

        Cursor c = db.rawQuery(obtenerPuntoGeograficoPorId,params);
        if (c.moveToFirst())
        {
            int idPuntoGeografico = c.getInt(c.getColumnIndex("idPuntoGeografico"));
            double latitud = c.getDouble(c.getColumnIndex("latitud"));
            double longitud = c.getDouble(c.getColumnIndex("longitud"));
            int idRecorrido = c.getInt(c.getColumnIndex("idRecorrido"));
            puntoGeografico = new PuntoGeografico(idPuntoGeografico,latitud,longitud,idRecorrido);
        }
        db.close();
        return puntoGeografico;
    }

    public PuntoGeografico obtenerPuntoGeograficoPorPosicionyRecorrido(double lat, double lng, int idR)
    {
        db = this.getReadableDatabase();
        PuntoGeografico puntoGeografico = null;
        String[] params = new String[]{String.valueOf(lat),String.valueOf(lng),String.valueOf(idR)};

        Cursor c = db.rawQuery(obtenerPuntoGeograficoPorPosicionyRecorrido,params);
        if (c.moveToFirst())
        {
            int idPuntoGeografico = c.getInt(c.getColumnIndex("idPuntoGeografico"));
            double latitud = c.getDouble(c.getColumnIndex("latitud"));
            double longitud = c.getDouble(c.getColumnIndex("longitud"));
            int idRecorrido = c.getInt(c.getColumnIndex("idRecorrido"));
            puntoGeografico = new PuntoGeografico(idPuntoGeografico,latitud,longitud,idRecorrido);
        }
        db.close();
        return puntoGeografico;
    }

    public List<PuntoGeografico> obtenerTodosLosPuntosGeograficosPorRecorrido(int idR)
    {
        db = this.getReadableDatabase();
        List<PuntoGeografico> puntosGeograficos = new ArrayList<>();
        String[] params = new String[]{String.valueOf(idR)};

        Cursor c = db.rawQuery(obtenerPuntosGeograficosPorRecorrido,params);
        if (c.moveToFirst())
        {
            do {
                int idPuntoGeografico = c.getInt(c.getColumnIndex("idPuntoGeografico"));
                double latitud = c.getDouble(c.getColumnIndex("latitud"));
                double longitud = c.getDouble(c.getColumnIndex("longitud"));
                int idRecorrido = c.getInt(c.getColumnIndex("idRecorrido"));
                PuntoGeografico puntoGeografico = new PuntoGeografico(idPuntoGeografico,latitud,longitud,idRecorrido);
                puntosGeograficos.add(puntoGeografico);
            }while (c.moveToNext());
        }
        db.close();
        return puntosGeograficos;
    }

    public List<PuntoGeografico> obtenerTodosLosPuntosGeograficos()
    {
        db = this.getReadableDatabase();
        List<PuntoGeografico> puntosGeograficos = new ArrayList<>();

        Cursor c = db.rawQuery(obtenerPuntosGeograficos,null);
        if (c.moveToFirst())
        {
            do {
                int idPuntoGeografico = c.getInt(c.getColumnIndex("idPuntoGeografico"));
                double latitud = c.getDouble(c.getColumnIndex("latitud"));
                double longitud = c.getDouble(c.getColumnIndex("longitud"));
                int idRecorrido = c.getInt(c.getColumnIndex("idRecorrido"));
                PuntoGeografico puntoGeografico = new PuntoGeografico(idPuntoGeografico,latitud,longitud,idRecorrido);
                puntosGeograficos.add(puntoGeografico);
            }while (c.moveToNext());
        }
        db.close();
        return puntosGeograficos;
    }
}
