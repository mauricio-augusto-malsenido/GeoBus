package com.mym.geobus.BaseDeDatosLocal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import com.mym.geobus.Entidades.Recorrido;

import java.util.ArrayList;
import java.util.List;

public class RecorridoDAO extends GeoBusOpenHelper {

    private SQLiteDatabase db; // Variable mediante la cual vamos a hacer referencia a nuestra base de datos

    // Operaciones para la tabla Recorrido
    private String insertarRecorrido = "INSERT INTO Recorrido (idRecorrido,nombre,descripcion,idLineaColectivo) VALUES (?,?,?,?)";
    private String eliminarRecorrido = "DELETE FROM Recorrido WHERE idRecorrido = ?";
    private String actualizarRecorrido = "UPDATE Recorrido SET nombre = ?, descripcion = ?, idLineaColectivo = ? WHERE idRecorrido = ?";
    private String obtenerRecorridoPorId = "SELECT * FROM Recorrido WHERE idRecorrido = ?";
    private String obtenerRecorridoPorNombre = "SELECT * FROM Recorrido WHERE nombre = ?";
    private String obtenerRecorridosPorLinea = "SELECT * FROM Recorrido WHERE idLineaColectivo = ?";
    private String obtenerRecorridos = "SELECT * FROM Recorrido";

    public RecorridoDAO(@Nullable Context context) {
        super(context);
    }

    public void crearRecorrido(Recorrido recorrido)
    {
        db = this.getWritableDatabase();
        String[] params = new String[]{String.valueOf(recorrido.getIdRecorrido()),recorrido.getNombre(),recorrido.getDescripcion(),String.valueOf(recorrido.getIdLineaColectivo())};
        db.execSQL(insertarRecorrido,params);
        db.close();
    }

    public void eliminarRecorrido(int idRecorrido)
    {
        db = this.getWritableDatabase();
        String[] params = new String[]{String.valueOf(idRecorrido)};
        db.execSQL(eliminarRecorrido,params);
        db.close();
    }

    public void modificarRecorrido(Recorrido recorrido)
    {
        db = this.getWritableDatabase();
        String[] params = new String[]{recorrido.getNombre(),recorrido.getDescripcion(),String.valueOf(recorrido.getIdLineaColectivo()),String.valueOf(recorrido.getIdRecorrido())};
        db.execSQL(actualizarRecorrido,params);
        db.close();
    }

    public Recorrido obtenerRecorridoPorId(int idR)
    {
        db = this.getReadableDatabase();
        Recorrido recorrido = null;
        String[] params = new String[]{String.valueOf(idR)};

        Cursor c = db.rawQuery(obtenerRecorridoPorId,params);
        if (c.moveToFirst())
        {
            int idRecorrido = c.getInt(c.getColumnIndex("idRecorrido"));
            String nombre = c.getString(c.getColumnIndex("nombre"));
            String descripcion = c.getString(c.getColumnIndex("descripcion"));
            int idLineaColectivo = c.getInt(c.getColumnIndex("idLineaColectivo"));
            recorrido = new Recorrido(idRecorrido,nombre,descripcion,idLineaColectivo);
        }
        db.close();
        return recorrido;
    }

    public Recorrido obtenerRecorridoPorNombre(String nom)
    {
        db = this.getReadableDatabase();
        Recorrido recorrido = null;
        String[] params = new String[]{nom};

        Cursor c = db.rawQuery(obtenerRecorridoPorNombre,params);
        if (c.moveToFirst())
        {
            int idRecorrido = c.getInt(c.getColumnIndex("idRecorrido"));
            String nombre = c.getString(c.getColumnIndex("nombre"));
            String descripcion = c.getString(c.getColumnIndex("descripcion"));
            int idLineaColectivo = c.getInt(c.getColumnIndex("idLineaColectivo"));
            recorrido = new Recorrido(idRecorrido,nombre,descripcion,idLineaColectivo);
        }
        db.close();
        return recorrido;
    }

    public List<Recorrido> obtenerTodosLosRecorridosPorLinea(int idL)
    {
        db = this.getReadableDatabase();
        List<Recorrido> recorridos = new ArrayList<>();
        String[] params = new String[]{String.valueOf(idL)};

        Cursor c = db.rawQuery(obtenerRecorridosPorLinea,params);
        if (c.moveToFirst())
        {
            do {
                int idRecorrido = c.getInt(c.getColumnIndex("idRecorrido"));
                String nombre = c.getString(c.getColumnIndex("nombre"));
                String descripcion = c.getString(c.getColumnIndex("descripcion"));
                int idLineaColectivo = c.getInt(c.getColumnIndex("idLineaColectivo"));
                Recorrido recorrido = new Recorrido(idRecorrido,nombre,descripcion,idLineaColectivo);
                recorridos.add(recorrido);
            }while (c.moveToNext());
        }
        db.close();
        return recorridos;
    }

    public List<Recorrido> obtenerTodosLosRecorridos()
    {
        db = this.getReadableDatabase();
        List<Recorrido> recorridos = new ArrayList<>();

        Cursor c = db.rawQuery(obtenerRecorridos,null);
        if (c.moveToFirst())
        {
            do {
                int idRecorrido = c.getInt(c.getColumnIndex("idRecorrido"));
                String nombre = c.getString(c.getColumnIndex("nombre"));
                String descripcion = c.getString(c.getColumnIndex("descripcion"));
                int idLineaColectivo = c.getInt(c.getColumnIndex("idLineaColectivo"));
                Recorrido recorrido = new Recorrido(idRecorrido,nombre,descripcion,idLineaColectivo);
                recorridos.add(recorrido);
            }while (c.moveToNext());
        }
        db.close();
        return recorridos;
    }
}
