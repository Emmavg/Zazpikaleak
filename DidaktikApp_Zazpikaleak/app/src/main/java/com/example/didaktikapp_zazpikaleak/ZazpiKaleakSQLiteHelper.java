package com.example.didaktikapp_zazpikaleak;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ZazpiKaleakSQLiteHelper extends SQLiteOpenHelper {
    //Sentencia SQL para crear la tabla de Usuarios
    String sqlCreate1 =
            "CREATE TABLE Alumno (nombre TEXT PRIMARY KEY)";
    String sqlCreate2 =
            "CREATE TABLE Progreso (actividad TEXT PRIMARY KEY," +
                    " hecho BOOLEAN)";


    public ZazpiKaleakSQLiteHelper(Context contexto, String nombre,
                                   CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(sqlCreate1);
        db.execSQL(sqlCreate2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Alumno");
        db.execSQL("DROP TABLE IF EXISTS Progreso");
        //Se crea la nueva version de la tabla
        db.execSQL(sqlCreate1);
        db.execSQL(sqlCreate2);
    }
}

