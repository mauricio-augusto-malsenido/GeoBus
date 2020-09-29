package com.mym.geobus.BaseDeDatosLocal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import com.mym.geobus.Entidades.LineaColectivo;

import java.util.ArrayList;
import java.util.List;

public class LineaColectivoDAO extends GeoBusOpenHelper {

    private SQLiteDatabase db; // Variable mediante la cual vamos a hacer referencia a nuestra base de datos

    // Operaciones para la tabla LineaColectivo
    private String insertarLineaColectivo = "INSERT INTO LineaColectivo (idLineaColectivo, nombre) VALUES (?,?)";
    private String eliminarLineaColectivo = "DELETE FROM LineaColectivo WHERE idLineaColectivo = ?";
    private String actualizarLineaColectivo = "UPDATE LineaColectivo SET nombre = ? WHERE idLineaColectivo = ?";
    private String obtenerLineaColectivoConId = "SELECT * FROM LineaColectivo WHERE idLineaColectivo = ?";
    private String obtenerLineaColectivoConNombre = "SELECT * FROM LineaColectivo WHERE nombre = ?";
    private String obtenerLineasColectivo = "SELECT * FROM LineaColectivo";

    public LineaColectivoDAO(@Nullable Context context) {
        super(context);
    }

    public void crearLineaColectivo(LineaColectivo linea)
    {
        db = this.getWritableDatabase();
        String[] params = new String[]{String.valueOf(linea.getIdLineaColectivo()),linea.getNombre()};
        db.execSQL(insertarLineaColectivo,params);
        db.close();
    }

    public void eliminarLineaColectivo(int idLineaColectivo)
    {
        db = this.getWritableDatabase();
        String[] params = new String[]{String.valueOf(idLineaColectivo)};
        db.execSQL(eliminarLineaColectivo,params);
        db.close();
    }

    public void modificarLineaColectivo(LineaColectivo linea)
    {
        db = this.getWritableDatabase();
        String[] params = new String[]{linea.getNombre(),String.valueOf(linea.getIdLineaColectivo())};
        db.execSQL(actualizarLineaColectivo,params);
        db.close();
    }

    public LineaColectivo obtenerLineaColectivoPorId(int idLineaColectivo)
    {
        db = this.getReadableDatabase();
        LineaColectivo lineaColectivo = null;
        String[] params = new String[]{String.valueOf(idLineaColectivo)};

        Cursor c = db.rawQuery(obtenerLineaColectivoConId,params);
        if (c.moveToFirst())
        {
            int idLinea = c.getInt(c.getColumnIndex("idLineaColectivo"));
            String nombre = c.getString(c.getColumnIndex("nombre"));
            lineaColectivo = new LineaColectivo(idLinea,nombre);
        }
        db.close();
        return lineaColectivo;
    }

    public LineaColectivo obtenerLineaColectivoPorNombre(String nom)
    {
        db = this.getReadableDatabase();
        LineaColectivo lineaColectivo = null;
        String[] params = new String[]{nom};

        Cursor c = db.rawQuery(obtenerLineaColectivoConNombre,params);
        if (c.moveToFirst())
        {
            int idLinea = c.getInt(c.getColumnIndex("idLineaColectivo"));
            String nombre = c.getString(c.getColumnIndex("nombre"));
            lineaColectivo = new LineaColectivo(idLinea,nombre);
        }
        db.close();
        return lineaColectivo;
    }

    public List<LineaColectivo> obtenerTodasLasLineasColectivos()
    {
        db = this.getReadableDatabase();
        List<LineaColectivo> lineasColectivo = new ArrayList<>();

        Cursor c = db.rawQuery(obtenerLineasColectivo,null);
        if (c.moveToFirst())
        {
            do {
                int idLinea = c.getInt(c.getColumnIndex("idLineaColectivo"));
                String nombre = c.getString(c.getColumnIndex("nombre"));
                LineaColectivo lineaColectivo = new LineaColectivo(idLinea,nombre);
                lineasColectivo.add(lineaColectivo);
            }while (c.moveToNext());
        }
        db.close();
        return lineasColectivo;
    }
}
