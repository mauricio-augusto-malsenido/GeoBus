package com.mym.geobus.BaseDeDatosLocal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import com.mym.geobus.Entidades.Colectivo;

import java.util.ArrayList;
import java.util.List;

public class ColectivoDAO extends GeoBusOpenHelper {

    private SQLiteDatabase db; // Variable mediante la cual vamos a hacer referencia a nuestra base de datos

    // Operaciones para la tabla Colectivo
    private String insertarColectivo = "INSERT INTO Colectivo (idColectivo,velocidad,latitudActual,longitudActual,idRecorrido,idLineaColectivo) VALUES (?,?,?,?,?,?)";
    private String eliminarColectivo = "DELETE FROM Colectivo WHERE idColectivo = ?";
    private String actualizarColectivo = "UPDATE Colectivo SET velocidad = ?, latitudActual = ?, longitudActual = ?, idRecorrido = ?, idLineaColectivo = ? WHERE idColectivo = ?";
    private String obtenerColectivo = "SELECT * FROM Colectivo WHERE idColectivo = ?";
    private String obtenerColectivosPorRecorridoyLinea = "SELECT * FROM Colectivo WHERE idRecorrido = ? AND idLineaColectivo = ?";
    private String obtenerColectivosPorRecorrido = "SELECT * FROM Colectivo WHERE idRecorrido = ?";
    private String obtenerColectivosPorLinea = "SELECT * FROM Colectivo WHERE idLineaColectivo = ?";
    private String obtenerColectivos = "SELECT * FROM Colectivo";

    public ColectivoDAO(@Nullable Context context) {
        super(context);
    }

    public void crearColectivo(Colectivo colectivo)
    {
        db = this.getWritableDatabase();
        String[] params = new String[]{String.valueOf(colectivo.getIdColectivo()),String.valueOf(colectivo.getVelocidad()),String.valueOf(colectivo.getLatitudActual()),String.valueOf(colectivo.getLongitudActual()),String.valueOf(colectivo.getIdRecorrido()),String.valueOf(colectivo.getIdLineaColectivo())};
        db.execSQL(insertarColectivo,params);
        db.close();
    }

    public void eliminarColectivo(int idColectivo)
    {
        db = this.getWritableDatabase();
        String[] params = new String[]{String.valueOf(idColectivo)};
        db.execSQL(eliminarColectivo,params);
        db.close();
    }

    public void modificarColectivo(Colectivo colectivo)
    {
        db = this.getWritableDatabase();
        String[] params = new String[]{String.valueOf(colectivo.getVelocidad()),String.valueOf(colectivo.getLatitudActual()),String.valueOf(colectivo.getLongitudActual()),String.valueOf(colectivo.getIdRecorrido()),String.valueOf(colectivo.getIdLineaColectivo()),String.valueOf(colectivo.getIdColectivo())};
        db.execSQL(actualizarColectivo,params);
        db.close();
    }

    public Colectivo obtenerColectivoPorId(int idC)
    {
        db = this.getReadableDatabase();
        Colectivo colectivo = null;
        String[] params = new String[]{String.valueOf(idC)};

        Cursor c = db.rawQuery(obtenerColectivo,params);
        if (c.moveToFirst())
        {
            int idColectivo = c.getInt(c.getColumnIndex("idColectivo"));
            float velocidad = c.getFloat(c.getColumnIndex("velocidad"));
            double latitudActual = c.getDouble(c.getColumnIndex("latitudActual"));
            double longitudActual = c.getDouble(c.getColumnIndex("longitudActual"));
            int idRecorrido = c.getInt(c.getColumnIndex("idRecorrido"));
            int idLineaColectivo = c.getInt(c.getColumnIndex("idLineaColectivo"));
            colectivo = new Colectivo(idColectivo,velocidad,latitudActual,longitudActual,idRecorrido,idLineaColectivo);
        }
        db.close();
        return colectivo;
    }

