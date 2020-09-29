package com.mym.geobus.BaseDeDatosLocal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import com.mym.geobus.Entidades.Parada;

import java.util.ArrayList;
import java.util.List;

public class ParadaDAO extends GeoBusOpenHelper {

    private SQLiteDatabase db; // Variable mediante la cual vamos a hacer referencia a nuestra base de datos

    // Operaciones para la tabla Parada
    private String insertarParada = "INSERT INTO Parada (idParada,latitud,longitud,idRecorrido,idLineaColectivo) VALUES (?,?,?,?,?)";
    private String eliminarParada = "DELETE FROM Parada WHERE idParada = ?";
    private String actualizarParada = "UPDATE Parada SET latitud = ?, longitud = ?, idRecorrido = ?, idLineaColectivo = ? WHERE idParada = ?";
    private String obtenerParada = "SELECT * FROM Parada WHERE idParada = ?";
    private String obtenerParadaPorPosicion = "SELECT * FROM Parada WHERE latitud = ? AND longitud = ?";
    private String obtenerParadasPorRecorridoyLinea = "SELECT * FROM Parada WHERE idRecorrido = ? AND idLineaColectivo = ?";
    private String obtenerParadasPorRecorrido = "SELECT * FROM Parada WHERE idRecorrido = ?";
    private String obtenerParadasPorLinea = "SELECT * FROM Parada WHERE idLineaColectivo = ?";
    private String obtenerParadas = "SELECT * FROM Parada";

    public ParadaDAO(@Nullable Context context) {
        super(context);
    }

    public void crearParada(Parada parada)
    {
        db = this.getWritableDatabase();
        String[] params = new String[]{String.valueOf(parada.getIdParada()),String.valueOf(parada.getLatitud()),String.valueOf(parada.getLongitud()),String.valueOf(parada.getIdRecorrido()),String.valueOf(parada.getIdLineaColectivo())};
        db.execSQL(insertarParada,params);
        db.close();
    }

    public void eliminarParada(int idParada)
    {
        db = this.getWritableDatabase();
        String[] params = new String[]{String.valueOf(idParada)};
        db.execSQL(eliminarParada,params);
        db.close();
    }

    public void modificarParada(Parada parada)
    {
        db = this.getWritableDatabase();
        String[] params = new String[]{String.valueOf(parada.getLatitud()),String.valueOf(parada.getLongitud()),String.valueOf(parada.getIdRecorrido()),String.valueOf(parada.getIdLineaColectivo()),String.valueOf(parada.getIdParada())};
        db.execSQL(actualizarParada,params);
        db.close();
    }

    public Parada obtenerParadaPorId(int idP)
    {
        db = this.getReadableDatabase();
        Parada parada = null;
        String[] params = new String[]{String.valueOf(idP)};

        Cursor c = db.rawQuery(obtenerParada,params);
        if (c.moveToFirst())
        {
            int idParada = c.getInt(c.getColumnIndex("idParada"));
            double latitud = c.getDouble(c.getColumnIndex("latitud"));
            double longitud = c.getDouble(c.getColumnIndex("longitud"));
            int idRecorrido = c.getInt(c.getColumnIndex("idRecorrido"));
            int idLineaColectivo = c.getInt(c.getColumnIndex("idLineaColectivo"));
            parada = new Parada(idParada,latitud,longitud,idRecorrido,idLineaColectivo);
        }
        db.close();
        return parada;
    }

    public Parada obtenerParadaPorPosicion(double lat, double lng)
    {
        db = this.getReadableDatabase();
        Parada parada = null;
        String[] params = new String[]{String.valueOf(lat),String.valueOf(lng)};

        Cursor c = db.rawQuery(obtenerParadaPorPosicion,params);
        if (c.moveToFirst())
        {
            int idParada = c.getInt(c.getColumnIndex("idParada"));
            double latitud = c.getDouble(c.getColumnIndex("latitud"));
            double longitud = c.getDouble(c.getColumnIndex("longitud"));
            int idRecorrido = c.getInt(c.getColumnIndex("idRecorrido"));
            int idLineaColectivo = c.getInt(c.getColumnIndex("idLineaColectivo"));
            parada = new Parada(idParada,latitud,longitud,idRecorrido,idLineaColectivo);
        }
        db.close();
        return parada;
    }

