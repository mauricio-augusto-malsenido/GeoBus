package com.mym.geobus.BaseDeDatosLocal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import com.mym.geobus.Entidades.Transmision;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class TransmisionDAO extends GeoBusOpenHelper {

    private SQLiteDatabase db; // Variable mediante la cual vamos a hacer referencia a nuestra base de datos

    // Operaciones para la tabla Transmision
    private String insertarTransmision = "INSERT INTO Transmision (idTransmision,fecha,horaComienzo,horaFin,puntajeObtenido,idUsuario,idColectivo) VALUES (?,?,?,?,?,?,?)";
    private String eliminarTransmision = "DELETE FROM Transmision WHERE idTransmision = ?";
    private String actualizarTransmision = "UPDATE Transmision SET fecha = ?, horaComienzo = ?, horaFin = ?, puntajeObtenido = ?, idUsuario = ?, idColectivo = ? WHERE idTransmision = ?";
    private String obtenerTransmision = "SELECT * FROM Transmision WHERE idTransmision = ?";
    private String obtenerTransmisionesPorUsuarioyColectivo = "SELECT * FROM Transmision WHERE idUsuario = ? AND idColectivo = ?";
    private String obtenerTransmisionesPorUsuario = "SELECT * FROM Transmision WHERE idUsuario = ?";
    private String obtenerTransmisionesPorColectivo = "SELECT * FROM Transmision WHERE idColectivo = ?";
    private String obtenerTransmisiones = "SELECT * FROM Transmision";

    public TransmisionDAO(@Nullable Context context) {
        super(context);
    }

    public void crearTransmision(Transmision transmision)
    {
        db = this.getWritableDatabase();
        String[] params = new String[]{String.valueOf(transmision.getIdTransmision()),String.valueOf(transmision.getFecha()),String.valueOf(transmision.getHoraComienzo()),"",String.valueOf(transmision.getPuntajeObtenido()),String.valueOf(transmision.getIdUsuario()),String.valueOf(transmision.getIdColectivo())};
        db.execSQL(insertarTransmision,params);
        db.close();
    }

    public void eliminarTransmision(int idTransmision)
    {
        db = this.getWritableDatabase();
        String[] params = new String[]{String.valueOf(idTransmision)};
        db.execSQL(eliminarTransmision,params);
        db.close();
    }

    public void modificarTransmision(Transmision transmision)
    {
        db = this.getWritableDatabase();
        String[] params = new String[]{String.valueOf(transmision.getFecha()),String.valueOf(transmision.getHoraComienzo()),String.valueOf(transmision.getHoraFin()),String.valueOf(transmision.getPuntajeObtenido()),String.valueOf(transmision.getIdUsuario()),String.valueOf(transmision.getIdColectivo()),String.valueOf(transmision.getIdTransmision())};
        db.execSQL(actualizarTransmision,params);
        db.close();
    }

    public Transmision obtenerTransmisionPorId (int idT)
    {
        db = this.getReadableDatabase();
        Transmision transmision = null;
        String[] params = new String[]{String.valueOf(idT)};

        Cursor c = db.rawQuery(obtenerTransmision,params);
        if (c.moveToFirst())
        {
            int idTransmision = c.getInt(c.getColumnIndex("idTransmision"));
            String fecha = c.getString(c.getColumnIndex("fecha"));
            String horaComienzo = c.getString(c.getColumnIndex("horaComienzo"));
            String horaFin = c.getString(c.getColumnIndex("horaFin"));
            int puntajeObtenido = c.getInt(c.getColumnIndex("puntajeObtenido"));
            int idUsuario = c.getInt(c.getColumnIndex("idUsuario"));
            int idColectivo = c.getInt(c.getColumnIndex("idColectivo"));
            transmision = new Transmision(idTransmision,fecha,horaComienzo,horaFin,puntajeObtenido,idUsuario,idColectivo);
        }
        db.close();
        return transmision;
    }

    public List<Transmision> obtenerTodasLasTransmisionesPorUsuarioyColectivo(int idU, int idC)
    {
        db = this.getReadableDatabase();
        List<Transmision> transmisiones = new ArrayList<>();
        String[] params = new String[]{String.valueOf(idU),String.valueOf(idC)};

        Cursor c = db.rawQuery(obtenerTransmisionesPorUsuarioyColectivo,params);
        if (c.moveToFirst())
        {
            do {
                int idTransmision = c.getInt(c.getColumnIndex("idTransmision"));
                String fecha = c.getString(c.getColumnIndex("fecha"));
                String horaComienzo = c.getString(c.getColumnIndex("horaComienzo"));
                String horaFin = c.getString(c.getColumnIndex("horaFin"));
                int puntajeObtenido = c.getInt(c.getColumnIndex("puntajeObtenido"));
                int idUsuario = c.getInt(c.getColumnIndex("idUsuario"));
                int idColectivo = c.getInt(c.getColumnIndex("idColectivo"));
                Transmision transmision = new Transmision(idTransmision,fecha,horaComienzo,horaFin,puntajeObtenido,idUsuario,idColectivo);
                transmisiones.add(transmision);
            }while (c.moveToNext());
        }
        db.close();
        return transmisiones;
    }

    public List<Transmision> obtenerTodasLasTransmisionesPorUsuario(int idU)
    {
        db = this.getReadableDatabase();
        List<Transmision> transmisiones = new ArrayList<>();
        String[] params = new String[]{String.valueOf(idU)};

        Cursor c = db.rawQuery(obtenerTransmisionesPorUsuario,params);
        if (c.moveToFirst())
        {
            do {
                int idTransmision = c.getInt(c.getColumnIndex("idTransmision"));
                String fecha = c.getString(c.getColumnIndex("fecha"));
                String horaComienzo = c.getString(c.getColumnIndex("horaComienzo"));
                String horaFin = c.getString(c.getColumnIndex("horaFin"));
                int puntajeObtenido = c.getInt(c.getColumnIndex("puntajeObtenido"));
                int idUsuario = c.getInt(c.getColumnIndex("idUsuario"));
                int idColectivo = c.getInt(c.getColumnIndex("idColectivo"));
                Transmision transmision = new Transmision(idTransmision,fecha,horaComienzo,horaFin,puntajeObtenido,idUsuario,idColectivo);
                transmisiones.add(transmision);
            }while (c.moveToNext());
        }
        db.close();
        return transmisiones;
    }

    public List<Transmision> obtenerTodasLasTransmisionesPorColectivo(int idC)
    {
        db = this.getReadableDatabase();
        List<Transmision> transmisiones = new ArrayList<>();
        String[] params = new String[]{String.valueOf(idC)};

        Cursor c = db.rawQuery(obtenerTransmisionesPorColectivo,params);
        if (c.moveToFirst())
        {
            do {
                int idTransmision = c.getInt(c.getColumnIndex("idTransmision"));
                String fecha = c.getString(c.getColumnIndex("fecha"));
                String horaComienzo = c.getString(c.getColumnIndex("horaComienzo"));
                String horaFin = c.getString(c.getColumnIndex("horaFin"));
                int puntajeObtenido = c.getInt(c.getColumnIndex("puntajeObtenido"));
                int idUsuario = c.getInt(c.getColumnIndex("idUsuario"));
                int idColectivo = c.getInt(c.getColumnIndex("idColectivo"));
                Transmision transmision = new Transmision(idTransmision,fecha,horaComienzo,horaFin,puntajeObtenido,idUsuario,idColectivo);
                transmisiones.add(transmision);
            }while (c.moveToNext());
        }
        db.close();
        return transmisiones;
    }

    public List<Transmision> obtenerTodasLasTransmisiones()
    {
        db = this.getReadableDatabase();
        List<Transmision> transmisiones = new ArrayList<>();

        Cursor c = db.rawQuery(obtenerTransmisiones,null);
        if (c.moveToFirst())
        {
            do {
                int idTransmision = c.getInt(c.getColumnIndex("idTransmision"));
                String fecha = c.getString(c.getColumnIndex("fecha"));
                String horaComienzo = c.getString(c.getColumnIndex("horaComienzo"));
                String horaFin = c.getString(c.getColumnIndex("horaFin"));
                int puntajeObtenido = c.getInt(c.getColumnIndex("puntajeObtenido"));
                int idUsuario = c.getInt(c.getColumnIndex("idUsuario"));
                int idColectivo = c.getInt(c.getColumnIndex("idColectivo"));
                Transmision transmision = new Transmision(idTransmision,fecha,horaComienzo,horaFin,puntajeObtenido,idUsuario,idColectivo);
                transmisiones.add(transmision);
            }while (c.moveToNext());
        }
        db.close();
        return transmisiones;
    }
}
