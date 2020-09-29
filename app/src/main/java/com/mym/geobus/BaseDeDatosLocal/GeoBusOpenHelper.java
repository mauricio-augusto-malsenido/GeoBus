package com.mym.geobus.BaseDeDatosLocal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class GeoBusOpenHelper extends SQLiteOpenHelper {

    // Datos para la creación de la base de datos
    private static String nombreBD = "GeoBus";
    private static int versionBD = 1;

    // Tabla Usuario
    private String crearTablaUsuario = "CREATE TABLE Usuario (idUsuario PRIMARY KEY, nombre TEXT, puntajeActual INTEGER, latitudActual REAL, longitudActual REAL, email TEXT)";
    private String eliminarTablaUsuario = "DROP TABLE IF EXISTS Usuario";

    // Tabla Parada
    private String crearTablaParada = "CREATE TABLE Parada (idParada PRIMARY KEY, latitud REAL, longitud REAL, idRecorrido INTEGER, idLineaColectivo INTEGER, FOREIGN KEY(idRecorrido) REFERENCES Recorrido(idRecorrido), FOREIGN KEY(idLineaColectivo) REFERENCES LineaColectivo(idLineaColectivo))";
    private String eliminarTablaParada = "DROP TABLE IF EXISTS Parada";

    // Tabla UsuarioParada
    private String crearTablaUsuarioParada = "CREATE TABLE UsuarioParada (idUsuario INTEGER, idParada INTEGER, fecha TEXT, hora TEXT, FOREIGN KEY(idUsuario) REFERENCES Usuario(idUsuario), FOREIGN KEY(idParada) REFERENCES Parada(idParada))";
    private String eliminarTablaUsuarioParada = "DROP TABLE IF EXISTS UsuarioParada";

    // Tabla Colectivo
    private String crearTablaColectivo = "CREATE TABLE Colectivo (idColectivo PRIMARY KEY, velocidad REAL, latitudActual REAL, longitudActual REAL, idRecorrido INTEGER, idLineaColectivo INTEGER, FOREIGN KEY(idRecorrido) REFERENCES Recorrido(idRecorrido), FOREIGN KEY(idLineaColectivo) REFERENCES LineaColectivo(idLineaColectivo))";
    private String eliminarTablaColectivo = "DROP TABLE IF EXISTS Colectivo";

    // Tabla Recorrido
    private String crearTablaRecorrido = "CREATE TABLE Recorrido (idRecorrido PRIMARY KEY, nombre TEXT, descripcion TEXT, idLineaColectivo INTEGER, FOREIGN KEY(idLineaColectivo) REFERENCES LineaColectivo(idLineaColectivo))";
    private String eliminarTablaRecorrido = "DROP TABLE IF EXISTS Recorrido";

    // Tabla LineaColectivo
    private String crearTablaLineaColectivo = "CREATE TABLE LineaColectivo (idLineaColectivo PRIMARY KEY, nombre TEXT)";
    private String eliminarTablaLineaColectivo = "DROP TABLE IF EXISTS LineaColectivo";

    // Tabla PuntoGeografico
    private String crearTablaPuntoGeografico = "CREATE TABLE PuntoGeografico (idPuntoGeografico PRIMARY KEY, latitud REAL, longitud REAL, idRecorrido INTEGER, FOREIGN KEY(idRecorrido) REFERENCES Recorrido(idRecorrido))";
    private String eliminarTablaPuntoGeografico = "DROP TABLE IF EXISTS PuntoGeografico";

    // Tabla Transmision
    private String crearTablaTransmision = "CREATE TABLE Transmision (idTransmision PRIMARY KEY, fecha TEXT, horaComienzo TEXT, horaFin TEXT, puntajeObtenido INTEGER, idUsuario INTEGER, idColectivo INTEGER, FOREIGN KEY(idUsuario) REFERENCES Usuario(idUsuario), FOREIGN KEY(idColectivo) REFERENCES Colectivo(idColectivo))";
    private String eliminarTablaTransmision = "DROP TABLE IF EXISTS Transmision";

    public GeoBusOpenHelper(@Nullable Context context) {
        super(context, nombreBD, null, versionBD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creación de las tablas de la base de datos
        db.execSQL(crearTablaUsuario);
        db.execSQL(crearTablaLineaColectivo);
        db.execSQL(crearTablaRecorrido);
        db.execSQL(crearTablaColectivo);
        db.execSQL(crearTablaTransmision);
        db.execSQL(crearTablaPuntoGeografico);
        db.execSQL(crearTablaParada);
        db.execSQL(crearTablaUsuarioParada);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Se eliminan todas las tablas de la base de datos
        db.execSQL(eliminarTablaTransmision);
        db.execSQL(eliminarTablaPuntoGeografico);
        db.execSQL(eliminarTablaUsuarioParada);
        db.execSQL(eliminarTablaParada);
        db.execSQL(eliminarTablaUsuario);
        db.execSQL(eliminarTablaColectivo);
        db.execSQL(eliminarTablaRecorrido);
        db.execSQL(eliminarTablaLineaColectivo);

        // Se vuelven a crear todas las tablas de la base de datos
        db.execSQL(crearTablaUsuario);
        db.execSQL(crearTablaLineaColectivo);
        db.execSQL(crearTablaRecorrido);
        db.execSQL(crearTablaColectivo);
        db.execSQL(crearTablaTransmision);
        db.execSQL(crearTablaPuntoGeografico);
        db.execSQL(crearTablaParada);
        db.execSQL(crearTablaUsuarioParada);
    }
}