    public List<Parada> obtenerTodasLasParadasPorRecorridoyLinea(int idR, int idL)
    {
        db = this.getReadableDatabase();
        List<Parada> paradas = new ArrayList<>();
        String[] params = new String[]{String.valueOf(idR),String.valueOf(idL)};

        Cursor c = db.rawQuery(obtenerParadasPorRecorridoyLinea,params);
        if (c.moveToFirst())
        {
            do {
                int idParada = c.getInt(c.getColumnIndex("idParada"));
                double latitud = c.getDouble(c.getColumnIndex("latitud"));
                double longitud = c.getDouble(c.getColumnIndex("longitud"));
                int idRecorrido = c.getInt(c.getColumnIndex("idRecorrido"));
                int idLineaColectivo = c.getInt(c.getColumnIndex("idLineaColectivo"));
                Parada parada = new Parada(idParada,latitud,longitud,idRecorrido,idLineaColectivo);
                paradas.add(parada);
            }while (c.moveToNext());
        }
        db.close();
        return paradas;
    }

    public List<Parada> obtenerTodasLasParadasPorRecorrido(int idR)
    {
        db = this.getReadableDatabase();
        List<Parada> paradas = new ArrayList<>();
        String[] params = new String[]{String.valueOf(idR)};

        Cursor c = db.rawQuery(obtenerParadasPorRecorrido,params);
        if (c.moveToFirst())
        {
            do {
                int idParada = c.getInt(c.getColumnIndex("idParada"));
                double latitud = c.getDouble(c.getColumnIndex("latitud"));
                double longitud = c.getDouble(c.getColumnIndex("longitud"));
                int idRecorrido = c.getInt(c.getColumnIndex("idRecorrido"));
                int idLineaColectivo = c.getInt(c.getColumnIndex("idLineaColectivo"));
                Parada parada = new Parada(idParada,latitud,longitud,idRecorrido,idLineaColectivo);
                paradas.add(parada);
            }while (c.moveToNext());
        }
        db.close();
        return paradas;
    }

    public List<Parada> obtenerTodasLasParadasPorLinea(int idL)
    {
        db = this.getReadableDatabase();
        List<Parada> paradas = new ArrayList<>();
        String[] params = new String[]{String.valueOf(idL)};

        Cursor c = db.rawQuery(obtenerParadasPorLinea,params);
        if (c.moveToFirst())
        {
            do {
                int idParada = c.getInt(c.getColumnIndex("idParada"));
                double latitud = c.getDouble(c.getColumnIndex("latitud"));
                double longitud = c.getDouble(c.getColumnIndex("longitud"));
                int idRecorrido = c.getInt(c.getColumnIndex("idRecorrido"));
                int idLineaColectivo = c.getInt(c.getColumnIndex("idLineaColectivo"));
                Parada parada = new Parada(idParada,latitud,longitud,idRecorrido,idLineaColectivo);
                paradas.add(parada);
            }while (c.moveToNext());
        }
        db.close();
        return paradas;
    }

    public List<Parada> obtenerTodasLasParadas()
    {
        db = this.getReadableDatabase();
        List<Parada> paradas = new ArrayList<>();

        Cursor c = db.rawQuery(obtenerParadas,null);
        if (c.moveToFirst())
        {
            do {
                int idParada = c.getInt(c.getColumnIndex("idParada"));
                double latitud = c.getDouble(c.getColumnIndex("latitud"));
                double longitud = c.getDouble(c.getColumnIndex("longitud"));
                int idRecorrido = c.getInt(c.getColumnIndex("idRecorrido"));
                int idLineaColectivo = c.getInt(c.getColumnIndex("idLineaColectivo"));
                Parada parada = new Parada(idParada,latitud,longitud,idRecorrido,idLineaColectivo);
                paradas.add(parada);
            }while (c.moveToNext());
        }
        db.close();
        return paradas;
    }
}