    public List<Colectivo> obtenerTodosLosColectivosPorRecorridoyLinea(int idR, int idL)
    {
        db = this.getReadableDatabase();
        List<Colectivo> colectivos = new ArrayList<>();
        String[] params = new String[]{String.valueOf(idR),String.valueOf(idL)};

        Cursor c = db.rawQuery(obtenerColectivosPorRecorridoyLinea,params);
        if (c.moveToFirst())
        {
            do {
                int idColectivo = c.getInt(c.getColumnIndex("idColectivo"));
                float velocidad = c.getFloat(c.getColumnIndex("velocidad"));
                double latitudActual = c.getDouble(c.getColumnIndex("latitudActual"));
                double longitudActual = c.getDouble(c.getColumnIndex("longitudActual"));
                int idRecorrido = c.getInt(c.getColumnIndex("idRecorrido"));
                int idLineaColectivo = c.getInt(c.getColumnIndex("idLineaColectivo"));
                Colectivo colectivo = new Colectivo(idColectivo,velocidad,latitudActual,longitudActual,idRecorrido,idLineaColectivo);
                colectivos.add(colectivo);
            }while (c.moveToNext());
        }
        db.close();
        return colectivos;
    }

    public List<Colectivo> obtenerTodosLosColectivosPorRecorrido(int idR)
    {
        db = this.getReadableDatabase();
        List<Colectivo> colectivos = new ArrayList<>();
        String[] params = new String[]{String.valueOf(idR)};

        Cursor c = db.rawQuery(obtenerColectivosPorRecorrido,params);
        if (c.moveToFirst())
        {
            do {
                int idColectivo = c.getInt(c.getColumnIndex("idColectivo"));
                float velocidad = c.getFloat(c.getColumnIndex("velocidad"));
                double latitudActual = c.getDouble(c.getColumnIndex("latitudActual"));
                double longitudActual = c.getDouble(c.getColumnIndex("longitudActual"));
                int idRecorrido = c.getInt(c.getColumnIndex("idRecorrido"));
                int idLineaColectivo = c.getInt(c.getColumnIndex("idLineaColectivo"));
                Colectivo colectivo = new Colectivo(idColectivo,velocidad,latitudActual,longitudActual,idRecorrido,idLineaColectivo);
                colectivos.add(colectivo);
            }while (c.moveToNext());
        }
        db.close();
        return colectivos;
    }

    public List<Colectivo> obtenerTodosLosColectivosPorLinea(int idL)
    {
        db = this.getReadableDatabase();
        List<Colectivo> colectivos = new ArrayList<>();
        String[] params = new String[]{String.valueOf(idL)};

        Cursor c = db.rawQuery(obtenerColectivosPorLinea,params);
        if (c.moveToFirst())
        {
            do {
                int idColectivo = c.getInt(c.getColumnIndex("idColectivo"));
                float velocidad = c.getFloat(c.getColumnIndex("velocidad"));
                double latitudActual = c.getDouble(c.getColumnIndex("latitudActual"));
                double longitudActual = c.getDouble(c.getColumnIndex("longitudActual"));
                int idRecorrido = c.getInt(c.getColumnIndex("idRecorrido"));
                int idLineaColectivo = c.getInt(c.getColumnIndex("idLineaColectivo"));
                Colectivo colectivo = new Colectivo(idColectivo,velocidad,latitudActual,longitudActual,idRecorrido,idLineaColectivo);
                colectivos.add(colectivo);
            }while (c.moveToNext());
        }
        db.close();
        return colectivos;
    }

    public List<Colectivo> obtenerTodosLosColectivos()
    {
        db = this.getReadableDatabase();
        List<Colectivo> colectivos = new ArrayList<>();

        Cursor c = db.rawQuery(obtenerColectivos,null);
        if (c.moveToFirst())
        {
            do {
                int idColectivo = c.getInt(c.getColumnIndex("idColectivo"));
                float velocidad = c.getFloat(c.getColumnIndex("velocidad"));
                double latitudActual = c.getDouble(c.getColumnIndex("latitudActual"));
                double longitudActual = c.getDouble(c.getColumnIndex("longitudActual"));
                int idRecorrido = c.getInt(c.getColumnIndex("idRecorrido"));
                int idLineaColectivo = c.getInt(c.getColumnIndex("idLineaColectivo"));
                Colectivo colectivo = new Colectivo(idColectivo,velocidad,latitudActual,longitudActual,idRecorrido,idLineaColectivo);
                colectivos.add(colectivo);
            }while (c.moveToNext());
        }
        db.close();
        return colectivos;
    }
}
